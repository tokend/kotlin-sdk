### DONT CHANGE BELOW ###

require 'yaml'
require 'json-schema'
require 'optparse'

specs_folder_path = ''
output_folder_path = ''
$namespace = ''

options_parser = OptionParser.new do |opts|
  opts.banner = "Usage: generator_java.rb [options]"

  opts.on("-s DIR", "--specs DIR", "Specs directory") do |specs|
    specs_folder_path = specs
  end

  opts.on("-o DIR", "--out DIR", "Output directory") do |output|
    output_folder_path = output
  end

  opts.on("-n NAMESPACE", "--namespace NAMESPACE", "Namespace of generated classes") do |namespace_option|
    $namespace = namespace_option
  end

  opts.on("-h", "--help", "Prints this help") do
    puts opts
    exit
  end
end
options_parser.parse!

if specs_folder_path == ''
  puts "Missing specs directory argument"
  puts options_parser
  exit
end

if output_folder_path == ''
  puts "Missing output directory argument"
  puts options_parser
  exit
end

if $namespace == ''
  puts "Missing namespace argument"
  puts options_parser
  exit
end

inner_folder_path = "#{specs_folder_path}/inner"
resource_folder_path = "#{specs_folder_path}/resources"

resource_schema_path = "#{__dir__}/schema/resource.yaml"
inner_schema_path = "#{__dir__}/schema/inner.yaml"

## Parse schemas

resource_schema = nil
inner_schema = nil

begin
  resource_schema = YAML.safe_load File.read resource_schema_path
rescue Exception => e
  puts "Failed to load '#{resource_schema_path}'. Error: #{e.message}\n"
  exit
end

begin
  inner_schema = YAML.safe_load File.read inner_schema_path
rescue Exception => e
  puts "Failed to load '#{inner_schema_path}'. Error: #{e.message}\n"
  exit
end

## Parse configs

inner_files = Dir[inner_folder_path + '/*.yaml']
resource_files = Dir[resource_folder_path + '/*.yaml']

$has_invalid_configs = false

def parse_configs(config_files, key, schema)
  configs = {}
  config_files.each do |config|
    contens = File.read config

    begin
      yaml = YAML.safe_load contens
    rescue Exception => e
      puts "Failed to load '#{config}'. Error: #{e.message}\n\n"
      $has_invalid_configs = true
      next
    end
    
    key_value = yaml[key]

    if key_value.nil?
      puts "Resource config '#{config}' has no value for key '#{key}'. Abort!\n\n"
      $has_invalid_configs = true
      next
    end
    
    begin
      JSON::Validator.validate!(schema, yaml)
    rescue Exception => e
      puts "Failed to validate '#{key_value}'. Error: #{e.message}\n\n"
      $has_invalid_configs = true
      next
    end

    configs[key_value.to_s] = yaml
  end
  configs
end

$resource_configs = parse_configs resource_files, 'key', resource_schema
$inner_configs = parse_configs inner_files, 'name', inner_schema

if $has_invalid_configs
  puts "Fix invalid configs!"
  exit
end

## Utils

def get_resource(key)
  resource_config = $resource_configs[key]

  if resource_config.nil?
    #puts "Resource for key \"#{key}\" not found. Abort!"
    puts "Resource for key \"#{key}\" not found. Return Base!"
    resource_config = { "name" => "Base" }
  end

  resource_config
end

def get_resource_by_name(name)
  resource_config = $resource_configs.find { |r| r[1]['name'] == name }

  if resource_config.nil?
    puts "Resource for name \"#{name}\" not found. Abort!"
    exit
  end

  resource_config[1]
end

def get_inner(name)
  $inner_configs[name]
end

$indention = '    '

def repeat_string(string, count: Int)
  result = ''
  (1..count).each do |_|
    result += string
  end
  result
end

def get_indention(level)
  result = repeat_string $indention, count: level
  result
end

$output_resources_directory = "#{output_folder_path}/resources"
$output_inner_directory = "#{output_folder_path}/inner"

previous_files = Dir["#{output_folder_path}/**/*.java"]
previous_files.each do |prev|
  File.delete prev
end

$output_string = ''

def add_line(line)
  $output_string += "#{line}\n"
end

def break_line
  add_line ''
end

def indent(level)
  $output_string += get_indention level
end

def add_indented_line(level, line: String)
  indent level
  add_line line
end

def indent_break(level)
  $output_string += get_indention level
  break_line
end

def close_block(level)
  indent level
  add_line '}'
end

def get_output_attribute_type(type)
  if type == 'Amount'
    'BigDecimal'
  elsif type == 'Double'
    'Double'
  elsif type == 'Object'
    'JsonNode'
  elsif type == 'String'
    type
  elsif type == 'Date'
    type
  elsif type == 'Int'
    'Integer'
  elsif type == 'Int32'
    'Integer'
  elsif type == 'Int64'
    'Long'
  elsif type == 'UInt32'
    'Long'
  elsif type == 'UInt64'
    'Long'
  elsif type == 'Bool'
    'Boolean'
  end
end

def get_camel_name(string)
  puts 'zero for camel' if string.nil?

  words = string.split('_')
  if words.count > 1
    words.map!(&:capitalize)
    words[0].downcase!
    return words.join
  else
    return string
  end
end

### DONT CHANGE ABOVE ###

#------------------ Generator code ------------------#

$all_resource_names = []

def get_attribute_type(attribute)
  type = get_camel_name attribute['type'] 
  output_type = get_output_attribute_type type

  if output_type.nil?
    inner = get_inner type

    if inner.nil?
      resource = get_resource_by_name type

      if resource.nil?
        puts "Unknown attribute type: '#{type}'"
        return
      end

      resource_name = "#{resource['name']}Resource"
      output_type = resource_name
    else
      inner_name = inner['name']
      output_type = inner_name
    end
  end

  return output_type
end

def write_property_and_getter(name, type, is_optional, is_colelction, is_string_map)
  if is_optional
    add_indented_line 1, line: "@Nullable"
  end

  type = "#{$namespace}.inner.Enum" if type == "Enum"
  
  property_type = type
  if is_colelction
    property_type = "List<#{type}>"
  end
  if is_string_map
    property_type = "Map<String, #{type}>"
  end

  add_indented_line 1, line: "private #{property_type} #{name};"

  indent_break 1

  # Getter
  if is_optional
    add_indented_line 1, line: "@Nullable"
  end

  getter_type = type
  if is_colelction
    getter_type = "List<? extends #{type}>"
  end
  if is_string_map
    getter_type = "Map<String, ? extends #{type}>"
  end

  getter_name = "get#{name.sub(/^./, &:upcase)}"
  getter_name = name if type == "Boolean"

  add_indented_line 1, line: "public #{getter_type} #{getter_name}() {"
  add_indented_line 2, line: "return #{name};"
  close_block 1
end

### DONT CHANGE BELOW ###

$resource_configs.each do |key, info|
  # puts "#{key}: #{info}"

  ### DONT CHANGE ABOVE ###

  #------------------ Generator code ------------------#

  $output_string = "// Auto-generated code. Do not edit.\n\n"
  $output_string += "package #{$namespace}.resources;\n\n"

  resource_name = info['name']
  output_resource_name = "#{resource_name}Resource"

  add_line 'import java.math.*;'
  add_line 'import java.util.*;'
  add_line 'import com.fasterxml.jackson.annotation.*;'
  add_line 'import com.github.jasminb.jsonapi.annotations.*;'
  add_line "import #{$namespace}.resources.*;"
  add_line "import #{$namespace}.inner.*;" unless $inner_configs.empty?
  add_line 'import com.fasterxml.jackson.databind.*;'
  add_line 'import org.jetbrains.annotations.Nullable;'
  add_line 'import org.tokend.sdk.api.base.model.*;'
  
  break_line

  puts "Render: #{resource_name}"

  ## Resource definition

  break_line
  
  super_class = 'BaseResource'
  base = info['base']
  unless base.nil?
    base_resource = get_resource base
    base_resource_name = base_resource['name']
    super_class = "#{base_resource_name}Resource"
  end
  
  add_line "@Type(\"#{key}\")"
  add_line "@JsonIgnoreProperties(ignoreUnknown = true)"
  add_line "public class #{output_resource_name} extends #{super_class} {"

  attributes = info['attributes']
  relations = info['relations']

  attributes_count = 0
  relations_count = 0

  attributes_count = attributes.count unless attributes.nil?
  relations_count = relations.count unless relations.nil?

  # Attributes 
  if attributes_count == 0
    add_indented_line 1, line: "@Override"
    unless base.nil?
      add_indented_line 1, line: "public boolean isFilled() { return super.isFilled(); }"
    else 
      add_indented_line 1, line: "public boolean isFilled() { return true; }"
    end
  else
    non_optional_attribute_names = []
    attribute_names = []

    attributes.each do |attribute|
      indent_break 1
      
      json_name = attribute['json_name'] || attribute['name']
      add_indented_line 1, line: "@JsonProperty(\"#{json_name}\")"  
      
      name = get_camel_name attribute['name']
      type = get_attribute_type attribute
      is_optional = attribute['optional']
      is_collection = attribute['is_collection']
      is_string_map = attribute['is_string_map']

      attribute_names.push(name)
      if !is_optional
        non_optional_attribute_names.push(name)
      end

      write_property_and_getter(name, type, is_optional, is_collection, is_string_map)
    end

    indent_break 1
    add_indented_line 1, line: "@Override"
    add_indented_line 1, line: "public boolean isFilled() {"

    attributes_to_check = non_optional_attribute_names
    attributes_to_check = attribute_names if non_optional_attribute_names.count == 0

    $output_string += "#{get_indention 2}return "

    attributes_to_check.each_with_index do |attribute_name, index|
      ending = '&&'
      ending = '' if index == attributes_to_check.count - 1
      add_indented_line 3, line: "#{attribute_name} != null #{ending}"
    end

    unless base.nil?
      ending = '&& '
      ending = '' if attributes_to_check.count == 0
      add_indented_line 3, line: "#{ending}super.isFilled()"
    end

    add_indented_line 2, line: ";"
    close_block 1
  end

  # Relations
  if relations_count > 0
    relations.each do |relation|
      indent_break 1
      
      add_indented_line 1, line: "@Relationship(\"#{relation['name']}\")"  
      
      name = get_camel_name relation['name']
      resource = get_resource relation['resource']
      type = resource['name'] + 'Resource'
      is_optional = false
      is_collection = relation['is_collection']
      is_string_map = false

      write_property_and_getter(name, type, is_optional, is_collection, is_string_map)
    end
  end

  close_block 0

  output_file_path = $output_resources_directory + '/' + output_resource_name + '.java'
  FileUtils.makedirs($output_resources_directory)
  output_file = File.open(output_file_path, 'w+')
  output_file.write($output_string)
  output_file.close
  
  $all_resource_names.push output_resource_name
### DONT CHANGE BELOW ###
end

$inner_configs.each do |name, info|
  ### DONT CHANGE ABOVE ###

  #------------------ Generator code ------------------#
  $output_string = "// Auto-generated code. Do not edit.\n\n"
  $output_string += "package #{$namespace}.inner;\n\n"

  inner_name = info['name']
  output_inner_name = inner_name

  add_line 'import java.math.*;'
  add_line 'import java.util.*;'
  add_line 'import com.fasterxml.jackson.annotation.*;'
  add_line 'import com.github.jasminb.jsonapi.annotations.*;'
  add_line 'import com.fasterxml.jackson.databind.*;'
  add_line 'import org.jetbrains.annotations.Nullable;'
  
  break_line

  puts "Render Inner: #{inner_name}"

  add_line "@JsonIgnoreProperties(ignoreUnknown = true)"
  add_line "public class #{output_inner_name} {"

  attributes = info['attributes']

  attributes_count = 0
  attributes_count = attributes.count unless attributes.nil?

  if attributes_count > 0
    attributes.each do |attribute|
      indent_break 1
      
      add_indented_line 1, line: "@JsonProperty(\"#{attribute['name']}\")"  
      
      name = get_camel_name attribute['name']
      type = get_attribute_type attribute
      is_optional = attribute['optional']
      is_collection = attribute['is_collection']
      is_string_map = attribute['is_string_map']

      write_property_and_getter(name, type, is_optional, is_collection, is_string_map)
    end
  end
  
  close_block 0

  output_file_path = $output_inner_directory + '/' + output_inner_name + '.java'
  FileUtils.makedirs($output_inner_directory)
  output_file = File.open(output_file_path, 'w+')
  output_file.write($output_string)
  output_file.close
### DONT CHANGE BELOW ###
end
### DONT CHANGE ABOVE ###

# AllResources 

$output_string = "// Auto-generated code. Do not edit.\n\n"
$output_string += "package #{$namespace}.resources;\n\n"

break_line

puts "Render AllResources"

add_line "public class AllResources {"

add_indented_line 1, line: "public static final Class<?>[] ARRAY = {"
$all_resource_names.each_with_index do |name, index|
  ending = ','
  ending = '' if index == $all_resource_names.count - 1
  add_indented_line 2, line: name + '.class' + ending
end
add_indented_line 1, line: "};"

close_block 0

output_file_path = $output_resources_directory + '/AllResources.java'
FileUtils.makedirs($output_resources_directory)
output_file = File.open(output_file_path, 'w+')
output_file.write($output_string)
output_file.close
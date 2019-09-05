# TokenD Kotlin SDK

This library facilitates interaction with TokenD-based system from Kotlin or Java applications including Android apps.

## Installation

For **Gradle** add following lines to your project's `build.gradle`:
```groovy
allprojects {
    repositories {
        ...
        jcenter()
        maven { url "https://maven.tokend.org" }
    }
}

dependencies {
    ...
    compile "org.tokend:sdk:2.8.1"
}

```

## Usage
```kotlin
const val TOKEND_URL = "https://api.testnet.tokend.org"

val api = TokenDApi(TOKEND_URL)
val keyServer = KeyServer(api.wallets)
```

### RxJava integration
If you would like to use RxJava in your project check out [Rx extensions for SDK](https://github.com/tokend/kotlin-sdk-rx-extensions).

### Android integration
For correct ProGuard minification add following lines to your project's `proguard-rules.pro`:
```proguard
# SDK
-dontwarn com.squareup.**
-dontwarn okio.**
-dontnote retrofit2.Platform
-dontwarn retrofit2.Platform$Java8
-dontwarn java.lang.**
-dontwarn javax.lang.**
-dontwarn javax.annotation.**
-keep class com.google.gson.** { *; }
-keepclassmembers enum * { *; }
-keepclassmembers class * { @com.github.jasminb.jsonapi.annotations.Id <fields>; }
-keep class com.fasterxml.jackson.databind.ObjectMapper {
    public <methods>;
    protected <methods>;
}
-keep class com.fasterxml.jackson.databind.ObjectWriter { public ** writeValueAsString(**); }
-keepnames class com.fasterxml.jackson.** { *; }
-dontwarn com.fasterxml.jackson.databind.**
-keep class * implements com.github.jasminb.jsonapi.ResourceIdHandler
-keep class sun.misc.Unsafe { *; }
-keep class org.codehaus.** { *; }
-keep class com.fasterxml.jackson.annotation.** { *; }
-keep @com.fasterxml.jackson.annotation.JsonIgnoreProperties class * { *; }
-keep @com.fasterxml.jackson.annotation.JsonCreator class * { *; }
-keep @com.fasterxml.jackson.annotation.JsonValue class * { *; }
-keep class com.fasterxml.** { *; }
-keepclassmembers public final enum com.fasterxml.jackson.annotation.JsonAutoDetect$Visibility {
    public static final com.fasterxml.jackson.annotation.JsonAutoDetect$Visibility *;
}

# General
-keepattributes SourceFile,LineNumberTable,*Annotation*,EnclosingMethod,Signature,Exceptions,InnerClasses

# Uncomment this if you would like to decode XDRs
#-keep class org.tokend.wallet.xdr.* { *; }
```

## Documentation
Visit our [Knowledge base](https://tokend.gitbook.io/knowledge-base/) and [API documentation](https://tokend.gitlab.io/docs) to get information on working with TokenD.

Also take a look at [Wiki](https://github.com/tokend/kotlin-sdk/wiki) to learn how to use SDK in your projects.

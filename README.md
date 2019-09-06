# TokenD Kotlin SDK

This library facilitates interaction with TokenD-based system from Kotlin or Java applications including Android apps.

## Installation

For **Gradle** add following lines to your project's `build.gradle`:
```groovy
allprojects {
    repositories {
        ...
        maven { url "https://maven.tokend.org" }
    }
}

dependencies {
    ...
    compile "org.tokend:sdk:2.8.2"
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
For correct ProGuard processing add following lines to your project's `proguard-rules.pro`:
```proguard
# General reflection
-keepattributes Signature, InnerClasses, EnclosingMethod
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations
-dontwarn javax.annotation.**

# Retrofit
-dontwarn org.codehaus.**
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# Jackson
-keep class com.fasterxml.** { *; }
-keep @com.fasterxml.jackson.annotation.** class * { *; }
-dontwarn com.fasterxml.jackson.databind.**

# JSONAPI
-keepclassmembers class * { @com.github.jasminb.jsonapi.annotations.Id <fields>; }
-keep class * implements com.github.jasminb.jsonapi.ResourceIdHandler

# Wallet
# Uncomment this if you would like to decode XDRs
#-keep class org.tokend.wallet.xdr.* { *; }
```

## Documentation
Visit our [Knowledge base](https://tokend.gitbook.io/knowledge-base/) and [API documentation](https://tokend.gitlab.io/docs) to get information on working with TokenD.

Also take a look at [Wiki](https://github.com/tokend/kotlin-sdk/wiki) to learn how to use SDK in your projects.

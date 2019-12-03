# TokenD Kotlin SDK

This library facilitates interaction with TokenD-based system from Kotlin or Java applications including Android apps.

## Installation

For **Gradle** add following lines to your project's `build.gradle`:
```groovy
allprojects {
    repositories {
        ...
        maven { url "https://maven.tokend.io" }
    }
}

dependencies {
    ...
    compile "org.tokend:sdk:3.3.2"
}

```

## Usage

### Setting up and making requests
```kotlin
const val TOKEND_URL = "https://api.testnet.tokend.org"

// Use TokenDApi facade members to access particular endpoints.
val api = TokenDApi(TOKEND_URL)
val assetsPage = api.v3.assets.get().execute().get()
val systemInfo = api.general.getSystemInfo().execute().get()

// Use KeyServer instance to work with key server.
val keyServer = KeyServer(api.wallets)
val walletInfo = keyServer.getWalletInfo(email, password).execute().get()
```

### Making custom requests
If there is an useful new endpoint which is not yet defined
in one of `TokenDApi` inner APIs you can work with it by making
custom requests from `TokenDApi.customRequests`:

```kotlin
val api = TokenDApi(TOKEND_URL)

// Use Class to get data with simple type.
val response = api
        .customRequests
        .get("/extra_endpoint", ResponseData::class.java)
        .execute()
        .get()

// Use Type obtained by TypeToken to get
// data with generic type.
val collectionType = object : TypeToken<List<ResponseData>>() {}.type
val collection = api
        .customRequests
        .get("/some_collection", collectionType)
        .execute()
        .get()
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
-keepclassmembers enum * { *; }

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
Visit our [Docs](https://docs.tokend.io/) to get information on working with TokenD.

Also take a look at [Wiki](https://github.com/tokend/kotlin-sdk/wiki) to learn how to use SDK in your projects.

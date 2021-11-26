# TokenD Kotlin SDK

This library facilitates interaction with TokenD-based system from Kotlin or Java applications including Android apps.

## Installation

For **Gradle** add following lines to your project's `build.gradle`:
```groovy
allprojects {
    repositories {
        /*...*/
        maven { url "https://maven.tokend.io" }
    }
}

dependencies {
    /*...*/
    implementation "org.tokend:sdk:4.0.0"
}

```

## Usage

### Setting up and making requests
```kotlin
const val TOKEND_URL = "https://api.testnet.tokend.org"

// Use TokenDApi facade members to access particular endpoints.
val api = TokenDApi(TOKEND_URL)
val assetsPage = api.v3.assets.get().execute().get()
val systemInfo = api.v3.info.getInfo().execute().get()

// Use KeyServer instance to work with key server.
val keyServer = KeyServer(api.wallets)
val wallet = keyServer.getWallet(email, password).execute().get()
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
TokenD SDK works fine on Android targeting JVM version 1.8.
For correct ProGuard processing check out the [rules from TokenD Android client](https://github.com/tokend/android-client/blob/master/app/proguard-rules.pro)

## Generate resources

Use TokenD mobile resourcegen Docker image to generate Java resources from
OpenAPI docs.

Create unit test in `org.tokend.sdk.dev` test package and use `DockerResourceGenerator`  
to generate resources directly from IDE.

Example of usage:
```kotlin
val generator = DockerResourceGenerator(
        tempDirectoryPath = "~/git/tokend/sdk/gen"
)
generator.generateResources(
        openapiSpecFilePath = "~/git/tokend/horizon/docs/build/openapi.yaml",
        namespace = "org.tokend.sdk.api.v3.model.generated",
        outputDirectoryPath = "~/git/tokend/sdk/src/main/java/org/tokend/sdk/api/generated",
        ignoredKeys = setOf()
)
```
For more information check out [Docker resource generator Readme](https://gitlab.com/tokend/mobile-openapi-resourcegen/-/blob/master/README.md)

## Documentation
Visit our [Docs](https://docs.tokend.io/) to get information on working with TokenD.

Also, take a look at [Wiki](https://github.com/tokend/kotlin-sdk/wiki/) to learn how to use SDK in your projects.

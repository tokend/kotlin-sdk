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
    compile "org.tokend:sdk:1.2.6"
}

```

## Usage
```kotlin
const val TOKEND_URL = "https://api.testnet.tokend.org"

val api = TokendApi(TOKEND_URL)
val keyStorage = KeyStorage(api.wallets)
```

If you would like to use RxJava in your project check out [Rx extensions for SDK](https://github.com/tokend/kotlin-sdk-rx-extensions).

## Documentation
Visit our [Knowledge base](https://tokend.gitbook.io/knowledge-base/) and [API documentation](https://tokend.gitlab.io/docs) to get information on working with TokenD.

Also take a look at [Wiki](https://github.com/tokend/kotlin-sdk/wiki) to learn how to use SDK in your projects.

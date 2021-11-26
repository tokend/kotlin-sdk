# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

Please check our [developers guide](https://gitlab.com/tokend/developers-guide)
for further information about branching and tagging conventions.

## [4.0.0] 2021-11-25

### Added
- Ability to customize wallet attributes and relationships
before saving it in `KeyServer.createAndSaveWallet` methods
using `walletCustomization` param
- Kotlin Jackson module (now can use `jacksonTypeRef` anywhere)

### Removed
- Gson! Use `JsonApiTools.objectMapper` and `@JsonProperties`, `@JsonIgnore` for serialization
- A lot of obsolete integration APIs
- Deprecated `general` API, use `v3.info` instead
- `EncryptedAccount`
- Serialization annotations from `Blob`, it is no more directly used in creation request

### Changed
- `WalletData` is no longer used for wallet creation
- `WalletSaveData` and `WalletCreationResult` are now used for wallet creation
- `KeyServer.getWalletInfo` and `KeyServer.getWalletData` are renamed to `getWallet`
- `WalletInfo` is replaced with `DecryptedWallet`
- `email` param in `KeyServer` methods and indirect models is renamed to `login`
- `currentWalletInfo` param in `KeyServer.updateWalletPassword` methods
is renamed to `currentWallet`

- `JsonApiToolsProvider` is renamed to `JsonApiTools`,
`getObjectMapper()` getter is now the `objectMapper` property
- `v3.general` is renamed to `v3.info`
- `api.generated` package is moved to `api.v3.model.generated`
- JVM target is set to 1.8
- Updated wallet module version to `3.7.0`

### Fixed
- Typo in wallet verification code serialized attribute

## [3.8.0] 2021-07-23

### Added
- `v3.general` API with system info method

### Removed
- `HashCodes` util. Use `arrayOf(x, y, z).contentHashCode()`
or `Objects.hash(x, y, z)` instead
- `ByteArray.hash()` extension. Use `Hashing.sha256()` instead
- Useless string encoding arguments in `Encoding` extensions
- Deprecated `transactions` API, use `v3.transactions` instead
- Deprecated `trades` API, use `v3.orderBooks` instead

### Changed
- `general` API is now deprecated, use `v3.general` instead

### Fixed
- Incorrect serialization annotations in `NameValue`

## [3.7.2] 2021-06-02

### Added
- Jackson serialization annotations for `RemoteFile`, `Policies`, `NameValue`

### Changed
- `TfaVerifier.Interface` is now really an interface, so mocked implementations
are possible now
- Handle `410 - Gone` HTTP error in `KeyServer` as incorrect password error

### Internal
- Removed Ruby resources generator files
- Fixed hardcoded language in Docker resource generator calls

## [3.7.1] 2021-01-29

### Added
- Ability to provide verification code during wallet creation to auto-verify email
- `JsonApiQueryMapBuilder` which is useful when constructing query maps for custom APIs
- Ability to specify original Account ID in `RequestSigner` (and so in `AccountRequestSigner`)
to use for envs that require `Account-Id` header
- `RemoteFile?.isReallyNullOrNullAccordingToTheJavascript` that allows finding out if it is really null or
"null according to the Javascript" (has everything empty)

### Changed
- `ApiDateUtil.tryParseDate` now throws error for unsupported formats instead
of current date return. See `ApiDateUtil.parseDateOrCurrent` for that
- `ApiDateUtil.tryParseDate` now throws specific error for empty strings
- `RemoteFile` fields are no more optional
- `RemoteFile.getUrl` can no more neither accept null `storageUrl` nor return null

### Fixed
- False-positive .hasValue for ApiResponse with Void or Unit fake value

### Internal
- Removed useless tests
- Fix not working tests on Java 11

## [3.7.0] 2020-12-25

### Added
- Ability to set any extra headers in `TokenDApi` constructor (down to `ServiceFactory`)

### Changed
- Updated Kotlin version to `1.4.10`
- Moved `SaleState` to `api.v3.sales.model`
- Moved `RequestState` to `api.v3.requests.model`
- Moved `getChart` methods to `ChartsApi` (`TokenDApi.charts`)

### Removed
- Deprecated sales API
- Deprecated reviewable requests API
- Deprecated key-value API
- Deprecated assets API
- Deprecated calls in blobs API
- Deprecated calls in accounts API
- Deprecated calls in documents API

### Internal
- Replace internal resourcegen with dockerized one

## [3.6.2] 2020-12-09

### Added
- KYC provider integration API

### Changed
- Make RFC 3339 a default format in ApiDateUtil
- Actualized booking and scheduler APIs

### Fixed
- Incorrect query params in scheduler API free-busy calls

## [3.6.1] 2020-10-09

### Added
- Return of `RemoteFile` from document uploading API calls
- Query name param to merged history page params
- Query map param to `getWalletInfo` and `getWalletData`
calls of `KeyServer`

### Changed
- Actualized invitations, invoices and booking integration APIs

### Internal 
- Add `float` to resource generator
- Make resource generator print errors and not report success if 
errors occurred

## [3.6.0] 2020-08-26

### Added 
- Ability to register any extra resource classes in `JsonApiToolsProvider`

### Changed
- Now KeyServer supports wallets with multiple secret seeds.
Backward compatibility is present.

### Fixed
- Deprecated `SslSocketFactory` setting up in `HttpClientFactory`
that caused errors on Java 9
- Double URL-decoding of `TokenDUri` query params
- Incorrect number-based pagination behavior in PagingParamsV3

## [3.5.0] 2020-07-15

### Added
- Recurring payments integration API
- Invoices integration API
- Bank cards integration API
- Friends list integration API
- Merged history integration API
- Invitations integration API
- Exchange integration API
- Ability to use locator integration API with bank cards
- Params, page model, `CustomRequestsApi` method and resource loader for pagination
 with multiple named cursors: `PagingParamsWithNamedCursors`, 
 `DataPageWithNamedCursors`, `SimpleNamedCursorsPagedResourceLoader`
- Ability to get `GsonBuilder` from `GsonFactory`

### Changed
- Updated wallet module version to `3.6.4`
- `KeyServer` wallet creation methods now return more info
- Actualized DNS integration API endpoints
- `SignInterceptor` is now public and allows 
auth headers creation for custom request data

### Removed
- `Date` header from request signing

### Internal
- Added resource generation related code to Git: 
 `ResourceGenerator`, `/generator`

## [3.4.0] 2020-03-11

### Added
- Streamers: using `PagedResourceStreamer` or
 `SimplePagedResourceStreamer` you can observe changes of paged resources.
 You can subscribe to movements, transactions or any other resource with
 chronological items order
 
## [3.3.5] 2020-03-10

### Added
- Ability to clone `ApiRequest`
- Ability to specify executor for `ApiCallback` in `BaseApi` and so 
in `TokenDApi`
- Ability to get custom request response as a `String` or `ByteArray`

## [3.3.4] 2020-02-19

### Added
- `CANCELLED` poll state
- `KYC_DATA` include for account endpoint

### Changed
- Actualized generated resources
- Updated Kotlin version to `1.3.31`

## [3.3.3] 2020-01-20

### Added
- Result meta XDR attribute to `SubmitTransactionResponse`
- Identity creation endpoint (`IdentitiesApi.create`)

### Changed
- Updated wallet module version to `3.6.3`
- Actualized booking integration API

## [3.3.2] 2019-12-03

### Added
- Booking integration APIs

### Changed
- Actualized marketplace integration API

## [3.3.1] 2019-11-18

### Changed
- Actualized company clients page query params
- Actualized `SaleResource`
- Base64 string extensions are now tolerant to missing paddings
- Actualized client redirect payload types

### Fixed
- Missing signer relation for wallet password recovery
- Invalid query params for client businesses request

## [3.3.0] 2019-11-05

### Added
- Account creation endpoint to `AccountsApi`
- Identity settings endpoints to `IdentitiesApi`
- Get operations endpoint to `HistoryApi`

### Changed
- Updated wallet module version to `3.6.0`
- Actualized assets API
- Actualized DNS integration API resources

## [3.2.0] 2019-10-24

### Added
- Ability to set custom signers while creating a wallet

### Changed
- Wallet creation methods in `KeyServer` now require a default
signer role value or a KeyValue API instance to obtain it
- Actualized marketplace integration API

## [3.1.0] 2019-10-16

### Added
- Get swap by ID method
- Ability to make custom requests,
 see Readme and `TokenDApi.customRequests`

### Changed
- Actualized marketplace integration API resources

### Fixed
- Naming mismatch in external system id resource file

## [3.0.0] 2019-10-04

### Changed
- We have updated the way of resources generation
so Horizon resources (`api/generated`) are now match
[OpenAPI specs](https://docs.tokend.io/horizon).
Some resources were renamed so code changes
may be required during the update.

## [2.9.0] 2019-09-24

### Added
- Locator integration API
- Swaps API

### Changed
- Actualized balances API query params
- Updated wallet module version to `3.5.0`

### Fixed
- Missing ProGuard rule for enums
- Invalid marketplace invoice type for crypto
- Undefined behavior in `ApiDateUtil` when using it from multiple threads

## [2.8.2] 2019-09-06

### Added
- `ApiRequest.executeAsync` methods accepting lambdas

### Changed
- Updated wallet module version to `3.4.2`
- Optimized ProGuard rules

## [2.8.1] 2019-09-05

### Added
- Ability to map `DataPage` items
- Initial marketplace integration API

### Changed
- Updated wallet module version to `3.4.1`

## [2.8.0] 2019-08-26

### Added
- Fiat integration API
- Payment proxy integration API

### Changed
- Actualized DNS integration API

### Removed
- Recovery seed concept – wallet creation methods in `KeyServer`
are affected

## [2.7.0] 2019-08-09

### Added
- Actual Blobs API methods
- Mass emails loading endpoint to `IdentitiesApi`
- Phone number settings endpoint to `IdentitiesApi`
- Transaction accept date to `SubmitTransactionResponse`
- Ledger states to `SystemInfo`
- `asset` filter to `ParticipantEffectsPageParams`
- `KeyServer.recoverWalletPassword` method which initiates KYC recovery
- `TfaFactor.Type.PHONE` for SMS 2FA

### Fixed
- Generated resources for atomic swap ask and bid

### Changed
- Actualized generated resources
- Actualized Documents API without interface change
- Increased HTTP timeout to 30 seconds (was 20)

### Removed
- `status` attribute of `ClientRedirectPayload`

### Deprecated
- `DocumentsApi.getUrl` method with `accountId` param
- `-AccountOwnedBlob` methods in `BlobsApi`

## [2.6.0] 2019-07-15

### Added
- Ability to add extra JSONAPI resources to `JsonApiToolsProvider`
with `addExtraResources` method
- Experimental integrations APIs
- Endpoint for account sales
- Method to get account's reviewable request by ID
- V3 transactions API

### Fixed
- `DataPage` will be last if it can't parse next link
(avoid infinite pagination)

### Changed
- Actualized generated resources

### Removed
- Deprecated account signers endpoint
- Deprecated favorites API

## [2.5.0] 2019-06-24

### Added
- Polls and votes endpoints
- Atomic swaps API v3

### Fixed
- Fail on blank links while parsing `DataPage`

### Changed
- Updated wallet module version to `3.2.0`
- Minimal supported Horizon version is now `3.5.0`

## [2.4.0] 2019-05-31

### Added
- Get converted balances method

### Changed
- Actualized generated resources
- Made `KeyServer.saveWallet` method return `WalletData` of the saved wallet
- Updated wallet module version to `3.1.0`
- Minimal supported Horizon version is now `3.4.0`


## [2.3.0] 2019-04-29

### Added
- New order book get method to `OrderBooksApi`
- Movements get method to `HistoryApi`

### Deprecated
- Get order book by Long ID method in `OrderBooksApi`

## [2.2.2] 2019-04-26

### Fixed
- Key-value entry value serialization

### Added
- Integration tests for documents and blobs upload

## [2.2.1] 2019-04-04

### Fixed
- Fatal exception on 403 HTTP errors with empty body

## [2.2.0] 2019-03-29

### Added
- `op_no_entry` error code to `TransactionFailedException`

### Fixed
- Missing paging params for key-value page get method in API v3
- Missing fields in `MatchedOrder` model

## [2.1.0] 2019-03-07

### Added
- All TLS suites support in in HTTP client
- Extensions with typed getters for details of 
`ReviewableRequestResource` (`.getTypedRequestDetails`)
 and `OperationResource` (`.getTypedDetails`)

### Fixed
- Actualized documents API
- Actualized reviewable requests API V3

## [2.0.0] 2019-02-28

### Added
- `v3` APIs
- Complete update password method to the `KeyServer`
- Ability to submit `Transaction` itself without conversion to `Base64`
- `SaleState` enum
- `BlobType` enum
- `.bitmask` extension for `Int` collections

### Changed
- Wallet library version to `3.0.1`
- Minimal supported Horizon version to `3.0.1`
- Minimal supported API version to `3.0.0`
- Params required for password change/recovery 
- ProGuard rules

### Deprecated
- APIs replaced by `v3` and related entities

### Removed
- `forceContentType` param of `TokenDApi`, now it's `true` by default
- `accessTypes` from `AuthRequest`

[2.0.0]: https://github.com/tokend/kotlin-sdk/compare/1.2.18...2.0.0
[2.1.0]: https://github.com/tokend/kotlin-sdk/compare/2.0.0...2.1.0
[2.2.0]: https://github.com/tokend/kotlin-sdk/compare/2.1.0...2.2.0
[2.2.1]: https://github.com/tokend/kotlin-sdk/compare/2.2.0...2.2.1
[2.2.2]: https://github.com/tokend/kotlin-sdk/compare/2.2.1...2.2.2
[2.3.0]: https://github.com/tokend/kotlin-sdk/compare/2.2.2...2.3.0
[2.4.0]: https://github.com/tokend/kotlin-sdk/compare/2.3.0...2.4.0
[2.5.0]: https://github.com/tokend/kotlin-sdk/compare/2.4.0...2.5.0
[2.6.0]: https://github.com/tokend/kotlin-sdk/compare/2.5.0...2.6.0
[2.7.0]: https://github.com/tokend/kotlin-sdk/compare/2.6.0...2.7.0
[2.8.0]: https://github.com/tokend/kotlin-sdk/compare/2.7.0...2.8.0
[2.8.1]: https://github.com/tokend/kotlin-sdk/compare/2.8.0...2.8.1
[2.8.2]: https://github.com/tokend/kotlin-sdk/compare/2.8.1...2.8.2
[2.9.0]: https://github.com/tokend/kotlin-sdk/compare/2.8.2...2.9.0
[3.0.0]: https://github.com/tokend/kotlin-sdk/compare/2.9.0...3.0.0
[3.1.0]: https://github.com/tokend/kotlin-sdk/compare/3.0.0...3.1.0
[3.2.0]: https://github.com/tokend/kotlin-sdk/compare/3.1.0...3.2.0
[3.3.0]: https://github.com/tokend/kotlin-sdk/compare/3.2.0...3.3.0
[3.3.1]: https://github.com/tokend/kotlin-sdk/compare/3.3.0...3.3.1
[3.3.2]: https://github.com/tokend/kotlin-sdk/compare/3.3.1...3.3.2
[3.3.3]: https://github.com/tokend/kotlin-sdk/compare/3.3.2...3.3.3
[3.3.4]: https://github.com/tokend/kotlin-sdk/compare/3.3.3...3.3.4
[3.3.5]: https://github.com/tokend/kotlin-sdk/compare/3.3.4...3.3.5
[3.4.0]: https://github.com/tokend/kotlin-sdk/compare/3.3.5...3.4.0
[3.5.0]: https://github.com/tokend/kotlin-sdk/compare/3.4.0...3.5.0
[3.6.0]: https://github.com/tokend/kotlin-sdk/compare/3.5.0...3.6.0
[3.6.1]: https://github.com/tokend/kotlin-sdk/compare/3.6.0...3.6.1
[3.6.2]: https://github.com/tokend/kotlin-sdk/compare/3.6.1...3.6.2
[3.7.0]: https://github.com/tokend/kotlin-sdk/compare/3.6.2...3.7.0
[3.7.1]: https://github.com/tokend/kotlin-sdk/compare/3.7.0...3.7.1
[3.7.2]: https://github.com/tokend/kotlin-sdk/compare/3.7.1...3.7.2
[3.8.0]: https://github.com/tokend/kotlin-sdk/compare/3.7.2...3.8.0
[4.0.0]: https://github.com/tokend/kotlin-sdk/compare/3.8.0...4.0.0
[Unreleased]: https://github.com/tokend/kotlin-sdk/compare/4.0.0...HEAD

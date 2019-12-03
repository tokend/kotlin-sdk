# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

Please check our [developers guide](https://gitlab.com/tokend/developers-guide)
for further information about branching and tagging conventions.

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
[Unreleased]: https://github.com/tokend/kotlin-sdk/compare/3.3.2...HEAD
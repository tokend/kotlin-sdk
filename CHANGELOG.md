# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

Please check our [developers guide](https://gitlab.com/tokend/developers-guide)
for further information about branching and tagging conventions.

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
[Unreleased]: https://github.com/tokend/kotlin-sdk/compare/2.8.0...HEAD
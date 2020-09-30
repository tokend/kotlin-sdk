package org.tokend.sdk.api.wallets

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.SimpleRetrofitApiRequest
import org.tokend.sdk.api.base.model.AttributesEntity
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.api.wallets.model.*
import org.tokend.sdk.keyserver.models.LoginParams
import org.tokend.sdk.keyserver.models.WalletData
import org.tokend.sdk.redirects.ClientRedirectPayload
import org.tokend.sdk.redirects.ClientRedirectType
import retrofit2.HttpException
import java.net.HttpURLConnection

open class WalletsApi(
        protected val walletsService: WalletsService
) {

    /**
     * Will return specific wallet by given id.
     * @see <a href="https://tokend.gitlab.io/docs/?http#get-wallet">Docs</a>
     */
    @JvmOverloads
    open fun getById(walletId: String,
                     queryMap: Map<String, Any>? = null): ApiRequest<WalletData> {
        return MappedRetrofitApiRequest(
                walletsService.getById(walletId, queryMap),
                { it.data }
        ) { error ->
            if (error is HttpException) {
                when (error.code()) {
                    HttpURLConnection.HTTP_FORBIDDEN -> throw EmailNotVerifiedException(walletId)
                    HttpURLConnection.HTTP_NOT_FOUND -> throw InvalidCredentialsException(
                            InvalidCredentialsException.Credential.PASSWORD
                    )
                    else -> error
                }
            } else {
                error
            }
        }
    }

    /**
     * Will create new wallet.
     * @see <a href="https://tokend.gitlab.io/docs/?http#create-wallet">Docs</a>
     */
    open fun create(data: WalletData): ApiRequest<WalletData> {
        return MappedRetrofitApiRequest(
                walletsService.create(
                        WalletResourceBody(data)
                ),
                DataEntity<WalletData>::data
        ) { error ->
            if (error is HttpException &&
                    error.code() == HttpURLConnection.HTTP_CONFLICT)
                EmailAlreadyTakenException()
            else
                error
        }
    }

    /**
     * Request is similar to wallet [create] but also contains additional transaction resource
     * used to update account signers.
     * @see <a href="https://tokend.gitlab.io/docs/?http#update-wallet">Docs</a>
     */
    open fun update(walletId: String,
                    data: WalletData): ApiRequest<Void> {
        return SimpleRetrofitApiRequest(
                walletsService.update(
                        walletId,
                        WalletResourceBody(data)
                )
        )
    }

    /**
     * Verifies wallet with given ID.
     * @see <a href="https://tokend.gitlab.io/docs/?http#wallet-verification">Docs</a>
     */
    open fun verify(walletId: String,
                    token: String): ApiRequest<Void> {
        return SimpleRetrofitApiRequest(
                walletsService.verify(
                        walletId,
                        DataEntity(
                                AttributesEntity(
                                        VerifyWalletRequestBody(token)
                                )
                        )
                )
        )
    }

    /**
     * Verifies wallet by given redirect payload.
     * @see verify(walletId, token)
     */
    open fun verify(redirectPayload: ClientRedirectPayload): ApiRequest<Void> {
        if (redirectPayload.type != ClientRedirectType.EMAIL_VERIFICATION) {
            throw IllegalArgumentException("Invalid redirect payload")
        }

        val walletId = redirectPayload.meta.get(VERIFICATION_META_WALLET_ID)?.asString
                ?: throw IllegalArgumentException("Missing '$VERIFICATION_META_WALLET_ID' in meta data")

        val token = redirectPayload.meta.get(VERIFICATION_META_TOKEN)?.asString
                ?: throw IllegalArgumentException("Missing '$VERIFICATION_META_TOKEN' in meta data")

        return verify(walletId, token)
    }

    /**
     * Will requesting verification resend.
     * @see <a href="https://tokend.gitlab.io/docs/?http#requesting-verification-resend">Docs</a>
     */
    open fun requestVerification(walletId: String): ApiRequest<Void> {
        return SimpleRetrofitApiRequest(
                walletsService.requestVerification(walletId)
        )
    }

    /**
     * Will return current default derivation parameters or parameters used to derive specific wallet.
     * @see <a href="https://tokend.gitlab.io/docs/?http#get-kdf-params">Docs</a>
     */
    open fun getLoginParams(email: String?,
                            isRecovery: Boolean): ApiRequest<LoginParams> {
        return MappedRetrofitApiRequest(
                walletsService.getLoginParams(email, isRecovery),
                { it.data }
        ) { error ->
            return@MappedRetrofitApiRequest if (error is HttpException
                    && error.code() == HttpURLConnection.HTTP_NOT_FOUND)
                InvalidCredentialsException(InvalidCredentialsException.Credential.EMAIL)
            else
                error
        }
    }

    companion object {
        private const val VERIFICATION_META_WALLET_ID = "wallet_id"
        private const val VERIFICATION_META_TOKEN = "token"
    }
}
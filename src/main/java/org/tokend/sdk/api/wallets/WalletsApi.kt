package org.tokend.sdk.api.wallets

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.SimpleRetrofitApiRequest
import org.tokend.sdk.api.base.model.AttributesEntity
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.api.wallets.model.EmailAlreadyTakenException
import org.tokend.sdk.api.wallets.model.EmailNotVerifiedException
import org.tokend.sdk.api.wallets.model.InvalidCredentialsException
import org.tokend.sdk.api.wallets.model.VerifyWalletRequestBody
import org.tokend.sdk.keyserver.models.LoginParams
import org.tokend.sdk.keyserver.models.WalletData
import retrofit2.HttpException
import java.net.HttpURLConnection

open class WalletsApi(
        protected val walletsService: WalletsService
) {

    /**
     * Will return specific wallet by given id.
     * @see <a href="https://tokend.gitlab.io/docs/?http#get-wallet">Docs</a>
     */
    open fun getById(walletId: String): ApiRequest<WalletData> {
        return MappedRetrofitApiRequest(
                walletsService.getById(walletId),
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
    open fun create(data: WalletData): ApiRequest<Void> {
        return SimpleRetrofitApiRequest(
                walletsService.create(
                        DataEntity(data)
                )
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
                        DataEntity(data)
                )
        )
    }

    /**
     * Once wallet is created and verified is false user should receive email
     * with verification link with client router payload.
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
}
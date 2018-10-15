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

    open fun update(walletId: String,
                    data: WalletData): ApiRequest<Void> {
        return SimpleRetrofitApiRequest(
                walletsService.update(
                        walletId,
                        DataEntity(data)
                )
        )
    }

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

    open fun requestVerification(walletId: String): ApiRequest<Void> {
        return SimpleRetrofitApiRequest(
                walletsService.requestVerification(walletId)
        )
    }

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
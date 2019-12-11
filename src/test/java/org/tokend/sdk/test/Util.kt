package org.tokend.sdk.test

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.TokenDApi
import org.tokend.sdk.signing.AccountRequestSigner
import org.tokend.sdk.tfa.TfaCallback
import org.tokend.wallet.Account
import kotlin.reflect.KVisibility
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.javaField

object Util {
    class NullabilityViolationException(propertyName: String, className: String?) :
            IllegalStateException("Property '$propertyName' " +
                    "of $className can't be null")

    fun getApi(url: String = Config.API_URL,
               tfaCallback: TfaCallback? = null): TokenDApi {
        return TokenDApi(url, tfaCallback = tfaCallback)
    }

    fun getSignedApi(account: Account = Config.ADMIN_ACCOUNT,
                     accountId: String = account.accountId,
                     url: String = Config.API_URL,
                     tfaCallback: TfaCallback? = null): TokenDApi {
        return TokenDApi(url, AccountRequestSigner(accountId, account), tfaCallback)
    }

    /**
     * Goes over properties with @SerializedName annotation
     * and checks if they violate nullability.
     * Checks nested objects and collections.
     *
     * @throws NullabilityViolationException if found null property which can't be null
     */
    fun checkNullabilityViolations(entity: Any) {
        val properties = try {
            entity::class.memberProperties
        } catch (e: Error) {
            return
        }

        properties
                .filter { property ->
                    val a = property.javaField?.getAnnotation(SerializedName::class.java)
                    a != null
                }
                .forEach { property ->
                    if (property.visibility != KVisibility.PUBLIC) {
                        return@forEach
                    }

                    val value = property.getter.call(entity)
                    if (value == null && !property.returnType.isMarkedNullable) {
                        throw NullabilityViolationException(property.name, entity::class.qualifiedName)
                    } else if (value != null) {
                        if (value is Collection<*>) {
                            value
                                    .filterNotNull()
                                    .forEach {
                                        checkNullabilityViolations(it)
                                    }
                        } else {
                            checkNullabilityViolations(value)
                        }
                    }
                }
    }

    /**
     * @see checkNullabilityViolations
     */
    fun checkNullabilityViolations(entities: Collection<Any>) {
        entities.forEach {
            checkNullabilityViolations(it)
        }
    }
}
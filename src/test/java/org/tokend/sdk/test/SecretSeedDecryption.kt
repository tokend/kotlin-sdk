package org.tokend.sdk.test

import org.junit.Assert
import org.junit.Test
import org.tokend.crypto.cipher.Aes256GCM
import org.tokend.sdk.keyserver.WalletEncryption
import org.tokend.sdk.keyserver.models.KeychainData
import org.tokend.sdk.utils.extentions.encodeBase64
import org.tokend.sdk.utils.extentions.encodeBase64String
import org.tokend.wallet.Account
import org.tokend.wallet.Base32Check
import java.security.SecureRandom

class SecretSeedDecryption {

    @Test
    fun seedsArray(){
        val json = """{"seeds":["SDS4XA3MS37Q2JO7C2LSNHXBV7PZLDBBNWQW7OQXE62T7ATLU5GUFDY2"]}"""
        val iv = SecureRandom.getSeed(8)
        val key = SecureRandom.getSeed(32)
        val encrypted = Aes256GCM(iv).encrypt(json.toByteArray(), key)
        val keychainData = KeychainData.fromRaw(iv, encrypted)
        val seed = WalletEncryption.decryptSecretSeed(keychainData, key).joinToString("")
        Assert.assertEquals("SDS4XA3MS37Q2JO7C2LSNHXBV7PZLDBBNWQW7OQXE62T7ATLU5GUFDY2", seed)
    }


    @Test
    fun singleSeed(){
        val seed = Account.random().secretSeed!!
        val iv = SecureRandom.getSeed(8)
        val key = SecureRandom.getSeed(32)
        val keychainData = WalletEncryption.encryptSecretSeed(seed, iv, key)
        val decryptedSeed = WalletEncryption.decryptSecretSeed(keychainData, key)
        Assert.assertArrayEquals(seed, decryptedSeed)
    }
}
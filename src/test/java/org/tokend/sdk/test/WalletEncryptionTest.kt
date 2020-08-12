package org.tokend.sdk.test

import org.junit.Assert
import org.junit.Test
import org.tokend.crypto.cipher.Aes256GCM
import org.tokend.sdk.keyserver.WalletEncryption
import org.tokend.sdk.keyserver.models.KeychainData
import java.security.SecureRandom

class WalletEncryptionTest {
    @Test
    fun encryptSingleSeed() {
        val seed = "SA4HT5YMPW37JBXSRMKYGAI6RPTRTPSVT4A6PB4C6YNQEW5NV2I6JODU".toCharArray()
        val iv = SecureRandom.getSeed(8)
        val key = SecureRandom.getSeed(32)
        val encrypted = WalletEncryption.encryptSecretSeed(seed, iv, key)
        val decrypted = String(Aes256GCM(iv).decrypt(encrypted.cipherText, key))
        Assert.assertEquals(
                "{\"seed\":\"SA4HT5YMPW37JBXSRMKYGAI6RPTRTPSVT4A6PB4C6YNQEW5NV2I6JODU\",\"seeds\":[\"SA4HT5YMPW37JBXSRMKYGAI6RPTRTPSVT4A6PB4C6YNQEW5NV2I6JODU\"]}",
                decrypted
        )
    }

    @Test
    fun encryptMultipleSeeds() {
        val seeds = listOf(
                "SD5GSVG5CJZWPWQ2HGY2KZTCABXY4AHG4PEHWCXCPEBJYXZB7ZPUKKNM".toCharArray(),
                "SDZ6HECTO4UX7JXEISB7NIVD7AUALKKRXBPVDJ7KC64JZC4IOVUOEK6A".toCharArray()
        )
        val iv = SecureRandom.getSeed(8)
        val key = SecureRandom.getSeed(32)
        val encrypted = WalletEncryption.encryptSecretSeeds(seeds, iv, key)
        val decrypted = String(Aes256GCM(iv).decrypt(encrypted.cipherText, key))
        Assert.assertEquals(
                "{\"seed\":\"SD5GSVG5CJZWPWQ2HGY2KZTCABXY4AHG4PEHWCXCPEBJYXZB7ZPUKKNM\",\"seeds\":[\"SD5GSVG5CJZWPWQ2HGY2KZTCABXY4AHG4PEHWCXCPEBJYXZB7ZPUKKNM\",\"SDZ6HECTO4UX7JXEISB7NIVD7AUALKKRXBPVDJ7KC64JZC4IOVUOEK6A\"]}",
                decrypted
        )
    }

    @Test
    fun decryptLegacy() {
        val content = "{\"seed\":\"SA4HT5YMPW37JBXSRMKYGAI6RPTRTPSVT4A6PB4C6YNQEW5NV2I6JODU\"}"
        val iv = SecureRandom.getSeed(8)
        val key = SecureRandom.getSeed(32)
        val encrypted = Aes256GCM(iv).encrypt(content.toByteArray(), key)
        val keychainData = KeychainData.fromRaw(iv, encrypted)
        val decryptedSeed = WalletEncryption.decryptSecretSeed(keychainData, key)
        Assert.assertEquals(
                "SA4HT5YMPW37JBXSRMKYGAI6RPTRTPSVT4A6PB4C6YNQEW5NV2I6JODU",
                decryptedSeed.joinToString("")
        )
    }

    @Test
    fun decryptMultipleSeeds() {
        val content = "{\"seeds\":[\"SD5GSVG5CJZWPWQ2HGY2KZTCABXY4AHG4PEHWCXCPEBJYXZB7ZPUKKNM\",\"SDZ6HECTO4UX7JXEISB7NIVD7AUALKKRXBPVDJ7KC64JZC4IOVUOEK6A\"]}"
        val iv = SecureRandom.getSeed(8)
        val key = SecureRandom.getSeed(32)
        val encrypted = Aes256GCM(iv).encrypt(content.toByteArray(), key)
        val keychainData = KeychainData.fromRaw(iv, encrypted)
        val decryptedSeeds = WalletEncryption.decryptSecretSeeds(keychainData, key)
        Assert.assertEquals(
                "SD5GSVG5CJZWPWQ2HGY2KZTCABXY4AHG4PEHWCXCPEBJYXZB7ZPUKKNM",
                decryptedSeeds[0].joinToString("")
        )
        Assert.assertEquals(
                "SDZ6HECTO4UX7JXEISB7NIVD7AUALKKRXBPVDJ7KC64JZC4IOVUOEK6A",
                decryptedSeeds[1].joinToString("")
        )
    }

    @Test
    fun decryptLegacyPlusMultipleSeeds() {
        val content = "{\"seed\":\"SD5GSVG5CJZWPWQ2HGY2KZTCABXY4AHG4PEHWCXCPEBJYXZB7ZPUKKNM\",\"seeds\":[\"SD5GSVG5CJZWPWQ2HGY2KZTCABXY4AHG4PEHWCXCPEBJYXZB7ZPUKKNM\",\"SDZ6HECTO4UX7JXEISB7NIVD7AUALKKRXBPVDJ7KC64JZC4IOVUOEK6A\"]}"
        val iv = SecureRandom.getSeed(8)
        val key = SecureRandom.getSeed(32)
        val encrypted = Aes256GCM(iv).encrypt(content.toByteArray(), key)
        val keychainData = KeychainData.fromRaw(iv, encrypted)
        val decryptedSeeds = WalletEncryption.decryptSecretSeeds(keychainData, key)
        Assert.assertEquals(
                "SD5GSVG5CJZWPWQ2HGY2KZTCABXY4AHG4PEHWCXCPEBJYXZB7ZPUKKNM",
                decryptedSeeds[0].joinToString("")
        )
        Assert.assertEquals(
                "SDZ6HECTO4UX7JXEISB7NIVD7AUALKKRXBPVDJ7KC64JZC4IOVUOEK6A",
                decryptedSeeds[1].joinToString("")
        )
    }

    @Test
    fun decryptMalformed() {
        val content = "{\"nothing\":\"here\"\"]}"
        val iv = SecureRandom.getSeed(8)
        val key = SecureRandom.getSeed(32)
        val encrypted = Aes256GCM(iv).encrypt(content.toByteArray(), key)
        val keychainData = KeychainData.fromRaw(iv, encrypted)
        try {
            WalletEncryption.decryptSecretSeeds(keychainData, key)
        } catch (e: IllegalStateException) {
            // Ok
            return
        } catch (e: Exception) {
            Assert.fail("Must throw IllegalStateException but $e is thrown")
        }
        Assert.fail("Must throw IllegalStateException but nothing is thrown")
    }
}
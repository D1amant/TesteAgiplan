package com.example.luisfernandorodrigues.testeagibank.helper

import java.math.BigInteger
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Date
import javax.inject.Inject

class HashGenerate @Inject constructor() {

    val ts: String by lazy {
        SimpleDateFormat(PATTERN, Locale.getDefault())
                .format(Date())
                .replace(".", "")
    }

    fun getHash(ts: String, privateKey: String, apiKey: String): String {
        val x = ts + privateKey + apiKey
        val d: java.security.MessageDigest? = java.security.MessageDigest.getInstance(ALGORITHM)
        d?.reset()
        d?.update(x.toByteArray())

        val mdbytes = d?.digest()
        return BigInteger(SIGNUM, mdbytes).toString(RADIX)
    }

    companion object {
        private const val PATTERN = "yyyy.MM.dd.HH.mm.ss"
        private const val RADIX = 16
        private const val SIGNUM = 1
        private const val ALGORITHM = "MD5"
    }
}
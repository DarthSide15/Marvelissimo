package com.darthside.marvelissimo.main_files

import android.util.Log
import java.security.MessageDigest

class Helper {

    private fun authenticateUrl() {
        val ts = "1"
        val publicKey = "174943a97b8c08a00a80d1ed425d9ed1"
        val privateKey = "0033be867dc3fdb7df59babb98fa5f55b2c7dbd8"

        val hexString = StringBuilder("")
        val md5 = MessageDigest.getInstance("MD5")
        md5.digest("$ts$privateKey$publicKey".toByteArray()).forEach {
            hexString.append(String.format("%02x", it))
        }
        val hash = hexString.toString()

        Log.d("", "Hash generated: $hash")
        Log.d("", "Timestamp set to $ts")
        Log.d("", "Public key is $publicKey")
    }
}
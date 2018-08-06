package com.example.luisfernandorodrigues.testeagiplan.Helper

import java.math.BigInteger
import java.text.SimpleDateFormat
import java.util.*


class HashGenerate{

    val ts : String ? = SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(Date()).replace("." , "")

     fun getHash (ts : String , privatekey : String , apikey : String ) : String? {
         val x = ts+privatekey+apikey
         var d: java.security.MessageDigest? = java.security.MessageDigest.getInstance("MD5")
         d!!.reset()
         d.update(x.toByteArray())


         val mdbytes = d.digest()
         //convert the byte to hex format method 1



         return BigInteger(1,mdbytes).toString(16)

    }

}
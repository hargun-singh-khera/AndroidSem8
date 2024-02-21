package com.example.myapplication

class UserDetail {
    lateinit var name: String
    lateinit var phoneNum: String
    lateinit var address: String

    @JvmName("getName1")
    fun getName():String {
        return name
    }
    @JvmName("getPhone1")
    fun getPhoneNum():String {
        return phoneNum
    }
    @JvmName("getAddress1")
    fun getAddress():String {
        return address
    }

    @JvmName("setName1")
    fun setName(n: String) {
        name = n
    }
    @JvmName("setAddress1")
    fun setAddress(a: String) {
        address = a
    }
    @JvmName("setPhone1")
    fun setPhn(ph:String) {
        phoneNum = ph
    }

}
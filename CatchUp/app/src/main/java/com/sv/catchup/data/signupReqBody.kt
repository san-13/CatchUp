package com.sv.catchup.data

data class signupReqBody(
    var username:String?=null,
    var email:String?=null,
    var password:String?=null,
    var phoneNumber:String?=null
)
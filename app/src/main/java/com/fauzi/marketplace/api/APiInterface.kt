package com.fauzi.marketplace.api

import android.telecom.Call
import com.fauzi.marketplace.model.DefaultResponse
import com.fauzi.marketplace.model.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface APiInterface {
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email:String,
        @Field("password") password:String
    ): retrofit2.Call<LoginResponse>

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name") name:String ,
        @Field("email") email: String ,
        @Field("password") password: String ,
        @Field("password_confirmation") password_confirmation:String
    ): retrofit2.Call<DefaultResponse>
}
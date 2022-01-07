package com.example.mwsprak6.api

import com.example.mwsprak6.api.response.StatusResponse
import com.example.mwsprak6.api.response.WebinarResponse


import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("read.php")
    fun getWebinar(): Call<WebinarResponse>

    @FormUrlEncoded
    @POST("create.php")
    fun insertWebinar(
        @Field("nim") nim: String,
        @Field("nama") nama: String,
        @Field("jenis_kelamin") jenisKelamin: String,
        @Field("prodi") prodi: String,
    ): Call<StatusResponse>

    @FormUrlEncoded
    @POST("update.php")
    fun updateWebinar(
        @Field("nim") nim: String,
        @Field("nama") nama: String,
        @Field("jenis_kelamin") jenisKelamin: String,
        @Field("prodi") prodi: String,
    ): Call<StatusResponse>

    @FormUrlEncoded
    @POST("delete.php")
    fun deleteData(
        @Field("nim") nim: String
    ): Call<StatusResponse>
}
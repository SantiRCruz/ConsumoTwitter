package com.santiago.twitterapplication.interfaces

import com.santiago.twitterapplication.models.cruduser.CrudUser
import com.santiago.twitterapplication.models.listuser.Data
import com.santiago.twitterapplication.models.listuser.ListUser
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("users")
    fun getListUsers(@Query("page") page:Int ): Call<ListUser>

    @GET("users/{id}")
    fun getListUser(@Path("id") id:Int): Call<Data>

    @POST("users")
    fun create(@Body crudUser: CrudUser): Call<CrudUser>

    @PUT("users/{id}")
    fun update(@Path("id")id:Int, @Body crudUser: CrudUser): Call<CrudUser>

    @DELETE("users/{id}")
    fun delete(@Path("id")id:Int,): Call<CrudUser>
}
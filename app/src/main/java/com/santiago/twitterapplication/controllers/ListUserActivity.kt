package com.santiago.twitterapplication.controllers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.santiago.twitterapplication.R
import com.santiago.twitterapplication.interfaces.ApiService
import com.santiago.twitterapplication.models.listuser.Data
import kotlinx.android.synthetic.main.activity_list_user.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListUserActivity : AppCompatActivity() {
    val apiUrl = "https://reqres.in/api/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_user)
        
        retrofitListUser()
    }
    private fun retrofitListUser() {
        val bundle  =  intent.extras
        var id = bundle?.get("id")
        val retrofit = Retrofit.Builder()
            .baseUrl(apiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        
        val serviceUser = retrofit.create(ApiService::class.java)
        serviceUser.getListUser(id.toString().toInt()).enqueue(object : Callback<Data>{
            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                var arrayAdapter = ArrayAdapter<Data>(applicationContext,android.R.layout.simple_list_item_1,
                    arrayOf(response.body()))
                listViewListUser.adapter = arrayAdapter
            }

            override fun onFailure(call: Call<Data>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

}
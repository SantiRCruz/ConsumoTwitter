package com.santiago.twitterapplication.controllers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.santiago.twitterapplication.R
import com.santiago.twitterapplication.interfaces.ApiService
import com.santiago.twitterapplication.models.listuser.Data
import com.santiago.twitterapplication.models.listuser.ListUser
import kotlinx.android.synthetic.main.activity_list_users.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListUsersActivity : AppCompatActivity() {
    val apiUrl = "https://reqres.in/api/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_users)

        retrofitListarUsuarios()

    }
    private fun retrofitListarUsuarios() {
        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(apiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create<ApiService>(ApiService::class.java)
        service.getListUsers(2).enqueue(object : Callback<ListUser> {
            override fun onResponse(
                call: Call<ListUser>,
                response: Response<ListUser>
            ) {
                Log.e("lista>>>>>>>>>>>" , response.body().toString())

                var arrayAdapter = ArrayAdapter<Data>(applicationContext,android.R.layout.simple_list_item_1,response.body()!!.data)

                listViewListUsers.adapter = arrayAdapter
            }

            override fun onFailure(call: Call<ListUser>, t: Throwable) {
                Log.e("ERROR>>>>>>",t.toString())
            }

        })
    }
}
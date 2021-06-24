package com.santiago.twitterapplication.controllers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.santiago.twitterapplication.CreateActivity
import com.santiago.twitterapplication.R
import com.santiago.twitterapplication.UpdateActivity
import com.santiago.twitterapplication.interfaces.ApiService
import com.santiago.twitterapplication.models.cruduser.CrudUser
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    val apiUrl = "https://reqres.in/api/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        passScreen()
        retrofitDelete()
    }

    private fun retrofitDelete() {
        buttonDelete.setOnClickListener {
            val retrofit =  Retrofit.Builder()
                .baseUrl(apiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val serviceDelete = retrofit.create(ApiService::class.java)
            serviceDelete.delete(editTextIdDelete.text.toString().toInt()).enqueue(object : Callback<CrudUser>{
                override fun onResponse(call: Call<CrudUser>, response: Response<CrudUser>) {
                    Log.e("DELETE>>>",response.body().toString())
                    Log.e("DELETE>>>",response.toString())
                    if (response.code()==204){
                        Toast.makeText(applicationContext, "THE USER WAS DELETE SUCCESSFULLY", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(applicationContext, "THE USER WAS NOT DELETE SUCCESSFULLY", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<CrudUser>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }
    }

    private fun passScreen() {
        buttonListUsers.setOnClickListener {
            var intent = Intent(this, ListUsersActivity::class.java)
            startActivity(intent)
        }
        buttonListUser.setOnClickListener {
            var intent = Intent(this, ListUserActivity::class.java)
            intent.putExtra("id", editTextNumberUser.text)
            if (editTextNumberUser.text.isNotEmpty()) {
                startActivity(intent)
            } else {
                Toast.makeText(
                    this,
                    "You do not pass a ( id ) for search your user",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        buttonPassCreate.setOnClickListener {
            var intent = Intent(this, CreateActivity::class.java)
            startActivity(intent)
        }
        buttonPassUpdate.setOnClickListener {
            var intent = Intent(this, UpdateActivity::class.java)
            startActivity(intent)

        }
    }
}
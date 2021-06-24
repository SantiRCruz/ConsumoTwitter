package com.santiago.twitterapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.santiago.twitterapplication.interfaces.ApiService
import com.santiago.twitterapplication.models.cruduser.CrudUser
import kotlinx.android.synthetic.main.activity_update.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UpdateActivity : AppCompatActivity() {
    val apiUrl = "https://reqres.in/api/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        retrofitUpdate()
    }

    private fun retrofitUpdate() {
        buttonUpdate.setOnClickListener {
            val retrofit = Retrofit.Builder()
                .baseUrl(apiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val serviceUpdate = retrofit.create(ApiService::class.java)
            serviceUpdate.update(editTextIdUpdate.text.toString().toInt(), CrudUser(editTextNameUpdate.text.toString(),editTextJobUpdate.text.toString())).enqueue(object : Callback<CrudUser>{
                override fun onResponse(call: Call<CrudUser>, response: Response<CrudUser>) {
                    Log.e("Update>>>",response.body().toString())
                    Log.e("Update>>>",response.toString())
                    if (response.code()==200){
                        Toast.makeText(applicationContext, "YOUR UPDATE WAS SUCCESSFULLY", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(applicationContext, "YOUR UPDATE WAS NOT SUCCESSFULLY", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<CrudUser>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }
    }
}
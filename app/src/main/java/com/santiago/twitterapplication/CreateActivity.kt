package com.santiago.twitterapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.santiago.twitterapplication.interfaces.ApiService
import com.santiago.twitterapplication.models.cruduser.CrudUser
import kotlinx.android.synthetic.main.activity_create.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CreateActivity : AppCompatActivity() {
    val apiUrl = "https://reqres.in/api/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        retrofitCreate()

    }

    private fun retrofitCreate() {
        buttonCreate.setOnClickListener {
            val retrofit = Retrofit.Builder()
                .baseUrl(apiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val serviceCreate = retrofit.create(ApiService::class.java)
            serviceCreate.create(CrudUser(editTextNameCreate.text.toString(),editTextJobCreate.text.toString())).enqueue(object : Callback<CrudUser>{
                override fun onResponse(call: Call<CrudUser>, response: Response<CrudUser>) {
                    Log.i("CREATE>>>",response.body().toString())
                    Log.i("CREATE>>>",response.toString())
                    if (response.code()==201){
                        Toast.makeText(applicationContext, "You create correctly your user", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(applicationContext, "Sorry you could not create a user", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<CrudUser>, t: Throwable) {
                    Log.i("ERROR>>>",t.message.toString())
                }
            })
        }
    }
}
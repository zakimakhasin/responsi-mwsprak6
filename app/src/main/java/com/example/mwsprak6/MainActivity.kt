package com.example.mwsprak6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.JsonReader
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mwsprak6.api.ApiConfig
import com.example.mwsprak6.api.response.Participant
import com.example.mwsprak6.api.response.StatusResponse
import com.example.mwsprak6.api.response.WebinarResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val mainAdapter = MainAdapter()


    companion object{
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        senData()

        val rvWebinar = findViewById<RecyclerView>(R.id.rvWebinar)
        val btn = findViewById<Button>(R.id.btnAdd)

        with(rvWebinar){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mainAdapter
        }



        btn.setOnClickListener {
            val intent = Intent(this, CreateAct::class.java)
            startActivity(intent)
        }

        getData()
    }

    private fun senData() {
        mainAdapter.onClickListener(object : MainAdapter.OnClickUpdate{
            override fun OnUdapete(data: Participant) {
                startActivity(
                    Intent(this@MainActivity, EditAct::class.java)
                        .putExtra("nim", data.nim)
                        .putExtra("nama", data.nama)
                        .putExtra("prodi", data.prodi)
                        .putExtra("gender", data.jenisKelamin)
                )
            }

            override fun OnDelete(data: Participant) {
                delData(data.nim)

            }

        })
    }

    private fun getData(){
        val client = ApiConfig.getService().getWebinar()
        client.enqueue(object : Callback<WebinarResponse>{
            override fun onResponse(
                call: Call<WebinarResponse>,
                response: Response<WebinarResponse>
            ) {
                Log.e(TAG, response.body().toString())
                mainAdapter.setData(response.body()!!.data)
            }

            override fun onFailure(call: Call<WebinarResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }
    private fun delData(
        nim : String
    ){
        val client = ApiConfig.getService().deleteData(nim)
        client.enqueue(object : Callback<StatusResponse>{
            override fun onResponse(
                call: Call<StatusResponse>,
                response: Response<StatusResponse>
            ) {
                if (response.isSuccessful){
                    val submit = response.body()
                    Toast.makeText(
                        applicationContext,
                        submit!!.message,
                        Toast.LENGTH_SHORT
                    ).show()
                    getData()
                }
            }

            override fun onFailure(call: Call<StatusResponse>, t: Throwable) {

            }

        })
    }
}
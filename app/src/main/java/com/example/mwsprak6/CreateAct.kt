package com.example.mwsprak6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import com.example.mwsprak6.api.ApiConfig
import com.example.mwsprak6.api.response.StatusResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateAct : AppCompatActivity() {
    private lateinit var etnim : EditText
    private lateinit var etnama : EditText
    private lateinit var rggender : RadioGroup
    private lateinit var etprodi : EditText
    private lateinit var btn : Button

    companion object{
        private const val TAG = "Create"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        etnim = findViewById(R.id.etNim)
        etnama = findViewById(R.id.etNama)
        rggender = findViewById(R.id.rgGender)
        etprodi = findViewById(R.id.etProdi)
        btn = findViewById(R.id.btnSave)

        btn.setOnClickListener {
            val rgjenis= rggender.checkedRadioButtonId
            val gender = findViewById<RadioButton>(rgjenis)

            val intent = Intent(this, MainActivity::class.java)
            insertData(
                etnim.text.toString(),
                etnama.text.toString(),
                gender.text.toString(),
                etprodi.text.toString()
            )
            startActivity(intent)
        }
    }

    private fun insertData(
        nim: String,
        nama: String,
        jenisKelamin: String,
        prodi: String
    ){
        val client = ApiConfig.getService().insertWebinar(nim, nama, jenisKelamin, prodi)
        client.enqueue(object : Callback<StatusResponse> {
            override fun onResponse(
                call: Call<StatusResponse>,
                response: Response<StatusResponse>
            ) {
                Log.e(TAG, response.body().toString())
            }

            override fun onFailure(call: Call<StatusResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }
}
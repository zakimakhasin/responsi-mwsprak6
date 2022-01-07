package com.example.mwsprak6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.view.get
import com.example.mwsprak6.api.ApiConfig
import com.example.mwsprak6.api.response.Participant
import com.example.mwsprak6.api.response.StatusResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.nio.IntBuffer

class EditAct : AppCompatActivity() {

    private lateinit var ednim : EditText
    private lateinit var ednama : EditText
    private lateinit var edprodi : EditText
    private lateinit var edgender : RadioGroup
    private lateinit var rblaki : RadioButton
    private lateinit var rbPer : RadioButton
    private lateinit var btn : Button


    companion object{
        private const val TAG = "EditActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val intent = intent
        val nim = intent.getStringExtra("nim")
        val nama = intent.getStringExtra("nama")
        val prodi = intent.getStringExtra("prodi")
        val gender = intent.getStringExtra("gender")

        ednim = findViewById(R.id.edit_Nim)
        ednama = findViewById(R.id.edit_Nama)
        edprodi = findViewById(R.id.edit_Prodi)
        edgender = findViewById(R.id.edit_Gender)
        rblaki = findViewById(R.id.rbElaki)
        rbPer = findViewById(R.id.rbEperem)
        btn = findViewById<Button>(R.id.edit_Save)


        //Menampilkan data
        ednim.setText(nim)
        ednama.setText(nama)
        edprodi.setText(prodi)
        if (gender.equals("Laki-laki")){
            rblaki.isChecked
        } else if (gender.equals("Prempuan")){
            rbPer.isChecked
        }

        //Memasukan data baru
        btn.setOnClickListener {
            val rgjenis=edgender.checkedRadioButtonId
            val gender = findViewById<RadioButton>(rgjenis)
            val intent = Intent(this, MainActivity::class.java)
            updateData(
                ednim.text.toString(),
                ednama.text.toString(),
                gender.text.toString(),
                edprodi.text.toString()
            )
            startActivity(intent)
        }

    }

    private fun updateData(
        nim: String,
        nama: String,
        jenisKelamin: String,
        prodi: String
    ){
        val client = ApiConfig.getService().updateWebinar(nim, nama, jenisKelamin, prodi)
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
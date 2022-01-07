package com.example.mwsprak6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class LoginActivity : AppCompatActivity(),  View.OnClickListener {

    private  lateinit var btnlogin : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnlogin = findViewById(R.id.btnlogin)
        btnlogin.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        val dashboardPage = Intent(this, MainActivity::class.java)
        startActivity(dashboardPage)
    }
}
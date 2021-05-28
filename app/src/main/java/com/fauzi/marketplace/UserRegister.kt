package com.fauzi.marketplace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_user_register.*
import kotlinx.android.synthetic.main.activity_user_sign_in.*

class UserRegister : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_register)

        btn_masuk.setOnClickListener{
            val intent = Intent(this , UserSignIn::class.java)
            startActivity(intent)
            Toast.makeText(this , "Masukkan Data Mu" , Toast.LENGTH_LONG).show()
        }
    }
}
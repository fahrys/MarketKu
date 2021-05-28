package com.fauzi.marketplace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_user_register.*
import kotlinx.android.synthetic.main.activity_user_sign_in.*

class UserSignIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_sign_in)

        btn_daftar.setOnClickListener{
            val intent = Intent(this , UserRegister::class.java)
            startActivity(intent)
            Toast.makeText(this , "Registrasi Dahulu" , Toast.LENGTH_LONG).show()
        }

        btn_sign_in.setOnClickListener{
            val intent = Intent(this , MainHome::class.java)
            startActivity(intent)
            Toast.makeText(this , "Welcome Stranger" , Toast.LENGTH_LONG).show()
    }
}
}
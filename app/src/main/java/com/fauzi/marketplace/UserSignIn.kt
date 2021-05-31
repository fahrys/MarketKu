package com.fauzi.marketplace

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.fauzi.marketplace.api.APiInterface
import com.fauzi.marketplace.api.ApiClient
import com.fauzi.marketplace.model.LoginResponse
import kotlinx.android.synthetic.main.activity_user_register.*
import kotlinx.android.synthetic.main.activity_user_sign_in.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserSignIn : AppCompatActivity() {
    lateinit var sharedPref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_sign_in)


        btn_daftar.setOnClickListener {
            val intent = Intent(this, UserRegister::class.java)
            startActivity(intent)
            Toast.makeText(this, "Registrasi Dahulu", Toast.LENGTH_LONG).show()
        }

        btn_sign_in.setOnClickListener {
            loginMasuk()
        }
    }

        fun loginMasuk(){
            val Uname = et_email.text.toString()
            val Upass   = et_password.text.toString()



            if (Uname.isEmpty() || Upass.isEmpty()){
                Toast.makeText(this, "Isikan Username atau Password Dahulu !", Toast.LENGTH_SHORT).show()
            } else {

                var apiInterface: APiInterface = ApiClient().getApiClient()!!.create(APiInterface::class.java)
                var requestCall: Call<LoginResponse> = apiInterface.login(Uname, Upass)
                requestCall.enqueue(object : Callback<LoginResponse> {
                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(baseContext, "Gagal Masuk "+t.toString(), Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        if(response.isSuccessful){
                            Log.d("log", response.body()?.token.toString())
                            val token: String = response.body()?.token.toString()
                            sharedPref = getSharedPreferences("SharePref", Context.MODE_PRIVATE)
                            val editor: SharedPreferences.Editor = sharedPref.edit()
                            editor.putString("token", token)
                            editor.apply()
                            Toast.makeText(this@UserSignIn, "Login Sukses!", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@UserSignIn, MainActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this@UserSignIn, "Username atau password salah", Toast.LENGTH_SHORT).show()
                        }
                    }

                })
        }
        }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
}

}

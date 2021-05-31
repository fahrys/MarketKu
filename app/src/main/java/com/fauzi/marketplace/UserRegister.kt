package com.fauzi.marketplace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.fauzi.marketplace.api.APiInterface
import com.fauzi.marketplace.api.ApiClient
import com.fauzi.marketplace.model.DefaultResponse
import kotlinx.android.synthetic.main.activity_user_register.*
import kotlinx.android.synthetic.main.activity_user_sign_in.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRegister : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_register)

        btn_masuk.setOnClickListener {
            val intent = Intent(this, UserSignIn::class.java)
            startActivity(intent)
            Toast.makeText(this, "Masukkan Data Mu", Toast.LENGTH_LONG).show()
        }

        btn_register.setOnClickListener {
            register()
            goToLoginPage()
        }
    }

//        val actionBar: ActionBar? = supportActionBar
//        actionBar!!.setTitle("Sign Up Form")
//        actionBar!!.setDisplayHomeAsUpEnabled(true)
//        actionBar!!.setDisplayShowHomeEnabled(true)

        fun register() {
            var name = et_username.text.toString()
            var email = etemail.text.toString()
            var password = etpasword.text.toString()
            var password2 = et_confirm_password.text.toString()
            if (password != password2) {
                return Toast.makeText(this, "Password tidak sesuai", Toast.LENGTH_SHORT).show()
            }
            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                return Toast.makeText(this, "Masih ada field yang kosong", Toast.LENGTH_SHORT)
                    .show()
            }

            var apiInterface: APiInterface =
                ApiClient().getApiClient()!!.create(APiInterface::class.java)
            var requestCall: Call<DefaultResponse> = apiInterface.register(name, email, password , password2)
            requestCall.enqueue(object : Callback<DefaultResponse> {
                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                    Toast.makeText(
                        baseContext,
                        "Proses Register gagal " + t.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onResponse(
                    call: Call<DefaultResponse>,
                    response: Response<DefaultResponse>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            this@UserRegister,
                            "Proses Register berhasil",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.d("log", response.body()?.success.toString())
                        val intent = Intent(this@UserRegister, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this@UserRegister,
                            "Proses Register Gagal",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            })
        }

    fun goToLoginPage(){
        val intent = Intent(this, UserSignIn::class.java)
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
}


}
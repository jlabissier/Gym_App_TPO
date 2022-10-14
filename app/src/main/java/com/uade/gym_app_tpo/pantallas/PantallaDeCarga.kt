package com.uade.gym_app_tpo.pantallas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.android.gms.auth.api.signin.GoogleSignInClient

import com.google.firebase.auth.FirebaseAuth
import com.uade.gym_app_tpo.R


class PantallaDeCarga : AppCompatActivity() {
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var fireBaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_de_carga)

        //fireBaseAuth = FirebaseAuth.getInstance()

        //checkUser()
    }


    override fun onStart() {
        super.onStart()

        Handler(Looper.getMainLooper()).postDelayed({
            //checkUser()
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        },1000)
    }

    fun checkUser() {
        val firebaseUser = fireBaseAuth.currentUser
        if (firebaseUser != null) {
            startActivity(Intent(this@PantallaDeCarga, Home::class.java))
            finish()
        }
        else{
            startActivity(Intent(this@PantallaDeCarga, Login::class.java))
            finish()
        }
    }







}





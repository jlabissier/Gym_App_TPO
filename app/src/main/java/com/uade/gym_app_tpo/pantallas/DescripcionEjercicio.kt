package com.uade.gym_app_tpo.pantallas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.uade.gym_app_tpo.R


class DescripcionEjercicio : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_descripcion_ejercicio)
    }


    override fun onStart() {
        super.onStart()
        var nombre = findViewById<TextView>(R.id.tvNombre)
        var musculo = findViewById<TextView>(R.id.tvMusculo)
        var descrip = findViewById<TextView>(R.id.tvDescrip)
        var imgMusculo  = findViewById<ImageView>(R.id.ivMusculo)

        nombre.text = intent.extras?.getString("NombreEjercicio")
        musculo.text = intent.extras?.getString("NombreMusculo")
        descrip.text = intent.extras?.getString("Descripcion")
        var imagenUrl = "https://wger.de" + intent.extras?.getString("Imagen")

        Log.d("Debug",imagenUrl!!);

        Glide.with(this)
            .load(imagenUrl)
            .placeholder(com.google.android.material.R.drawable.ic_clock_black_24dp)
            .centerCrop()
            .into(imgMusculo)

    }
}
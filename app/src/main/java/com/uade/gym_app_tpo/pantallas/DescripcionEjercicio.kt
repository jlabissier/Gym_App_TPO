package com.uade.gym_app_tpo.pantallas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import com.bumptech.glide.Glide
import com.uade.gym_app_tpo.R
import com.uade.gym_app_tpo.dataService.RepositorioMain
import com.uade.gym_app_tpo.objetos.Exercise
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class DescripcionEjercicio : AppCompatActivity() {
    private val coroutineContext: CoroutineContext = newSingleThreadContext("Descripcion")
    private val scope = CoroutineScope(coroutineContext)


    val urlsImagenes = arrayListOf("",
        "https://firebasestorage.googleapis.com/v0/b/gym-app-tpo.appspot.com/o/id1_bicep.png?alt=media&token=00f08977-f649-43ca-8332-350f2eb714f6",
        "https://firebasestorage.googleapis.com/v0/b/gym-app-tpo.appspot.com/o/id2_deltoide.png?alt=media&token=4e4110fd-dd6d-4bfb-8370-2e50edc9b836",
        "https://firebasestorage.googleapis.com/v0/b/gym-app-tpo.appspot.com/o/id3_Serratus_anterior.png?alt=media&token=3018519e-1be8-4545-a049-63909eece362",
        "https://firebasestorage.googleapis.com/v0/b/gym-app-tpo.appspot.com/o/id4_pectorales.png?alt=media&token=5ea0f4d2-664e-4658-832a-802a7a8dd63f",
        "https://firebasestorage.googleapis.com/v0/b/gym-app-tpo.appspot.com/o/id5_Triceps.png?alt=media&token=66b123ff-4c3c-4118-ab87-a888f6d66767",
        "https://firebasestorage.googleapis.com/v0/b/gym-app-tpo.appspot.com/o/id6_pared_abd.png?alt=media&token=4dbae92f-01ba-4ce1-8991-087a6df94d01",
        "https://firebasestorage.googleapis.com/v0/b/gym-app-tpo.appspot.com/o/id7_Calves.png?alt=media&token=6e26098e-a13c-443c-afa8-ff8b19550992",
        "https://firebasestorage.googleapis.com/v0/b/gym-app-tpo.appspot.com/o/id8_Glutes.png?alt=media&token=91bc65dd-284c-4147-bd48-803021c1bee6",
        "https://firebasestorage.googleapis.com/v0/b/gym-app-tpo.appspot.com/o/id9_Trapezius.png?alt=media&token=2a205e40-c7a2-4c8d-ace3-2a7f387c3fd1",
        "https://firebasestorage.googleapis.com/v0/b/gym-app-tpo.appspot.com/o/id10_Quadriceps_femoris.png?alt=media&token=b33e2c2d-0f6b-4cbd-9b15-7524b2d0a231",
        "https://firebasestorage.googleapis.com/v0/b/gym-app-tpo.appspot.com/o/id11_Hamstrings.png?alt=media&token=7e439ac6-7202-4a0a-8417-f91f5cfc6cfb",
        "https://firebasestorage.googleapis.com/v0/b/gym-app-tpo.appspot.com/o/id12_Latissimus_dorsi.png?alt=media&token=a3d7184f-0023-4ce3-aa42-aeb10ea8bf85",
        "https://firebasestorage.googleapis.com/v0/b/gym-app-tpo.appspot.com/o/id13_Brachialis.png?alt=media&token=aba54a2f-c618-4e95-b249-2176a47718a7",
        "https://firebasestorage.googleapis.com/v0/b/gym-app-tpo.appspot.com/o/id14_Obliquus_externus_abdominis.png?alt=media&token=6ec66ba3-7c5b-4d0c-a079-14494e45c96a",
        "https://firebasestorage.googleapis.com/v0/b/gym-app-tpo.appspot.com/o/id15_Soleus.png?alt=media&token=7779c5cf-8f83-4b47-8b0c-1766cc8b23f8");

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
        descrip.text = limpiarDescripcion( intent.extras?.getString("Descripcion")!! )
        var imagenId =  intent.extras?.getInt("ImagenId")
        var imagenUrl =  urlsImagenes[imagenId!!];

        Log.d("Debug",imagenUrl!!);

        Glide.with(this)
            .load(imagenUrl)
            .placeholder(com.google.android.material.R.drawable.ic_clock_black_24dp)
            .centerCrop()
            .into(imgMusculo)



        var fav = findViewById<Switch>(R.id.favDescrip);
        var ejercicio = Exercise();
        fav.setOnCheckedChangeListener { _, isChecked ->
            val message = if (isChecked) "Switch1:ON" else "Switch1:OFF"
            Log.d("PRUEBA",message);

            if(isChecked){
                scope.launch {
                    RepositorioMain.guardarFavorito(this@DescripcionEjercicio , ejercicio)
                    withContext(Dispatchers.Main){
                        Log.d("prueba","Se guardo el ejercicio Fav")
                    }
                }
            }
            else{
                scope.launch {
                    RepositorioMain.eliminarFavorito(this@DescripcionEjercicio, ejercicio)
                    withContext(Dispatchers.Main){
                        Log.d("prueba","Se guardo el ejercicio Fav")
                    }
                    ejercicio.favorito = false;
                }
            }
        }

    }


    fun limpiarDescripcion(descripcion: String) : String{
        var result = ""
        result = if(descripcion != null){
            descripcion
                .replace("<p>","").replace("</p>","")
                .replace("<ol>","").replace("</ol>","")
                .replace("<li>","").replace("</li>","")
                .replace("<ul>","").replace("</ul>","")
                .replace("<em>","").replace("</em>",": \n")
        }else{
            "El ejercicio no contiene descripcion, consulte con su entrenador"
        }
        return result
    }
}
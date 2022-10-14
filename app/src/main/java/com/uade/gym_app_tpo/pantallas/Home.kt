package com.uade.gym_app_tpo.pantallas

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.uade.gym_app_tpo.R
import com.uade.gym_app_tpo.dataService.RepositorioMain
import com.uade.gym_app_tpo.databinding.ActivityMainBinding
import com.uade.gym_app_tpo.objetos.Category
import com.uade.gym_app_tpo.objetos.Exercise
import com.uade.gym_app_tpo.objetos.Muscle
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class Home : AppCompatActivity() {
    private val coroutineContext: CoroutineContext = newSingleThreadContext("Main")
    private val scope = CoroutineScope(coroutineContext)
    private var ejercicios = ArrayList<Exercise>()
    private var musculos = ArrayList<Muscle>()
    private var categorias = ArrayList<Category>()
    private lateinit var binding: ActivityMainBinding

    private lateinit var fireBaseAuth: FirebaseAuth


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnEjercicios = findViewById<Button>(R.id.BtnEjercicios)
        btnEjercicios.setOnClickListener{
            cambioPantallaEjercicios()
        }

        val btnFavoritos = findViewById<Button>(R.id.BtnFavoritos)
        btnFavoritos.setOnClickListener{
            cambioPantallaEjerciciosFavoritos()
        }

        fireBaseAuth = FirebaseAuth.getInstance()
        checkUser()


        binding.btnLogOut.setOnClickListener{
            fireBaseAuth.signOut()
            checkUser()
        }

    }

    private fun checkUser() {
        val firebaseUser = fireBaseAuth.currentUser
        if(firebaseUser == null){
            startActivity(Intent(this,Login::class.java))
        }

    }

    override fun onStart() {
        super.onStart()

        //cargarDatos()

    }


    fun cargarDatos(){
        // Ejercicios
        scope.launch{
            // TODO: Revisar todos los objtos. sos un vago julian.
            ejercicios = RepositorioMain.fetchEjercicios(this@Home)!!
            categorias = RepositorioMain.fetchCategorias(this@Home)!!
            musculos = RepositorioMain.fetchMusculos(this@Home)!!
            //var ejererciciosfavoritos = Repos.fetchFavoritos()

            withContext(Dispatchers.Main){
                //Log.d("debug","cant Personajes: " + personajes!!.data.size)
                Log.d("debug","Cant Ejercicios: " + (ejercicios!!.size ))
                Log.d("debug","Cant Categorias: " + (categorias!!.size ))
                Log.d("debug","Cant Musculos: " + (musculos!!.size ))

            }
        }

    }



    fun cambioPantallaEjercicios(){
        // Cambiar a la pantalla de listado de ejercicios.
        // strar.listadoEjercicios("Ejercicios")
        var intent = Intent(this, Listado::class.java)
        intent.putExtra("Titulo","Ejercicios")
        intent.putExtra("Ejercicios", ejercicios )
        intent.putExtra("Musculos",musculos)
        intent.putExtra("Categorias",categorias)

        startActivity(intent)
        finish()
    }

    fun cambioPantallaEjerciciosFavoritos(){
        // Cambiar a la pantalla de listado de ejercicios.
        var intent = Intent(this, Listado::class.java)
        intent.putExtra("Titulo","Favoritos")

        // todo: Lista ejercicios trae de firebase.
        intent.putExtra("Ejercicios",ejercicios)

        intent.putExtra("Musculos",musculos)
        intent.putExtra("Categorias",categorias)

        startActivity(intent)
        finish()
    }

}
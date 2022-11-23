package com.uade.gym_app_tpo.pantallas

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uade.gym_app_tpo.R
import com.uade.gym_app_tpo.dataService.RepositorioMain
import com.uade.gym_app_tpo.objetos.Category
import com.uade.gym_app_tpo.objetos.Exercise
import com.uade.gym_app_tpo.objetos.Muscle
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class Listado : AppCompatActivity() {

    private val coroutineContext: CoroutineContext = newSingleThreadContext("Main")
    private val scope = CoroutineScope(coroutineContext)

    private lateinit var rvEjercios  : RecyclerView
    private lateinit var adapter : EjerciciosAdapter

    private var ejercicios = ArrayList<Exercise>()
    private var musculos = ArrayList<Muscle>()
    private var categorias = ArrayList<Category>()
    private lateinit var titulo : String




    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_ejercicios)
        supportActionBar?.hide()

        val btnHome = findViewById<Button>(R.id.BtnHome)
        btnHome.setOnClickListener {
            VolverAlHome()
        }

        //todo cargar datos de cache en listas.

        rvEjercios = findViewById<RecyclerView>(R.id.rvEjercicios)
        rvEjercios.layoutManager = LinearLayoutManager(this)
        adapter = EjerciciosAdapter(ejercicios,categorias,musculos,this)
        rvEjercios.adapter = adapter


        adapter.onItemClick = { exercise: Exercise, muscle: Muscle ->
            val intent = Intent(this, DescripcionEjercicio::class.java)
            intent.putExtra("Titulo",titulo)
            intent.putExtra("NombreEjercicio", exercise.name)
            intent.putExtra("NombreMusculo", muscle.name)
            intent.putExtra("Descripcion", exercise.description)
            intent.putExtra("ImagenId",muscle.id)
            intent.putExtra("categoriaID",exercise.category)
            intent.putExtra("exerciseId",exercise.id)
            intent.putExtra("exerciseUUID",exercise.uuid)
            intent.putExtra("favorito",exercise.favorito)
            startActivity(intent)
            finish()
        }

    }


    override fun onStart() {
        super.onStart()
        titulo = intent.extras?.getString("Titulo").toString()
        val TituloText = findViewById<TextView>(R.id.Titulo)
        TituloText.text = titulo;
        val textCargando = findViewById<TextView>(R.id.tv_cargando)


        // como obtengo el titulo cuando inicio la activit
        //cargarDatos(this)

        if(titulo == "Ejercicios"){
            scope.launch{
                ejercicios = RepositorioMain.fetchEjercicios(this@Listado)!!
                categorias = RepositorioMain.fetchCategorias(this@Listado)!!
                musculos = RepositorioMain.fetchMusculos(this@Listado)!!

                withContext(Dispatchers.Main){
                    adapter.update(ejercicios,categorias,musculos)
                    textCargando.visibility = View.INVISIBLE
                }
            }
        }
        else if( titulo == "Favoritos"){
            scope.launch{
                ejercicios = RepositorioMain.fetchEjerciciosFavoritos(this@Listado)!!
                categorias = RepositorioMain.fetchCategorias(this@Listado)!!
                musculos = RepositorioMain.fetchMusculos(this@Listado)!!

                withContext(Dispatchers.Main){
                    if(ejercicios.size > 0){
                        adapter.update(ejercicios,categorias,musculos)
                        textCargando.visibility = View.INVISIBLE
                    }else{
                        textCargando.text = "No Tiene Ejercicios guardados en favoritos \n Agregue algunos y vuelva a revisarlos"
                    }

                }
            }
        }




    }

    fun VolverAlHome(){
        var intent = Intent(this, Home::class.java)
        startActivity(intent)
        finish()
    }

    fun filtroPorCat(){
        //var categoria = findViewById<Options>(R.id.filtroCategora);
        //var ejerciciosFiltrados = ejercicios.map(e.c = c)

    }

    fun cargarDatos(context: Context) {
        // Ejercicios
        scope.launch {
            // TODO: Revisar todos los objtos. sos un vago julian.
            ejercicios = RepositorioMain.fetchEjercicios(context)!!
            categorias = RepositorioMain.fetchCategorias(context)!!
            musculos = RepositorioMain.fetchMusculos(context)!!
            //var ejererciciosfavoritos = Repos.fetchFavoritos()

            withContext(Dispatchers.Main) {
                //Log.d("debug","cant Personajes: " + personajes!!.data.size)
                Log.d("debug", "Cant Ejercicios: " + (ejercicios!!.size))
                Log.d("debug", "Cant Categorias: " + (categorias!!.size))
                Log.d("debug", "Cant Musculos: " + (musculos!!.size))

            }
        }
    }


}
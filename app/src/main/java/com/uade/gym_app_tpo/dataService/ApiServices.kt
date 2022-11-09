package com.uade.gym_app_tpo.dataService

import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.uade.gym_app_tpo.objetos.Category
import com.uade.gym_app_tpo.objetos.Exercise
import com.uade.gym_app_tpo.objetos.Muscle
import com.uade.gym_app_tpo.pantallas.EjerciciosAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiServices {


    companion object{
        val BASE_URL = "https://wger.de/"
        private val db = FirebaseFirestore.getInstance()
        private lateinit var firebaseAuth: FirebaseAuth

        suspend fun fetchEjercicios(context: Context): ArrayList<Exercise>? {
            val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val apiEndpoint = retrofit.create(ExercisesApi::class.java)
            var result = apiEndpoint.getEjercicios().execute()

            return if(result.isSuccessful){
                result.body()!!.results
            }else{
                Log.e("debug","Error al obtener los Ejercicios")
                //val response = ResponseDisney(ArrayList<Personaje>(),0,0,"","")
                return ArrayList<Exercise>()
            }
        }

        suspend fun fetchCategorias(context: Context): ArrayList<Category>? {
            val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val apiEndpoint = retrofit.create(ExercisesApi::class.java)
            var result = apiEndpoint.getCategorys().execute()

            return if(result.isSuccessful){
                result.body()!!.results
            }else{
                Log.e("debug","Error al obtener los Ejercicios")
                //val response = ResponseDisney(ArrayList<Personaje>(),0,0,"","")
                return ArrayList<Category>()
            }
        }

        suspend fun fetchMusculos(context: Context): ArrayList<Muscle>? {
            val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val apiEndpoint = retrofit.create(ExercisesApi::class.java)
            var result = apiEndpoint.getMuscles().execute()

            return if(result.isSuccessful){
                result.body()!!.results
            }else{
                Log.e("debug","Error al obtener los Ejercicios")
                //val response = ResponseDisney(ArrayList<Personaje>(),0,0,"","")
                return ArrayList<Muscle>()
            }
        }

        suspend fun guardarFavorito(context: EjerciciosAdapter, ejercicio: Exercise){
            firebaseAuth = FirebaseAuth.getInstance()
            val firebaseUser = firebaseAuth.currentUser
            val email : String = firebaseUser?.email!!
            Log.d("PRUEBA",email);

            db.collection("usuarios")
                .document(email)
                .collection("favoritos")
                .document(ejercicio.id.toString())
                .set(
                    hashMapOf(
                        "id" to ejercicio.id,
                        "uid" to ejercicio.uuid,
                        "name" to ejercicio.name,
                        "description" to ejercicio.description,
                        "category" to ejercicio.category,
                        "muscles" to ejercicio.muscles,
                        "favorito" to true,
                    )
                )
        }

        suspend fun eliminarFavorito(context: EjerciciosAdapter,ejercicio: Exercise){
            firebaseAuth = FirebaseAuth.getInstance()
            val firebaseUser = firebaseAuth.currentUser
            val email : String = firebaseUser?.email!!
            Log.d("PRUEBA",email);

            db.collection("usuarios")
                .document(email)
                .collection("favoritos")
                .document(ejercicio.id.toString())
                .delete()
                .addOnSuccessListener {
                    Log.d("prueba","ApiService: Ejercicio eliminado de favoritos")
                }
                .addOnFailureListener {
                    Log.d("prueba","ApiService: ERROR: Ejercicio no se pudo eliminar de favoritos")
                }
        }

        suspend fun fetchEjerciciosFavoritos(context: Context): ArrayList<Exercise>? {
            firebaseAuth = FirebaseAuth.getInstance()
            val firebaseUser = firebaseAuth.currentUser
            val email : String = firebaseUser?.email!!
            var ejercicios = ArrayList<Exercise>()

            db.collection("usuarios").document(email).collection("favoritos")
                .get()
                .addOnSuccessListener { documents ->
                    if (documents != null) {
                        for (document in documents) {
                            Log.d("GetFirebase", "${document.id} => ${document.data}")
                            var ejercicio = document.toObject(Exercise::class.java)
                            Log.d("GetFirebase", "Convertido a objeto: => ${ejercicio}")
                            ejercicios.add(ejercicio)
                        }
                    } else {
                        Log.d("GetFirebase", "No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d("GetFirebase", "get failed with ", exception)
                }

            return ejercicios
        }


    }



}
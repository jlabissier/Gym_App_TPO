package com.uade.gym_app_tpo.dataService

import android.content.Context
import android.util.Log
import com.uade.gym_app_tpo.objetos.Category
import com.uade.gym_app_tpo.objetos.Exercise
import com.uade.gym_app_tpo.objetos.Muscle
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiServices {
    companion object{
        val BASE_URL = "https://wger.de/"

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


    }



}
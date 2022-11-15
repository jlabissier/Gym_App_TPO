package com.uade.gym_app_tpo.dataService

import android.content.Context
import com.uade.gym_app_tpo.objetos.Category
import com.uade.gym_app_tpo.objetos.Exercise
import com.uade.gym_app_tpo.objetos.Muscle
import com.uade.gym_app_tpo.pantallas.EjerciciosAdapter

class RepositorioMain {
    // que corcho es esto.
    companion object {
        suspend fun fetchEjercicios(context: Context): ArrayList<Exercise>? {
            return ApiServices.fetchEjercicios(context)?.let { verificarEjercicios(it) }
        }

        suspend fun fetchCategorias(context: Context): ArrayList<Category>? {
            return ApiServices.fetchCategorias(context)
        }

        suspend fun fetchMusculos(context: Context): ArrayList<Muscle>? {
            return ApiServices.fetchMusculos(context)
        }

        suspend fun guardarFavorito(context: EjerciciosAdapter, ejercicio: Exercise){
            ApiServices.guardarFavorito(context,ejercicio)
        }

        suspend fun eliminarFavorito(context: EjerciciosAdapter,ejercicio: Exercise){
            ApiServices.eliminarFavorito(context,ejercicio)
        }

        suspend fun fetchEjerciciosFavoritos(context: Context): ArrayList<Exercise>? {
            return ApiServices.fetchEjerciciosFavoritos(context)
        }

        fun verificarEjercicios(ejercicios : ArrayList<Exercise>): ArrayList<Exercise> {
            val ejerFiltrados = ArrayList<Exercise>()
            for (ejercicio in ejercicios)
                if(ejercicio.muscles?.size!! > 0) {
                    ejerFiltrados.add(ejercicio)
                }

            return ejerFiltrados
        }
    }
}
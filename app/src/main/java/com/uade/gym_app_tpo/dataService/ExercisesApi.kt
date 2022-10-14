package com.uade.gym_app_tpo.dataService

import com.uade.gym_app_tpo.objetos.CategoriasResponse
import com.uade.gym_app_tpo.objetos.EjerciciosResponse
import com.uade.gym_app_tpo.objetos.MuscleResponse
import retrofit2.Call
import retrofit2.http.GET

interface ExercisesApi {

    @GET("/api/v2/exercise?limit=500")
    fun getEjercicios(
        //@Query("id") id:String
    ) : Call<EjerciciosResponse>

    @GET("/api/v2/muscle?limit=500")
    fun getMuscles(
    ) : Call<MuscleResponse>

    @GET("/api/v2/exercisecategory")
    fun getCategorys(
    ): Call<CategoriasResponse>


}
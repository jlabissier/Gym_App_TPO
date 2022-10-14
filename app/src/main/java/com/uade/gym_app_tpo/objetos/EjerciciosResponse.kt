package com.uade.gym_app_tpo.objetos


data class EjerciciosResponse (
        val count: Int? = null,
        val next: String? = null,
        val previous: String? = null,
        val results: ArrayList<Exercise>? = null,
)
package com.uade.gym_app_tpo.objetos

import com.google.gson.annotations.SerializedName

data class FavoriteExercise(
    val id: Int? = null,
    val uuid: String? = null,
    val name: String? = null,

    val description: String? = null,

    val category: Int? = null,
    val muscles: ArrayList<Int>? = null,

    var favorito: Boolean = false

)
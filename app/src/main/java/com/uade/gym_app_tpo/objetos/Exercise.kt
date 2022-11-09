package com.uade.gym_app_tpo.objetos

import com.google.gson.annotations.SerializedName

data class Exercise(
    val id: Int? = null,
    val uuid: String? = null,
    val name: String? = null,

    @SerializedName("exercise_base")
    val exerciseBase: Int? = null,

    val description: String? = null,

    @SerializedName("creation_date")
    val creationDate: String? = null,

    val category: Int? = null,
    val muscles: ArrayList<Int>? = null,

    @SerializedName("muscles_secondary")
    val musclesSecondary: ArrayList<Int>? = null,


    val equipment: ArrayList<Int>? = null,
    val language: Int? = null,
    val license: Int? = null,

    @SerializedName("license_author")
    val license_author: String? = null,

    val variations: ArrayList<Int>? = null,

    var favorito: Boolean = false
)

package com.uade.gym_app_tpo.objetos

import com.google.gson.annotations.SerializedName

data class Category (
    val id: Int? = null,
    val name: String? = null,
    val name_en: String? = null,
    val is_front: Boolean? = null,
    @SerializedName("image_url_main")
    val image_main: String? = null,
    @SerializedName("image_url_secondary")
    val image_secondary: String? = null,
)
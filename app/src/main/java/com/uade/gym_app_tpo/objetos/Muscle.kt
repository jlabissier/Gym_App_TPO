package com.uade.gym_app_tpo.objetos
import com.google.gson.annotations.SerializedName


data class Muscle (
    val id: Int? = null,
    val name: String? = null,

    @SerializedName("name_en")
    val nameEn: String? = null,

    @SerializedName("is_front")
    val isFront: Boolean? = null,

    @SerializedName("image_url_main")
    val imageURLMain: String? = null,

    @SerializedName("image_url_secondary")
    val imageURLSecondary: String? = null
)

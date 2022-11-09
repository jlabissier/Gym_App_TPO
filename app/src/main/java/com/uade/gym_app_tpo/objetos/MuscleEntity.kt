package com.uade.gym_app_tpo.objetos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "muscle_table")

data class MuscleEntity (
    @PrimaryKey @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "name") var name: String,
    @SerializedName("name_en")
    @ColumnInfo(name = "nameEn") var nameEn: String,
    @ColumnInfo(name = "is_front") var is_front: Boolean,
    @SerializedName("image_url_main")
    @ColumnInfo(name = "image_main") var image_main: String,
    @SerializedName("image_url_secondary")
    @ColumnInfo(name = "image_secondary") var image_secondary: String,
    )
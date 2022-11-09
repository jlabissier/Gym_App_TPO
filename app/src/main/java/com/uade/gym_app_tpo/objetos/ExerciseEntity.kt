package com.uade.gym_app_tpo.objetos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise_table")
data class ExerciseEntity(
 @PrimaryKey @ColumnInfo(name = "id") var id: Int,
 @ColumnInfo(name = "uid") var uid: String,
 @ColumnInfo(name = "name") var name: String,
 @ColumnInfo(name = "description") var description: String,
 @ColumnInfo(name = "category") var category: Int,
 @ColumnInfo(name = "muscles") var muscles: ArrayList<Int>,
 @ColumnInfo(name = "favorito") var favorito: Boolean,
)

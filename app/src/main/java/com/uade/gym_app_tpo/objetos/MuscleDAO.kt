package com.uade.gym_app_tpo.objetos

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


interface MuscleDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlumno(item : CategoryEntity)

    @Query("DELETE FROM muscle_table")
    suspend fun deleteAll()

    @Query("DELETE FROM muscle_table WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM muscle_table")
    suspend fun fetchAll() : MutableList<MuscleEntity>
}
package com.uade.gym_app_tpo.objetos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface ExerciseDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercise(item : ExerciseEntity)

    @Query("DELETE FROM exercise_table")
    suspend fun deleteAll()

    @Query("DELETE FROM exercise_table WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM exercise_table")
    suspend fun fetchAll() : MutableList<ExerciseEntity>

}
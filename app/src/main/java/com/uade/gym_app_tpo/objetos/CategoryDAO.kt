package com.uade.gym_app_tpo.objetos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface CategoryDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlumno(item : CategoryEntity)

    @Query("DELETE FROM category_table")
    suspend fun deleteAll()

    @Query("DELETE FROM category_table WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM category_table")
    suspend fun fetchAll() : MutableList<CategoryEntity>

}
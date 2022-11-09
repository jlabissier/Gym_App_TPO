package com.uade.gym_app_tpo.dataService

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.uade.gym_app_tpo.objetos.ExerciseEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Database(entities = [ExerciseEntity::class], version = 1, exportSchema = false)
abstract class RoomDataBase : RoomDatabase() {

    abstract fun ExerciseDAO(): ExerciseEntity

    companion object {
        @Volatile
        private var _instance: RoomDataBase? = null

        fun getInstance(context: Context): RoomDataBase = _instance ?: synchronized(this) {
            _instance ?: buildDatabase(context)
        }

        private fun buildDatabase(context: Context): RoomDataBase =
            Room.databaseBuilder(context, RoomDataBase::class.java, "Room_dataBase")
                .fallbackToDestructiveMigration()
                .build()

        suspend fun clean(context: Context) = coroutineScope {
            launch(Dispatchers.IO) {
                getInstance(context).clearAllTables()
            }
        }
    }
}
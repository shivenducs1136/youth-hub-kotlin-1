package com.dsckiet.youthhub.RoomDatabaseWork.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dsckiet.youthhub.RoomDatabaseWork.Dao.PlaylistDao
import com.dsckiet.youthhub.RoomDatabaseWork.Entity.PlaylistEntity

@Database(entities = [PlaylistEntity::class],version = 1)
abstract class PlaylistDataBase: RoomDatabase() {

    abstract fun playlistDAO(): PlaylistDao
    companion object{

        @Volatile
        private var INSTANCE: PlaylistDataBase? = null
        fun getDatabase(context: Context): PlaylistDataBase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlaylistDataBase::class.java,
                    "playlist_database"
                ).build()
                INSTANCE = instance

                instance
            }
        }

    }
}
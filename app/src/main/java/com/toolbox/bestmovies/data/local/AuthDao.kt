package com.toolbox.bestmovies.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.toolbox.bestmovies.data.entities.Auth

@Dao
abstract class AuthDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAuth(auth: Auth): Long

    @Query("SELECT * FROM Auth LIMIT 1")
    abstract fun queryAuth(): Auth?

}
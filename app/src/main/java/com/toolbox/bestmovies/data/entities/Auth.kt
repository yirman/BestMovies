package com.toolbox.bestmovies.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Auth (
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @SerializedName("token") var token : String? = null,
    @SerializedName("type") var type : String? = null
)
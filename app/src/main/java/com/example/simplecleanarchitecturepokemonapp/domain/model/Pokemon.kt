package com.example.simplecleanarchitecturepokemonapp.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "pokemon")
data class Pokemon(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val name: String,
    val url: String
): Parcelable

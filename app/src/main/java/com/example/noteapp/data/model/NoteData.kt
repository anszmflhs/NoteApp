package com.example.noteapp.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "note_table")
@Parcelize
data class NoteData (
    @PrimaryKey(autoGenerate = true)

    val id : Int,
    val title : String,
    val priority : Priority,
    val description : String
        ) : Parcelable
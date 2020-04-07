package com.example.onemore6

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "studentTbl")
data class Student(
    @PrimaryKey
    var id:Long?,

    @ColumnInfo(name = "uuid")
    var fullName: String,

    @ColumnInfo(name = "result")
    var result:Int
)
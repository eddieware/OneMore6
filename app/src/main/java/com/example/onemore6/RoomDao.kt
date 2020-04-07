package com.example.onemore6

import androidx.room.*

@Dao
interface studentDao{
    @Query("SELECT * FROM studentTbl")
    fun allStudents():List<Student>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(student:Student)

    @Update
    fun update(student:Student)
}
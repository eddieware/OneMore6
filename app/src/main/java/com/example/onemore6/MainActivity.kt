package com.example.onemore6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mDb:RoomSingleton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the room database PRUEBA
        //Room.databaseBuilder(applicationContext, AppDB::class.java,"EmployeeDB").build()
        mDb = Room.databaseBuilder(applicationContext,RoomSingleton::class.java, "EmployeeDB").build()

        // Make the text view scrollable
        textView.movementMethod = ScrollingMovementMethod()

        // Insert button click listener
        btnInsert.setOnClickListener{
            // Initialize a new student
            Thread{
                var student = Student()

                student.id= 3
                student.fullName="Eddie3"
                student.result=15
                mDb.studentDao().insert(student)

                mDb.studentDao().allStudents().forEach{
                    Log.i("@EDUARDO", " ID is ${it.id}")
                    Log.i("@EDUARDO", " Name is ${it.fullName}")
                    Log.i("@EDUARDO", " Position is ${it.result}")

                }
            }.start()
        }

        btnUpdate.setOnClickListener{
            // Initialize a new student
            Thread{
                var student = Student()

                student.id= 2
                student.fullName="NILESH"
                student.result=15
                mDb.studentDao().insert(student)

                mDb.studentDao().allStudents().forEach{
                    Log.i("@EDUARDO", " ID is ${it.id}")
                    Log.i("@EDUARDO", " Name is ${it.fullName}")
                    Log.i("@EDUARDO", " Position is ${it.result}")

                }
            }.start()
        }


        btnDelete.setOnClickListener{
            // Initialize a new student
            Thread{
                var student = Student()

                student.id= 2
                student.fullName="NILESH"
                student.result=15
                mDb.studentDao().delete(student)

                mDb.studentDao().allStudents().forEach{
                    Log.i("@EDUARDO", " ID is ${it.id}")
                    Log.i("@EDUARDO", " Name is ${it.fullName}")
                    Log.i("@EDUARDO", " Position is ${it.result}")

                }
            }.start()
        }









        // Select button click listener
        btnSelect.setOnClickListener{
            doAsync {
                // Get the student list from database
                val list = mDb.studentDao().allStudents()

                uiThread {
                    toast("${list.size} records found.")
                    // Display the students in text view
                    textView.text = ""
                    for (student in list){
                        textView.append("${student.id} : ${student.fullName} : ${student.result}\n")
                    }
                }
            }
        }
    }
}

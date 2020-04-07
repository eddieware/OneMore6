package com.example.onemore6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mDb:RoomSingleton
    private val mRandom: Random = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the room database
        mDb = RoomSingleton.getInstance(applicationContext)

        // Make the text view scrollable
        textView.movementMethod = ScrollingMovementMethod()

        // Insert button click listener
        btnInsert.setOnClickListener{
            // Initialize a new student
            val student = Student(id = null,
                //fullName = UUID.randomUUID().toString(),
                //result = mRandom.nextInt(100)
                fullName = "Eduardo",
                result = 10

                //Remplazar por un place Holder
            )

            doAsync {
                // Put the student in database
                mDb.studentDao().insert(student)

                uiThread {
                    toast("One record inserted.")
                }
            }
        }

        /*

        btnUpdate.setOnClickListener{
            // Initialize a new student
            val student = Student(id = null,
                //fullName = UUID.randomUUID().toString(),
                //result = mRandom.nextInt(100)
                fullName = "Eduardo",
                result = 14

                //Remplazar por un place Holder
            )

            doAsync {
                // Put the student in database
                mDb.studentDao().update(student)

                uiThread {
                    toast("One record updated.")
                }
            }
        }*/

        btnUpdate.setOnClickListener {
            Thread{

                val student = Student(id = null,
                    //fullName = UUID.randomUUID().toString(),
                    //result = mRandom.nextInt(100)
                    fullName = "Eduardo",
                    result = 10

                    //Remplazar por un place Holder
                )


                student.id = 2
                student.fullName = "NILESH"
                student.result= 10
                mDb.studentDao().allStudents()
                //db.empDAO().update(emp)


                /*
                  emp.emp_id = 3
                  emp.emp_name="Eduardo mejia3"
                  emp.emp_post="Application Developer3"
                  db.empDAO().saveEmp(emp)*/
                //db.empDAO().delData(emp)


                //mDb.studentDao().allStudents()

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

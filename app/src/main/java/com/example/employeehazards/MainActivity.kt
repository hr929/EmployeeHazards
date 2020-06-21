package com.example.employeehazards

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var db:FirebaseFirestore
    private lateinit var mStorageRef:StorageReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db= FirebaseFirestore.getInstance()
        mStorageRef=FirebaseStorage.getInstance().reference
        go.setOnClickListener {
            if(id.text.toString()==""){
                Toast.makeText(this,"Please enter the id of the employee you are searching",Toast.LENGTH_LONG).show()
            }
            else{
                db.collection("employee").document(id.text.toString()).get().addOnCompleteListener {
                    if(it.result!!.exists()){
                        name.text=it.result!!.getString("name")
                        dob.text=it.result!!.getString("dob")
                        status.text=it.result!!.getString("status")
                        emergency_num.text=it.result!!.getString("emergency_number")
                        contact.text=it.result!!.getString("contact")
                        health_hazard.text=it.result!!.getString("health_hazard")
                    }
                    if(!it.result!!.exists()){
                        Toast.makeText(this,"Could not find employee",Toast.LENGTH_LONG).show()

                    }
                }.addOnFailureListener {
                    Toast.makeText(this,"Could not load data or find employee",Toast.LENGTH_LONG).show()
                }


            }

        }
    }
}
package com.mmm.firebase_todo_practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mmm.firebase_todo_practice.databinding.ActivityAddMainBinding

class AddMainActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddMainBinding
private lateinit var DatabaseReference : DatabaseReference
    private lateinit var auth : FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        DatabaseReference = FirebaseDatabase.getInstance().reference
        auth = FirebaseAuth.getInstance()


        binding.SavaData.setOnClickListener {

            var title = binding.edtTitle.text.toString()
            var description  = binding.edtDescription.text.toString()

            if (title.isEmpty() && description.isEmpty()){

                Toast.makeText(this, "fill data", Toast.LENGTH_SHORT).show()
            }else{
                val currentuser = auth.currentUser
                currentuser?.let { user ->
                    // generate unique key
                    var notekey = DatabaseReference.child("usres").child(user.uid).child("notes").push().key

                    var ModelData = ModelData(title,description,notekey ?:"")
                    if (notekey != null)
                        DatabaseReference.child("usres").child(user.uid).child("notes").child(notekey).setValue(ModelData)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful){
                                    Toast.makeText(this, "note save your Data", Toast.LENGTH_SHORT).show()
                                    finish()
                                }else{
                                    Toast.makeText(this, "faild save your Data", Toast.LENGTH_SHORT).show()
                                }
                            }

                }








            }



        }





    }
}
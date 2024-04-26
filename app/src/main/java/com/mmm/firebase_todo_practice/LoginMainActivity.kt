package com.mmm.firebase_todo_practice

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.mmm.firebase_todo_practice.databinding.ActivityLoginMainBinding
import com.mmm.firebase_todo_practice.databinding.ActivitySingupMainBinding

class LoginMainActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginMainBinding
    lateinit var auth : FirebaseAuth


    override fun onStart() {
        super.onStart()
        var currentUser :FirebaseUser? = auth.currentUser
        if (currentUser != null){

            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        auth = FirebaseAuth.getInstance()

        binding.login.setOnClickListener {

            var loginemail = binding.loginemail.text.toString()
            var lodinpassword = binding.loginpassword.text.toString()

            if (loginemail.isEmpty() || lodinpassword.isEmpty() ){

                Toast.makeText(this, "fill all data", Toast.LENGTH_SHORT).show()


            }else{

                auth.signInWithEmailAndPassword(loginemail,lodinpassword)
                    .addOnCompleteListener { task ->

                        if (task.isSuccessful) {
                            Log.e(ContentValues.TAG, "success")
                            startActivity(Intent(this,MainActivity::class.java))

                        }
                        else{
                            Toast.makeText(this, "resigester faild: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }


                    }
            }



        }


        binding.singUPbtn.setOnClickListener {
    startActivity(Intent(this,SingupMainActivity::class.java))
    finish()

        }

    }
}
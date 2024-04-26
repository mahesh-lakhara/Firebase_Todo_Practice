package com.mmm.firebase_todo_practice

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.mmm.firebase_todo_practice.databinding.ActivitySingupMainBinding

class SingupMainActivity : AppCompatActivity() {

    lateinit var binding: ActivitySingupMainBinding
    lateinit var auth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingupMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()



        binding.singinbtn.setOnClickListener {
            startActivity(Intent(this,LoginMainActivity::class.java))
            finish()


        }
        binding.ragister.setOnClickListener {

            var email = binding.emailbtn.text.toString()
            var password = binding.passwordbtn.text.toString()
            var repassword = binding.Repitepassword.text.toString()

            if (email.isEmpty() || password.isEmpty() || repassword.isEmpty()){

                Toast.makeText(this, "fill all data", Toast.LENGTH_SHORT).show()

            }else if(password != repassword){

                Toast.makeText(this, "enter vaild password", Toast.LENGTH_SHORT).show()
            }else{

auth.createUserWithEmailAndPassword(email,password)
    .addOnCompleteListener { task ->

        if (task.isSuccessful) {
            Log.e(ContentValues.TAG, "success")
            startActivity(Intent(this,LoginMainActivity::class.java))
//            finish()
    }
        else{
            Toast.makeText(this, "resigester faild: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
        }


        }
            }

        }


    }
}
package com.mmm.firebase_todo_practice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mmm.firebase_todo_practice.databinding.ActivityAddMainBinding
import com.mmm.firebase_todo_practice.databinding.ActivityLoginMainBinding
import com.mmm.firebase_todo_practice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.add.setOnClickListener{
            startActivity(Intent(this,AddMainActivity::class.java))
        }
        binding.ShowDta.setOnClickListener{
            startActivity(Intent(this,AllNoteMainActivity::class.java))
        }


    }
}
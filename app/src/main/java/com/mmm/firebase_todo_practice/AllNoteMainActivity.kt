package com.mmm.firebase_todo_practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.mmm.firebase_todo_practice.databinding.ActivityAddMainBinding
import com.mmm.firebase_todo_practice.databinding.ActivityAllNoteMainBinding
import com.mmm.firebase_todo_practice.databinding.DialogeUpdateNoteBinding

class AllNoteMainActivity : AppCompatActivity(), NotesAdpter.OnItemClickListener {

    lateinit var binding: ActivityAllNoteMainBinding
    private lateinit var DatabaseReference: DatabaseReference
    private lateinit var auth: FirebaseAuth
    lateinit var adpter: NotesAdpter
    private lateinit var RecyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllNoteMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        RecyclerView = binding.rcv
        RecyclerView.layoutManager = LinearLayoutManager(this)

        DatabaseReference = FirebaseDatabase.getInstance().reference
        auth = FirebaseAuth.getInstance()


        val currentuser = auth.currentUser
        currentuser?.let { user ->

            var noteReference = DatabaseReference.child("usres").child(user.uid).child("notes")
            noteReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var noteList = mutableListOf<ModelData>()
                    for (noteSnapshort in snapshot.children) {

                        var note = noteSnapshort.getValue(ModelData::class.java)
                        note?.let {
                            noteList.add(it)
                        }
                    }
                    var adpter = NotesAdpter(noteList, this@AllNoteMainActivity)
                    RecyclerView.adapter = adpter

                }
                override fun onCancelled(error: DatabaseError) {}
            })
        }


    }

    override fun onDeleteClick(noteId: String) {

        var currentuser = auth.currentUser
        currentuser?.let { user ->
            var noteReference = DatabaseReference.child("users").child(user.uid).child("notes")
            noteReference.child(noteId).removeValue()

        }

    }

    override fun onupdateClick(noteId: String, currenttitle: String, currentdescription: String) {

        var dialogbinding = DialogeUpdateNoteBinding.inflate(LayoutInflater.from(this))
        var dialog = AlertDialog.Builder(this).setView(dialogbinding.root).setTitle("update note")
            .setPositiveButton("update") { dialog, _ ->
                var newtitle = dialogbinding.txtuptitlt.text.toString()
                var newdescription = dialogbinding.txtupdescri.text.toString()
                updateNotedatabase(noteId, newtitle, newdescription)
                dialog.dismiss()
            }

            .setNegativeButton("cancle1") { dialog, _ ->
                dialog.dismiss()
            }.create()
        dialogbinding.txtuptitlt.setText(currenttitle)
        dialogbinding.txtupdescri.setText(currentdescription)
        dialog.show()


    }

    private fun updateNotedatabase(noteId: String, newtitle: String, newdescription: String) {

        var currentuser = auth.currentUser
        currentuser?.let { user ->
            var noteReference = DatabaseReference.child("users").child(user.uid).child("notes")
            var updateNote = ModelData(newtitle, newdescription, noteId)
            noteReference.child(noteId).setValue(updateNote).addOnCompleteListener { task ->

                    if (task.isSuccessful) {
                        Toast.makeText(this, "succefull", Toast.LENGTH_SHORT).show()
                    } else {
                    }

                    Toast.makeText(this, "not succefull", Toast.LENGTH_SHORT).show()
                }

        }


    }
}
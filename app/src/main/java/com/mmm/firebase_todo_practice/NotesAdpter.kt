package com.mmm.firebase_todo_practice

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mmm.firebase_todo_practice.databinding.IteamDataBinding

class NotesAdpter(private var notes:List<ModelData>, private var iteamclickListner :OnItemClickListener): RecyclerView.Adapter<NotesAdpter.DataHolder>() {

    interface OnItemClickListener{
        fun onDeleteClick(noteId:String)
        fun onupdateClick(noteId:String,title:String,description:String)
    }

    lateinit var context: Context



    class DataHolder(itemView: IteamDataBinding) : ViewHolder(itemView.root){
        var binding = itemView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        context = parent.context
        var binding = IteamDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataHolder(binding)

    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: DataHolder, position: Int) {

        holder.binding.apply {
            notes.get(position).apply {

               txtTitle.text = title
                txtDecription.text = description
            }
    }
       holder.apply {
           notes.get(position).apply {

               binding.txtupdate.setOnClickListener {
                   iteamclickListner.onupdateClick(noteId,title,description)
               }
               binding.txtDelete.setOnClickListener {
                   iteamclickListner.onDeleteClick(noteId)
               }

           }

       }

    }

}
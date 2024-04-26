package com.mmm.firebase_todo_practice

data class ModelData (var title:String, var description:String, val noteId : String){
    constructor():this("","","")
}
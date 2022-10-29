package com.sefax.kotlintodolist

data class Todo(
    val name : String,
    var isChecked : Boolean = false
)
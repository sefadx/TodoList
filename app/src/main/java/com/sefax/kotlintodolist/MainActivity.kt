package com.sefax.kotlintodolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.sefax.kotlintodolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        todoAdapter = TodoAdapter(mutableListOf())

        binding.rvTodoItems.adapter = todoAdapter
        binding.rvTodoItems.layoutManager = LinearLayoutManager(this)

        binding.buttonAddItem.setOnClickListener {
            if (binding.editText.text.isNotEmpty()){
                todoAdapter.addTodoItem(Todo(binding.editText.text.toString()))
                binding.editText.text.clear()
            }
        }

        binding.buttonRemoveItem.setOnClickListener {
            todoAdapter.deleteTodoItems()
        }

    }
}
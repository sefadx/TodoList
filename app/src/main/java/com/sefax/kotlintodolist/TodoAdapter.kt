package com.sefax.kotlintodolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sefax.kotlintodolist.databinding.ItemTodoBinding

class TodoAdapter(
    private val todos: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding = ItemTodoBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent
                ,false)
        )
    }

    fun addTodoItem(todo: Todo){
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteTodoItems(){
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    //Bu kısımda chechkbox durumuna göre textviewde ki yazının üstünün çizili olup olmamasını belirliyoruz.
    private fun itemSetStatus(nameTextView : TextView, isChecked : Boolean){
        if(isChecked){
            nameTextView.paintFlags = nameTextView.paintFlags or STRIKE_THRU_TEXT_FLAG
        }else{
            nameTextView.paintFlags = nameTextView.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodoItem = todos[position]

        holder.apply {
            binding.itemText.text = currentTodoItem.name
            binding.itemCheckbox.isChecked = currentTodoItem.isChecked
            itemSetStatus(binding.itemText,currentTodoItem.isChecked)
            binding.itemCheckbox.setOnCheckedChangeListener { _, isChecked ->
                itemSetStatus(binding.itemText,isChecked)
                currentTodoItem.isChecked = isChecked
            }
        }

    }

    override fun getItemCount(): Int {
        return todos.size
    }
}
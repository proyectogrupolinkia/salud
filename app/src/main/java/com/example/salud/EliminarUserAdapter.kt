package com.example.salud


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class EliminarUserAdapter(private var users: List<User>, private val onDeleteClick: (User) -> Unit) :
    RecyclerView.Adapter<EliminarUserAdapter.UserViewHolder>() {


    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.tvNameEliminar)
        val textView2: TextView = view.findViewById(R.id.tvCorreoEliminar)

        val btnConsultar: Button = view.findViewById(R.id.btneliminar)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_usereliminar, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.btnConsultar.setOnClickListener { onDeleteClick(user) }

        holder.textView.text = user.nombre
        holder.textView2.text = user.correo


    }

    override fun getItemCount(): Int = users.size

    fun updateData(newUsers: List<User>) {
        users = newUsers
        notifyDataSetChanged()
    }


}

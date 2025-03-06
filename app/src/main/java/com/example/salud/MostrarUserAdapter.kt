package com.example.salud


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MostrarUserAdapter(private var users: List<User>,
                         private val onEditClick: (User)-> Unit,
                         private val onUpdateClick: (User) -> Unit,
                         private val onDeleteClick: (User) -> Unit) : RecyclerView.Adapter<MostrarUserAdapter.UserViewHolder>() {


    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.tvName)
        val textView2: TextView = view.findViewById(R.id.tvCorreo)

        val btnConsultar: Button =view.findViewById(R.id.btnConsultar)
        val btnModificar: Button =view.findViewById(R.id.btnModificar)
        val btnEliminar: Button =view.findViewById(R.id.btnEliminar)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.btnConsultar.setOnClickListener {  onEditClick (user)}
        holder.btnModificar.setOnClickListener {  onUpdateClick (user)}
        holder.btnEliminar.setOnClickListener {  onDeleteClick (user)}

        holder.textView.text = user.nombre
        holder.textView2.text = user.correo




    }

    override fun getItemCount(): Int = users.size

    fun updateData(newUsers: List<User>) {
        users = newUsers
        notifyDataSetChanged()
    }


}

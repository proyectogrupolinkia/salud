package com.example.salud


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class GestionarUserAdapter(private var users: List<User>,
                           private val onReadClick: (User)-> Unit,
                           private val onUpdateClick: (User) -> Unit,
                           private val onDeleteClick: (User) -> Unit) : RecyclerView.Adapter<GestionarUserAdapter.UserViewHolder>() {


    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewName: TextView = view.findViewById(R.id.tvName)
        val textViewCorreo: TextView = view.findViewById(R.id.tvCorreo)

        val btnConsultar: Button =view.findViewById(R.id.btnConsultar)
        val btnModificar: Button =view.findViewById(R.id.btnModificar)
        val btnEliminar: Button =view.findViewById(R.id.btnEliminar)

    }
    //Creamos el viewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }
    //Asociamos los datos

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.btnConsultar.setOnClickListener {  onReadClick (user)}
        holder.btnModificar.setOnClickListener {  onUpdateClick (user)}
        holder.btnEliminar.setOnClickListener {  onDeleteClick (user)}

        holder.textViewName.text = user.nombre
        holder.textViewCorreo.text = user.correo




    }
    //Obtenemos el tamaño de los datos.

    override fun getItemCount(): Int = users.size
    //Actualizamos los datos del user mediante los eventos.

    fun updateData(newUsers: List<User>) {
        users = newUsers
        notifyDataSetChanged()
    }


}

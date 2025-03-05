package com.example.salud


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MostrarUserAdapter(private var users: List<User>, private val onEditClick: (User) -> Unit) : RecyclerView.Adapter<MostrarUserAdapter.UserViewHolder>() {


    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.tvName)
        val textView2: TextView = view.findViewById(R.id.tvCorreo)
//        val textView3: TextView = view.findViewById(R.id.tvEdad)
//        val textView4: TextView = view.findViewById(R.id.tvPeso)
//        val textView5: TextView = view.findViewById(R.id.tvAltura)
        val btnConsultar: Button =view.findViewById(R.id.btnConsultar)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.btnConsultar.setOnClickListener {  onEditClick (user)}

        holder.textView.text = user.nombre
        holder.textView2.text = user.correo
//        holder.textView3.text = user.edad.toString()
//        holder.textView4.text = user.peso.toString()
//        holder.textView5.text = user.altura.toString()



    }

    override fun getItemCount(): Int = users.size

    fun updateData(newUsers: List<User>) {
        users = newUsers
        notifyDataSetChanged()
    }


}

package com.example.salud

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ModificarUsuario : AppCompatActivity() {


        private lateinit var dbHelper: SQLiteHelper
        private lateinit var adapter: ModificarUserAdapter
        private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_modificar_usuario)
        dbHelper = SQLiteHelper(this)
        val searchView: SearchView = findViewById(R.id.searchViewModificar)
        recyclerView = findViewById(R.id.recyclerViewModificar)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ModificarUserAdapter(
            emptyList(),
            onEditClick = { user -> editUser(user) })
        recyclerView.adapter = adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Cuando el usuario hace submit, no haremos nada por ahora
                return false
            }
            @SuppressLint("Range")
            override fun onQueryTextChange(newText: String?): Boolean {
                // Filtrar los usuarios según el texto ingresado
                val cursor = dbHelper.getAllUsers()
                val users = mutableListOf<User>()
                if (cursor.moveToFirst()) {
                    do {
                        val id = cursor.getInt(cursor.getColumnIndex(SQLiteHelper.COLUMN_ID))
                        val nombre = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_NOMBRE))
                        val correo = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_CORREO))
                        val edad = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_EDAD))
                        val peso = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_PESO))
                        val altura = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_ALTURA))




                        if (nombre.contains(newText!!, true)) {
                            users.add(User(id, nombre,correo, edad.toInt(),peso.toDouble(),altura.toDouble()))
                        }
                    } while (cursor.moveToNext())
                }

                // Actualizar el RecyclerView con los resultados de búsqueda
                adapter.updateData(users)
                return true
            }
        })

    } private fun editUser(user: User) {
        val intent = Intent(this, UpdateUsuario::class.java)

        intent.putExtra("USER_ID", user.id)

        intent.putExtra("USER_NAME", user.nombre)
        intent.putExtra("USER_MAIL", user.correo)
        intent.putExtra("USER_EDAD", user.edad)
        intent.putExtra("USER_PESO", user.peso)
        intent.putExtra("USER_ALTURA", user.altura)
        startActivity(intent)

    }

}

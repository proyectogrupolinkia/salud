package com.example.salud

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EliminarUsuario : AppCompatActivity() {


        private lateinit var dbHelper: SQLiteHelper
        private lateinit var adapter: EliminarUserAdapter
        private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_eliminar_usuario)
        dbHelper = SQLiteHelper(this)
        val searchView: SearchView = findViewById(R.id.searchViewEliminar)
        recyclerView = findViewById(R.id.recyclerViewEliminar)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = EliminarUserAdapter(
            emptyList(),
            onDeleteClick = { user -> deleteUser(user) })
        recyclerView.adapter = adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Cuando el usuario hace submit, no haremos nada por ahora
                return false
            }
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


    } private fun deleteUser(user: User) {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirmar eliminación")
        builder.setMessage("¿Está seguro que desea eliminar al usuario?")
        builder.setPositiveButton("Sí") { dialog, _ ->

        dbHelper = SQLiteHelper(this)

        dbHelper.deleteUser(user.id)
        Toast.makeText(this, "Usuario eliminado con nombre: ${user.nombre}", Toast.LENGTH_SHORT).show()

            dialog.dismiss()
        }
        builder.setNegativeButton("No") { dialog, _ ->
            // Cancelar la eliminación y cerrar el diálogo
            dialog.dismiss()
        }
        val dialogo = builder.create()
        dialogo.show()

    }

}

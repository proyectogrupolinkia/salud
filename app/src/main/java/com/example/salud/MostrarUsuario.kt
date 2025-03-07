package com.example.salud

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MostrarUsuario : AppCompatActivity() {
    private lateinit var dbHelper: SQLiteHelper
    private lateinit var adapter: MostrarUserAdapter
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_mostrar_usuario)
        dbHelper = SQLiteHelper(this)
        val searchView: SearchView = findViewById(R.id.searchView)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MostrarUserAdapter(emptyList(),onEditClick = { user -> editUser(user) },
            onUpdateClick = { user -> updateUser(user) },
            onDeleteClick = { user -> deleteUser(user) })
        recyclerView.adapter = adapter

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

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
                        if (correo.contains(newText!!, true)) {
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
        val intent = Intent(this, ConsultarUsuario::class.java)
        intent.putExtra("USER_NAME", user.nombre)
        intent.putExtra("USER_MAIL", user.correo)
        intent.putExtra("USER_EDAD", user.edad)
        intent.putExtra("USER_PESO", user.peso)
        intent.putExtra("USER_ALTURA", user.altura)

        val IMC = user.calculaIMC(user.peso,user.altura)
        intent.putExtra("USER_IMC",IMC)
        startActivity(intent)

    }

    private fun updateUser(user: User) {
        val intent = Intent(this, UpdateUsuario::class.java)

        intent.putExtra("USER_ID", user.id)

        intent.putExtra("USER_NAME", user.nombre)
        intent.putExtra("USER_MAIL", user.correo)
        intent.putExtra("USER_EDAD", user.edad)
        intent.putExtra("USER_PESO", user.peso)
        intent.putExtra("USER_ALTURA", user.altura)
        val IMC = user.calculaIMC(user.peso,user.altura)
        intent.putExtra("USER_IMC",IMC)
        startActivity(intent)

    }

    private fun deleteUser(user: User) {
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
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_home -> {
                // Regresa a la actividad principal
                val intent = Intent(this, MainActivity::class.java)
                // Opcional: Limpiar la pila de activities para evitar volver atrás
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
                true
            }

            R.id.action_back -> {
                // Finaliza la activity actual para volver a la anterior
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    }

package com.example.salud

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

class RegistrarUsuario : AppCompatActivity() {
    private lateinit var dbHelper: SQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_usuario)

        dbHelper = SQLiteHelper(this) //Invocamos al constructor del SQLite

        //editTexts

        val editTextNombre = findViewById<EditText>(R.id.etNombre)
        val editTextCorreo = findViewById<EditText>(R.id.etCorreo)
        val editTextEdad = findViewById<EditText>(R.id.etEdad)
        val editTextPeso = findViewById<EditText>(R.id.etPeso)
        val editTextAltura = findViewById<EditText>(R.id.etAltura)
        val buttonEnviar = findViewById<Button>(R.id.btnEnviar)

        //Barra del navegador

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        // Acciones cuando el botón es presionado

        buttonEnviar.setOnClickListener {

            // Obtener los datos de los campos de texto
            val nombre = editTextNombre.text.toString()
            val correo = editTextCorreo.text.toString()
            val edad = editTextEdad.text.toString()
            val peso = editTextPeso.text.toString()
            val altura = editTextAltura.text.toString()
            val regexCorreo = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()

            // Validar que todos los campos no estén vacíos y rellenados correctamente.
            if (nombre.isEmpty() || correo.isEmpty() || edad.isEmpty() || peso.isEmpty() || altura.isEmpty()) {
                Toast.makeText(this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT)
                    .show()

            } else if (!correo.matches(regexCorreo)) { //Verificamos el formato del correo
                Toast.makeText(
                    this,
                    "El correo electrónico no tiene un formato válido",
                    Toast.LENGTH_SHORT
                )
                    .show()
            } else if (edad.toInt() > 120) {//Verificamos la edad
                Toast.makeText(
                    this,
                    "La edad no es correcta, por favor, vuelve a introducirla.",
                    Toast.LENGTH_SHORT
                )
                    .show()
            } else if (peso.toInt() > 200) {//Verificamos el peso
                Toast.makeText(
                    this,
                    "El peso no es correcto, por favor, vuelve a introducirlo.",
                    Toast.LENGTH_SHORT
                )
                    .show()
            } else if (altura.toInt() > 250 || altura.toInt() < 20) {//Verificamos la altura
                Toast.makeText(
                    this,
                    "La altura no es correcta, por favor, vuelve a introducirla.",
                    Toast.LENGTH_SHORT
                )
                    .show()
            } else {
                // Si el correo no esta registrado, hacemos un insert y mostramos los datos en un Toast,
                // si no, indicamos que el mail ya existe.
                val result = dbHelper.insertUser(
                    nombre,
                    correo,
                    edad.toInt(),
                    peso.toDouble(),
                    altura.toDouble()
                )
                if (result) {
                    Toast.makeText(
                        this,
                        "Usuario insertado con nombre: $nombre",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this,
                        "El correo ya está registrado. No se registrará este usuario.",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }


        }

    }

    //ToolBar de navegacion.
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
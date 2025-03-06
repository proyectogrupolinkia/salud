package com.example.salud

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class UpdateUsuario : AppCompatActivity() {
    private lateinit var dbHelper: SQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_usuario)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        dbHelper = SQLiteHelper(this)

        val etViewNombrefijo = findViewById<TextView>(R.id.etNombrefijo)
        val etViewCorreofijo = findViewById<TextView>(R.id.etCorreofijo)
        val etViewEdadfijo = findViewById<TextView>(R.id.etEdadfijo)
        val etViewPesofijo = findViewById<TextView>(R.id.etPesofijo)
        val etViewAlturafijo = findViewById<TextView>(R.id.etAlturafijo)
        val btActualizar = findViewById<Button>(R.id.btActualizar)

        val ConsultaIDfijo= intent.getIntExtra("USER_ID",0)

        val ConsultaNombrefijo= intent.getStringExtra("USER_NAME")
        val ConsultaCorreofijo= intent.getStringExtra("USER_MAIL")
        val ConsultaEdadfijo= intent.getIntExtra("USER_EDAD",0)
        val ConsultaPesofijo= intent.getDoubleExtra("USER_PESO",0.0)
        val ConsultaAlturafijo= intent.getDoubleExtra("USER_ALTURA",0.0)

//        etViewNombrefijo.text=" "+ConsultaNombrefijo.toString()
//        etViewCorreofijo.text=" "+ConsultaCorreofijo.toString()
//        etViewEdadfijo.text=" "+ConsultaEdadfijo.toString()
//        etViewPesofijo.text=" "+ConsultaPesofijo.toString()+" Kg"
//        etViewAlturafijo.text=" "+ConsultaAlturafijo.toString()+" cms"

        etViewNombrefijo.text=ConsultaNombrefijo.toString()
        etViewCorreofijo.text=ConsultaCorreofijo.toString()
        etViewEdadfijo.text=ConsultaEdadfijo.toString()
        etViewPesofijo.text=ConsultaPesofijo.toString()
        etViewAlturafijo.text=ConsultaAlturafijo.toString()

        btActualizar.setOnClickListener {
            val nombreActualizado= etViewNombrefijo.text.toString()
            val correoActualizado= etViewCorreofijo.text.toString()
            val edadActualizado = etViewEdadfijo.text.toString()
            val pesoActualizado= etViewPesofijo.text.toString()
            val alturaActualizado= etViewAlturafijo.text.toString()
            if (nombreActualizado.isEmpty() || correoActualizado.isEmpty() || edadActualizado.isEmpty() || pesoActualizado.isEmpty() || alturaActualizado.isEmpty()) {
                Toast.makeText(this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT)
                    .show()
            } else {
                // Mostrar los datos en un Toast (o lo que desees hacer con ellos)
                 val result = dbHelper.updateUser(ConsultaIDfijo,nombreActualizado,correoActualizado,edadActualizado.toInt(),pesoActualizado.toDouble(),alturaActualizado.toDouble())
                 Toast.makeText(this, "Usuario actualizado con nombre: $nombreActualizado", Toast.LENGTH_SHORT).show()

            }




        }

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
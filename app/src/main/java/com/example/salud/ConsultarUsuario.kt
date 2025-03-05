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

class ConsultarUsuario : AppCompatActivity() {
    private lateinit var dbHelper: SQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultar_usuario)

        dbHelper = SQLiteHelper(this)

        val textViewNombrefijo = findViewById<TextView>(R.id.tvNombrefijo)
        val textViewCorreofijo = findViewById<TextView>(R.id.tvCorreofijo)
        val textViewEdadfijo = findViewById<TextView>(R.id.tvEdadfijo)
        val textViewPesofijo = findViewById<TextView>(R.id.tvPesofijo)
        val textViewAlturafijo = findViewById<TextView>(R.id.tvAlturafijo)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val ConsultaNombrefijo= intent.getStringExtra("USER_NAME")
        val ConsultaCorreofijo= intent.getStringExtra("USER_MAIL")
        val ConsultaEdadfijo= intent.getIntExtra("USER_EDAD",0)
        val ConsultaPesofijo= intent.getDoubleExtra("USER_PESO",0.0)
        val ConsultaAlturafijo= intent.getDoubleExtra("USER_ALTURA",0.0)

        textViewNombrefijo.text=" "+ConsultaNombrefijo.toString()
        textViewCorreofijo.text=" "+ConsultaCorreofijo.toString()
        textViewEdadfijo.text=" "+ConsultaEdadfijo.toString()
        textViewPesofijo.text=" "+ConsultaPesofijo.toString()+" Kg"
        textViewAlturafijo.text=" "+ConsultaAlturafijo.toString()+" cms"



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
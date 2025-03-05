package com.example.salud

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

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
}
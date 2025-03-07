package com.example.salud

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Botones del MainActivity
        val botonRegistrar = findViewById<Button>(R.id.buttonRegistrar)
        val botonMostrar = findViewById<Button>(R.id.buttonMostrar)
        val botonIMC = findViewById<Button>(R.id.buttonIMC)
        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

//Definimos las Activities a las que apuntan los botones.

        botonRegistrar.setOnClickListener {

            val intent = Intent(this, RegistrarUsuario::class.java)
            startActivity(intent)
        }

        botonMostrar.setOnClickListener {

            val intent = Intent(this, GestionarUsuario::class.java)
            startActivity(intent)
        }
        botonIMC.setOnClickListener {
            val intent = Intent(this, IMCActivity::class.java)
            startActivity(intent)
        }

    }


}




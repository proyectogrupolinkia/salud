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



        //Atributos
        val botonRegistrar = findViewById<Button>(R.id.buttonRegistrar)
        val botonMostrar = findViewById<Button>(R.id.buttonMostrar)
        //val botonModificar = findViewById<Button>(R.id.buttonModificar)
        //  val botonEliminar = findViewById<Button>(R.id.buttonEliminar)
        val botonIMC = findViewById<Button>(R.id.buttonIMC)
        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)



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
//        botonModificar.setOnClickListener {
//            val intent = Intent(this, ModificarUsuario::class.java)
//            startActivity(intent)
//        }
//
//        botonEliminar.setOnClickListener {
//            val intent = Intent (this, EliminarUsuario::class.java)
//            startActivity(intent)
        }




    }




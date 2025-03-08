package com.example.salud

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputBinding
import android.widget.Button
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    private var isFabOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Alejandro Menu FAB
        val fabMain: FloatingActionButton = findViewById(R.id.fab_menuOp)
        fabMain.setOnClickListener { view ->
            mostrarMenuFAB(view)
        }

        //Atributos
//        val botonRegistrar = findViewById<Button>(R.id.buttonRegistrar)
//        val botonMostrar = findViewById<Button>(R.id.buttonMostrar)
//        val botonModificar = findViewById<Button>(R.id.buttonModificar)
//        val botonEliminar = findViewById<Button>(R.id.buttonEliminar)
//        val botonIMC = findViewById<Button>(R.id.buttonIMC)
//        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
//        setSupportActionBar(toolbar)


//        botonRegistrar.setOnClickListener {
//
//            val intent = Intent(this, RegistrarUsuario::class.java)
//            startActivity(intent)
//        }
//
//        botonMostrar.setOnClickListener {
//
//            val intent = Intent(this, MostrarUsuario::class.java)
//            startActivity(intent)
//        }
//
//        botonModificar.setOnClickListener {
//            val intent = Intent(this, ModificarUsuario::class.java)
//            startActivity(intent)
//        }
//
//        botonEliminar.setOnClickListener {
//            val intent = Intent (this, EliminarUsuario::class.java)
//            startActivity(intent)
//        }
//        botonIMC.setOnClickListener {
//           val intent = Intent(this, IMCActivity::class.java)
//           startActivity(intent)
//       }
//


    }
    /**
     * Funcion que desplega todas las opciones programadas al presionar el menu FAB
     * @author Alejandro
     *
     * */
    private fun mostrarMenuFAB(anchorView: View) {
        val popupMenu = PopupMenu(this, anchorView)
        val inflater: MenuInflater = popupMenu.menuInflater
        inflater.inflate(R.menu.fab_menu, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.mRegistrar -> {
                    //Accion Registrar
                    val intent = Intent(this, RegistrarUsuario::class.java)
                    startActivity(intent)
                    true
                }
                R.id.mMostrar -> {
                    // Acción para la Opción Mostrar
                    val intent = Intent(this, MostrarUsuario::class.java)
                    startActivity(intent)
                    true
                }
                R.id.mModificar ->{
                    val intent = Intent (this, EliminarUsuario::class.java)
                    startActivity(intent)
                    true
                }
                R.id.mEliminar ->{
                    val intent = Intent (this, EliminarUsuario::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }



}
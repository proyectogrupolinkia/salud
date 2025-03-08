package com.example.salud

import android.content.Intent
import android.os.Bundle
import android.view.MenuInflater
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.PopupMenu
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
                R.id.mGestionar -> {
                    // Acción para la Opción Mostrar
                    val intent = Intent(this, GestionarUsuario::class.java)
                    startActivity(intent)
                    true
                }
                R.id.mIMC -> {
                    // Acción para la Opción Mostrar
                    val intent = Intent(this, IMCActivity::class.java)
                    startActivity(intent)
                    true
                }


                else -> false
            }
        }
        popupMenu.show()
    }
}




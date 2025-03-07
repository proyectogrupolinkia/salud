package com.example.salud

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class IMCActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc)

        val etPeso = findViewById<EditText>(R.id.etPeso)
        val etAltura = findViewById<EditText>(R.id.etAltura)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)
        val chartIMC = findViewById<BarChart>(R.id.chartIMC)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        btnCalcular.setOnClickListener {
            val pesoText = etPeso.text.toString()
            val alturaText = etAltura.text.toString()

            //Verificamos que se hayan completado los campos

            if (pesoText.isEmpty() || alturaText.isEmpty()) {
                tvResultado.text = "Por favor, complete ambos campos"
                return@setOnClickListener
            }

            val peso = pesoText.toDoubleOrNull()
            val altura = alturaText.toDoubleOrNull()

            if (peso == null || altura == null || altura <= 0) {
                tvResultado.text = "Ingrese valores numéricos válidos"
                return@setOnClickListener
            }

            //Calculamos IMC

            val imc = peso / (altura * altura)
            val resultado = when {
                imc < 18.5 -> "Bajo peso"
                imc in 18.5..24.9 -> "Peso normal"
                imc in 25.0..29.9 -> "Sobrepeso"
                else -> "Obesidad"
            }
            tvResultado.text = "IMC: %.2f\n$resultado".format(imc)

            //actualizar el grafico con el nuevo valor del IMC
            actualizarGrafico(chartIMC, imc)
        }
    }

    //Parte Gráfica en Diagrama de Barras

    private fun actualizarGrafico(chart: BarChart, imc: Double) {
        val valoresIMC = listOf(
            BarEntry(0f, 18.5f),  //bajo peso
            BarEntry(1f, 24.9f),  //normal
            BarEntry(2f, 29.9f),  //sobrepeso
            BarEntry(3f, imc.toFloat()) //IMC del usuario
        )

        val colores = listOf(
            android.graphics.Color.GREEN,    //bajo peso
            android.graphics.Color.YELLOW,   //normal
            android.graphics.Color.RED,  //sobrepeso
            android.graphics.Color.BLUE  //usuario
        )

        val etiquetas = listOf("Bajo", "Normal", "Sobrepeso", "Tú")

        val dataSet = BarDataSet(valoresIMC, "Clasificación IMC")
        dataSet.colors = colores

        val data = BarData(dataSet)
        chart.data = data
        chart.invalidate()

        //labels
        val xAxis = chart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.valueFormatter = IndexAxisValueFormatter(etiquetas)
        xAxis.granularity = 1f
        xAxis.setLabelCount(etiquetas.size)
    }

    //Toolkbar de navegación.
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



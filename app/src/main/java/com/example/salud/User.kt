package com.example.salud
//Constructor de User

data class User(val id: Int, val nombre: String, val correo: String, val edad: Int,val peso: Double, val altura: Double){
    //Método propio de calcular el IMC.

fun calculaIMC(peso: Double, altura: Double): Double {
    val imc = (peso/(altura*altura/10000))
    return imc
}

}
//
package com.example.interesapp.viewmodels

import android.icu.math.BigDecimal
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.interesapp.models.PrestamoState
import com.example.interesapp.views.calcularCuota
import java.math.RoundingMode

class PrestamoViewModel: ViewModel() {

    var state by mutableStateOf(PrestamoState())
        private set

    fun confirmaDialog() {
        state= state.copy(showAlert=false)
    }
    fun limpiar() {
        state=state.copy(
            montoPrestamo = "",
            cantCuotas = "",
            tasa = "",
            montoInteres = 0.0,
            montoCuota = 0.0

        )

    }

    fun onValue(value: String,campo: String) {
        Log.i( "melvin",campo)
        Log.i( "melvin", value )


        when( campo ){
            "montoPrestamo" -> state.copy(montoPrestamo=value)
            "cuotas" -> state = state.copy(cantCuotas=value)
            "tasa" -> state=state.copy(tasa=value)

        }


    }

    fun calcularTotal(montoPrestamo:Double,cantCuotas: Int, tasaInteresAnual: Double): Double {
        val res = cantCuotas *calcularCuota(montoPrestamo, cantCuotas,tasaInteresAnual)
        return java.math.BigDecimal(res).setScale(2, RoundingMode.HALF_UP).toDouble()

    }
    fun calcularCuota(montoPrestamo: Double, cantCuotas: Int, tasaInteresAnual: Double): Double {

        val tasaInteresMensual= tasaInteresAnual/12/100
        val cuota =montoPrestamo*tasaInteresAnual* Math.pow(1+tasaInteresMensual,cantCuotas.toDouble())/
                (Math.pow(1+tasaInteresMensual, cantCuotas.toDouble())-1)
        val cuotaRedondeada = java.math.BigDecimal(cuota).setScale(2, RoundingMode.HALF_UP).toDouble()
        return cuotaRedondeada


    }

    fun calcular(){
        val montoPrestamo = state.montoPrestamo
        val cantCuotas = state.cantCuotas
        val tasa =state.tasa
        if(montoPrestamo!=""&& cantCuotas!=""&& tasa!=""){
            state=state.copy(
                montoCuota = calcularCuota(montoPrestamo.toDouble(),cantCuotas.toInt(), tasa.toDouble()),
                montoInteres = calcularTotal(montoPrestamo.toDouble(),cantCuotas.toInt(), tasa.toDouble())

            )
        }else{
            state=state.copy(showAlert=true)
        }


}}


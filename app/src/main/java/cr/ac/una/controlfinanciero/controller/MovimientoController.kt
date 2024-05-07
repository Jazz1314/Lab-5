package cr.ac.una.controlfinanciero.controller

import android.util.Log
import cr.ac.una.controlfinanciero.entity.Movimiento
import cr.ac.una.controlfinancierocamera.service.MovimientoService

class MovimientoController {

    var movimientos: ArrayList<Movimiento> = arrayListOf()
    var movimientoService= MovimientoService()

    fun insertMovimiento(movimiento: Movimiento) {
        movimientos.add(movimiento)
    }

    fun getMovimientos(): List<Movimiento> {
        return movimientos
    }

    fun validarDecimales(amount: String): Boolean {

        if (amount.endsWith(".0")) {
            return true
        }

        val amountOfDecimals: Int

        try {
            amountOfDecimals = amount.substring(amount.indexOf('.')).length - 1
        } catch (e: Exception) {
            return true
        }

        return amountOfDecimals <= 2
    }
    suspend fun  listMovimientos():ArrayList<Movimiento>{
        return movimientoService.apiService.getItems().items as ArrayList<Movimiento>
    }
    fun showMovimientos() {
        for (movimiento in movimientos) {
            Log.d(
                "Movimiento",
                "Monto: ${movimiento.monto} Tipo: ${movimiento.tipo} Fecha: ${movimiento.fecha}"
            )
        }
    }
}
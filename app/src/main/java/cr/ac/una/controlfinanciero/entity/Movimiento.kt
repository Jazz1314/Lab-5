package cr.ac.una.controlfinanciero.entity
import java.io.Serializable
import java.util.Date
data class Movimiento(
    var monto : Double,
    var tipo: Int,
    var fecha :Date) : Serializable

fun leerEntero(mensaje: String): Int {
    while (true) {
        print(mensaje)
        val entrada = readlnOrNull()
        val numero = entrada?.toIntOrNull()
        if (numero != null && numero >= 0) {
            return numero
        } else {
            println("Entrada no válida. Introduce un número válido.")
        }
    }
}

fun leerDecimal(mensaje: String): Double {
    while (true) {
        print(mensaje)
        val entrada = readlnOrNull()
        val numero = entrada?.toDoubleOrNull()
        if (numero != null && numero >= 0) {
            return numero
        } else {
            println("Entrada no válida. Introduce un número decimal válido.")
        }
    }
}

val MONEDA = "$"

val inventario = mutableMapOf(
    "galletas" to 0,
    "muffins" to 0,
    "pasteles" to 0
)
val precios = mutableMapOf(
    "galletas" to 0.0,
    "muffins" to 0.0,
    "pasteles" to 0.0
)

fun mostrarInventario() {
    println("\nInventario actual:")
    inventario.forEach { (producto, cantidad) ->
        println("$producto: $cantidad unidades | Precio unitario: $MONEDA${precios[producto]}")
    }
}

fun reabastecerArticulo() {
    print("¿Qué artículo deseas reabastecer? (galletas/muffins/pasteles): ")
    val producto = readln()?.lowercase() ?: ""
    if (producto in inventario) {
        val cantidad = leerEntero("¿Cuántas unidades deseas añadir? ")
        val precio = leerDecimal("Nuevo precio unitario para $producto: ")
        inventario[producto] = inventario[producto]!! + cantidad
        precios[producto] = precio
        println("Reabastecimiento completado.")
    } else {
        println("Artículo no encontrado.")
    }
}

fun venderArticulo() {
    print("¿Qué artículo deseas vender? (galletas/muffins/pasteles): ")
    val producto = readln()?.lowercase() ?: ""
    if (producto in inventario) {
        val cantidad = leerEntero("¿Cuántas unidades deseas vender? ")
        val stockActual = inventario[producto]!!
        if (cantidad > stockActual) {
            println("No hay suficiente stock disponible.")
        } else {
            inventario[producto] = stockActual - cantidad
            val ingreso = cantidad * precios[producto]!!
            println("Venta realizada. Ingresos generados: $MONEDA$ingreso")
        }
    } else {
        println("Artículo no encontrado.")
    }
}

fun main() {
    while (true) {
        println("\nMenú de gestión de inventario:")
        println("1. Ver niveles de inventario")
        println("2. Reabastecer artículos")
        println("3. Vender artículos")
        println("4. Salir")
        when (leerEntero("Elige una opción: ")) {
            1 -> mostrarInventario()
            2 -> reabastecerArticulo()
            3 -> venderArticulo()
            4 -> {
                println("Saliendo del programa...")
                break
            }
            else -> println("Opción no válida.")
        }
    }
}

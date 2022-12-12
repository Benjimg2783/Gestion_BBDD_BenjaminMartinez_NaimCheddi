import kotlin.system.exitProcess

class Gui(private val gest: GestorBBDD? = GestorBBDD.getInstance()) {
    init {
        if (gest?.getConnection() == false) {
            println("Ha habido un error en la conexion")
            exitProcess(404)

        }

    }

    fun welcome() {

        var user = ""
        var pass = ""
        var answer = ""
        println("¿Dispone de un Usuario en el sistema? Y/N")
        answer = readln()
        if (answer.uppercase() == "Y" || answer.uppercase() == "YES" || answer.uppercase() == "S" || answer.uppercase() == "SI") {
            println("Introduzca el nombre")
            user = readln()
            println("Introduzca la contraseña")
            pass = readln()
            if (gest!!.selectUser(user, pass)) {
                println("Usuario Correcto")
            } else {
                println("El usuario o contraseña no son correctos o no existe en el sistema, ¿Que desea hacer? \n1.Volver a intentarlo\n2.Registrar el usuario")
                val registro = readln()
                var check = false
                while (!check) {
                    when (registro) {
                        "1" -> {
                            println("Introduce el usuario")
                            user = readln()
                            println("Introduce la contraseña")
                            pass = readln()
                            if (gest!!.selectUser(user, pass)) {
                                check = true
                            } else {
                                println("Usuario no encontrado")
                            }
                        }
                        "2" -> {
                            if (gest!!.insertUser(user, pass)) {
                                println("¡Usuario registrado con exito!")
                            } else {
                                println("El usuario ya existe o caracteres invalidos")
                            }

                        }
                        else -> {
                            println("Seleccione 1 o 2")
                        }
                    }
                }

            }
        } else {
            println("Desea registrar un nuevo usuario?Y/N")
            answer = readln()
            if (answer.uppercase() == "Y" || answer.uppercase() == "YES" || answer.uppercase() == "S" || answer.uppercase() == "SI") {
                println("Introduce el usuario")
                user = readln()
                println("Introduce la contraseña")
                pass = readln()
                if (gest!!.insertUser(user, pass)) {
                    println("¡Usuario registrado con exito!")
                } else {
                    println("El usuario ya existe o caracteres invalidos")
                }
            } else println("Usuario no encontrado")
        }
    }

    fun inventory() {
        var comprobar = false
        while (!comprobar) {
            println("MENU INVENTARIO")
            println(
                "1. Ver todos los productos \n" +
                        "2. Seleccionar un productto \n" +
                        "3. Buscar un producto por su precio \n" +
                        "4. Introducir un producto \n" +
                        "5. Eliminar un producto \n" +
                        "6. Actualizar el precio de un producto \n" +
                        "7. Actualizar la cantidad de un producto \n" +
                        "8. Salir"
            )
            val select = readln()
            when (select) {
                "1" -> {
                    gest?.selectAllProducts()
                }
                "2" -> {
                    println("Cual es el nombre del producto?")
                    val name = readln()
                    println(gest?.selectProduct(name))
                }
                "3" -> {
                    println("Cual es el precio del producto?")
                    var precio = 0.0
                    try {
                        precio = readln().toDouble()
                    } catch (e: java.lang.Exception) {
                        println("El precio debe ser double, EJ: 3.00")
                    }
                    gest?.selectProductosByPrecio(precio)

                }
                "4" -> {
                    println("Que nombre tiene el producto?")
                    val name = readln()
                    println("Que precio tiene el producto (EJ: 100.00)")
                    val precio = 0.0
                    try {
                        val precio = readln().toDouble()
                    } catch (e: java.lang.Exception) {
                        println("Error al leer el formato, siga el ejemplo")
                    }
                    println("Cantidad del producto (EJ: 20)")
                    var cant = 0
                    try {
                        cant = readln().toInt()
                    } catch (e: java.lang.Exception) {
                        println("Error al leer el formato, siga el ejemplo")
                    }
                    println("Descripcion del producto")
                    val desc = readln()
                    if(gest?.insertProduct(name, precio, cant, desc)==false) println("Error al insertar, vuelva a intentarlo")
                }
                "5" -> {
                    println("Introduzca el nombre del producto que desea eliminar")
                    val name = readln()
                    if (gest?.deleteProduct(name) == false) {
                        println("No se ha encontrado el producto, vuelva a intentarlo")
                    }

                }
                "6" -> {
                    println("Introduzca el nombre del producto que desea actualizar")
                    val name = readln()
                    println("Introduzca el precio que desea que tenga el producto")
                    var price = 0.0
                    try {
                        price = readln().toDouble()
                        if(gest?.updateProductPrice(name,price)==false){
                            println("No se ha encontrado $name")
                        }
                    } catch (e: java.lang.Exception) {
                        println("Error al actualizar")
                    }
                }
                "7" -> {
                    println("Introduzca el nombre del producto que desea actualizar")
                    val name = readln()
                    println("Introduzca la cantidad que desea que tenga el producto")
                    var cantidad = 0
                    try {
                        cantidad = readln().toInt()
                        if(gest?.updateProductAmount(name,cantidad)==false){
                            println("No se ha encontrado $name")
                        }
                    } catch (e: java.lang.Exception) {
                        println("Error al actualizar")
                    }
                }
                "8" -> {comprobar = true}

            }
        }
    }
}
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
            }else{
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
                            }else{
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
                            println("Seleccione 1 o 2")}
                    }
                }

            }
        } else {
            println("Desea registrar un nuevo usuario?Y/N")
            answer= readln()
            if (answer.uppercase() == "Y" || answer.uppercase() == "YES" || answer.uppercase() == "S" || answer.uppercase() == "SI"){
                println("Introduce el usuario")
                user = readln()
                println("Introduce la contraseña")
                pass = readln()
                if (gest!!.insertUser(user, pass)) {
                    println("¡Usuario registrado con exito!")
                } else {
                    println("El usuario ya existe o caracteres invalidos")
                }
            }else println("Usuario no encontrado")
        }
    }
}
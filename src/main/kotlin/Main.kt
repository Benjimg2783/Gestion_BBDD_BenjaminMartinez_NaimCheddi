fun main(args: Array<String>) {
    val gestorBBDD=GestorBBDD.getInstance()
    println("Tienes un usuario registrado en el sistema. y/n")
    val registro= readln()
    if (registro.lowercase()=="y"||registro.lowercase()=="yes"||registro.lowercase()=="s"||registro.lowercase()=="si"){
        println("Escriba su nombre de usuario")
        val user = readln()
        println("Escriba su contraseña")
        val pass= readln()
    }
    else if (registro.lowercase()=="n"||registro.lowercase()=="no"){
        println("Escriba su nombre de usuario")
        val user = readln()
        println("Escriba su contraseña")
        val pass= readln()
    }
    else{
        println("Error de escritura vuelva intentarlo, prueba a escribir y/n/yes/no/s/si")
    }
}
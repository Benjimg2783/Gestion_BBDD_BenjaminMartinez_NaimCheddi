fun main() {
    val gestorBBDD=GestorBBDD.getInstance()
    if (gestorBBDD != null) {
        gestorBBDD.getConnection()
        if(gestorBBDD.comprobadorUsuario("Benjamin","1234")){
            /*TODO*/
        } else{
            println("El usuario no es correcto")}
    }
}
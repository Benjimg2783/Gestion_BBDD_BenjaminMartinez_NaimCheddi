import java.sql.Connection
import java.sql.DriverManager

class GestorBBDD {
    companion object {
        @Volatile
        private var instance: GestorBBDD? = null

        fun getInstance(): GestorBBDD? {
            if (instance == null) {
                instance = GestorBBDD()
                println("[CONEXION REALIZADA]")
            } else (println("[CONEXION EXISTENTE]"))
            return instance
        }
    }

    private var conexion: Connection? = null
    private val url = "jdbc:mysql://localhost/"
    private val bd: String = "bd_proyectoTema2"
    private val user: String = "root"
    private val pass: String = ""
    fun getConnection() {
        conexion = DriverManager.getConnection(url + bd, user, pass)
    }
    private fun selectUser(user:String, pass:String): Boolean {
        var comprobador=true
        val ps = conexion!!.prepareStatement(Consultas.selectUser)
        ps.setString(1,user)
        ps.setString(2,pass)
        val rs = ps.executeQuery()
        rs.next()
        try {
            rs.getString(1)+rs.getString(2)
        }catch (_:Exception){comprobador=false}
        return comprobador
    }
    private fun insertUser(user: String, pass: String):Boolean{
        val ps =conexion!!.prepareStatement(UpdateUsers.insertUser)
        ps.setString(1,user)
        ps.setString(2,pass)
        var rs=-1
        try { rs = ps.executeUpdate() }catch (e:Exception){
            println("Usuario ya existente en el sistema")}
        return rs>0
    }
    fun comprobadorUsuario(user: String,password:String): Boolean {
        var comprobador = false
        var usuario = user
        var contrasenya = password
        if (selectUser(usuario,contrasenya)){
            println("Usuario correcto")
            comprobador = true
        }else {
            println("El usuario o contraseña no son correctos o no existe en el sistema, ¿Desea volver a intentarlo? y/n")
            var registro = readln()
            if (registro.lowercase() == "y" || registro.lowercase() == "yes" || registro.lowercase() == "s" || registro.lowercase() == "si") {
                println("Introduce el usuario")
                usuario = readln()
                println("Introduce la contraseña")
                contrasenya = readln()
                comprobadorUsuario(usuario, contrasenya)
                comprobador=true
            } else if (registro.lowercase() == "n" || registro.lowercase() == "no") {
                println("¿Desea registrar este usuario en el sistema? y/n")
                registro = readln()
                if (registro.lowercase() == "y" || registro.lowercase() == "yes" || registro.lowercase() == "s" || registro.lowercase() == "si") {
                    insertUser(usuario, contrasenya)
                    comprobador = true
                }
            }
        }
        return comprobador
    }
}
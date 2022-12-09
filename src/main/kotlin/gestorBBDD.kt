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
    fun getConnection() : Boolean {
        return try {
            conexion = DriverManager.getConnection(url + bd, user, pass)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun selectUser(user: String, pass: String): Boolean {
        var comprobador = true
        val ps = conexion!!.prepareStatement(Consultas.selectUser)
        ps.setString(1, user)
        ps.setString(2, pass)
        val rs = ps.executeQuery()
        rs.next()
        try {
            rs.getString(1) + rs.getString(2)
        } catch (_: Exception) {
            comprobador = false
        }
        return comprobador
    }

    fun insertUser(user: String, pass: String): Boolean {
        val ps = conexion!!.prepareStatement(UpdateUsers.insertUser)
        ps.setString(1, user)
        ps.setString(2, pass)
        var rs = -1
        try {
            rs = ps.executeUpdate()
        } catch (e: Exception) {
            println("Usuario ya existente en el sistema")
        }
        return rs > 0
    }
    
}

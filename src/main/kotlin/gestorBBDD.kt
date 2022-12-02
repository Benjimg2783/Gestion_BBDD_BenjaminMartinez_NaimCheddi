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
    private val bd: String = "bd_pruebatema2"
    private val user: String = "root"
    private val pass: String = ""
    fun getConnection() {
        conexion = DriverManager.getConnection(url + bd, user, pass)
    }
}
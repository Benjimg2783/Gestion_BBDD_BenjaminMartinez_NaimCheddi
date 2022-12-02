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
    fun selectUser(user:String,pass:String):Pair<String,String>{
        val ps = conexion!!.prepareStatement(Consultas.selectUser)
        ps.setString(1,user)
        ps.setString(2,pass)
        val rs=ps.executeQuery()
        return Pair(rs.getString(1),rs.getString(2))

    }
    fun insertUser(user: String,pass: String):Boolean{
        val ps =conexion!!.prepareStatement(UpdateUsers.insertUser)
        ps.setString(1,user)
        ps.setString(2,pass)
        val rs=ps.executeUpdate()
        return rs>0
    }
}
fun main(){
    val gestorBBDD=GestorBBDD.getInstance()
    if (gestorBBDD != null) {
        gestorBBDD.getConnection()
        println(gestorBBDD.selectUser("Benjamin","1234"))
    }

}
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
    fun getConnection(): Boolean {
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
        val ps = conexion!!.prepareStatement(Modificaciones.insertUser)
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
    fun selectProduct(producto:String): String {
        var comprobador = true
        val ps = conexion!!.prepareStatement(Consultas.selectProductoByName)
        ps.setString(1,producto)
        val rs = ps.executeQuery()
        rs.next()
        return rs.getString(1)+ "\n" + rs.getDouble(2) +"\n" + rs.getInt(3)+"\n" + rs.getString(4)+ "\n"+ "****************"
    }
    fun selectAllProducts(){
        val ps=conexion!!.prepareStatement(Consultas.selectProductos)
        val rs=ps.executeQuery()
        while (rs.next()){
            println(rs.getString(1))
            println(rs.getDouble(2))
            println(rs.getInt(3))
            println(rs.getString(4))
        }
        println("****************")
    }
    fun selectProductosByPrecio(precio: Double){
        val ps=conexion!!.prepareStatement(Consultas.selectProductosByPrecio)
        ps.setDouble(1,precio)
        val rs=ps.executeQuery()
        while (rs.next()){
            println(rs.getString(1))
            println(rs.getDouble(2))
            println(rs.getInt(3))
            println(rs.getString(4))
        }
        println("****************")
    }
    fun insertProduct(producto: String,precio:Double,cantidad:Int,descripcion:String):Boolean{
        val ps = conexion!!.prepareStatement(Modificaciones.insertProduct)
        ps.setString(1, producto)
        ps.setInt(3, cantidad)
        ps.setDouble(2,precio)
        ps.setString(4,descripcion)
        var rs = -1
        try {
            rs = ps.executeUpdate()
        } catch (e: Exception) {
            println("Producto ya existente en el sistema")
        }
        return rs > 0
    }
    fun deleteProduct(producto: String):Boolean{
        val ps = conexion!!.prepareStatement(Modificaciones.deleteProduct)
        ps.setString(1,producto)
        var rs=-1
        try {
            rs = ps.executeUpdate()
        } catch (e: Exception) {
            println("Error a la hora de la eliminación de $producto")
        }
        return rs > 0
    }
    fun updateProductPrice(producto: String,precio: Double):Boolean{
        val ps = conexion!!.prepareStatement(Modificaciones.updateProductPrice)
        ps.setDouble(1,precio)
        ps.setString(2,producto)
        var rs=-1
        try {
            rs = ps.executeUpdate()
        } catch (e: Exception) {
            println("Error en la actualización del precio de $producto")
        }
        return rs > 0

    }
    fun updateProductAmount(producto: String,cantidad: Int):Boolean{
        val ps = conexion!!.prepareStatement(Modificaciones.updateProductAmount)
        ps.setInt(1,cantidad)
        ps.setString(2,producto)
        var rs=-1
        try {
            rs = ps.executeUpdate()
        } catch (e: Exception) {
            println("Error en la actualización del precio de $producto")
        }
        return rs > 0
    }
}

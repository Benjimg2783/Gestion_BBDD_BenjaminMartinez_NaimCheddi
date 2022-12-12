import java.sql.Connection
import java.sql.DriverManager

/**
 * La clase GestorBBDD instancia la base de datos e implementa diferentes metodos para su
 * respectivo uso.
 * La Clase solo podrá ser instanciada una vez.
 */
class GestorBBDD {
    /**
     * El companion object introducimos por seguridad que la clase solo se pueda instanciar una vez.
     */
    companion object {
        @Volatile
        private var instance: GestorBBDD? = null

        fun getInstance(): GestorBBDD? {
            if (instance == null) {
                instance = GestorBBDD()
                println("[INSTANCIA DE LA BASE DE DATOS CREADA]")
            } else (println("[INSTANCIA EXISTENTE]"))
            return instance
        }
    }

    private var conexion: Connection? = null
    private val url = "jdbc:mysql://localhost/"
    private val bd: String = "bd_proyectoTema2"
    private val user: String = "root"
    private val pass: String = ""

    /**
     * El método getConnection() establece la conexión con la base de datos.
     * @return `true` si la conexión se realiza correctamente.
     */
    fun getConnection(): Boolean {
        return try {
            conexion = DriverManager.getConnection(url + bd, user, pass)
            true
        } catch (e: Exception) {
            false
        }
    }

    /**
     * El método selectUser() permite comprobar si existe el usuario.
     * @param user El usuario solicitado.
     * @param pass La contraseña solicitada.
     * @return `true`, si el user y el pass son correctos.
     */
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

    /**
     * El método insertUser() inserta un usuario en la base de datos.
     * @param user usuario solicitado.
     * @param pass Contraseña solicitada.
     * @return Devuelve `true` si se ha insertado correctamente.
     */
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

    /**
     * El método selectProduct() Nos permite buscar un producto por su nombre.
     * @param producto Nombre del producto.
     * @return Nos devuelve un String con sus atributos.
     */
    fun selectProduct(producto: String): String {
        val ps = conexion!!.prepareStatement(Consultas.selectProductoByName)
        ps.setString(1, producto)
        val rs = ps.executeQuery()
        rs.next()
        return try {
            rs.getString(1) + "\n" + rs.getDouble(2) + "\n" + rs.getInt(3) + "\n" + rs.getString(4) + "\n" + "****************"
        } catch (e: Exception) {
            return "No se ha encontrado el producto"
        }
    }

    /**
     * El método selectAllProducts() Nos permite mostrar todos los productos del inventario.
     */
    fun selectAllProducts() {
        val ps = conexion!!.prepareStatement(Consultas.selectProductos)
        val rs = ps.executeQuery()
        while (rs.next()) {
            println(rs.getString(1))
            println(rs.getDouble(2))
            println(rs.getInt(3))
            println(rs.getString(4))
        }
        println("****************")
    }

    /**
     * El método selectProductsByPrecio nos permite buscar un producto por su precio.
     * @param precio El precio del producto buscado.
     */
    fun selectProductosByPrecio(precio: Double) {
        val ps = conexion!!.prepareStatement(Consultas.selectProductosByPrecio)
        ps.setDouble(1, precio)
        val rs = ps.executeQuery()
        while (rs.next()) {
            println(rs.getString(1))
            println(rs.getDouble(2))
            println(rs.getInt(3))
            println(rs.getString(4))
        }
        println("****************")
    }

    /**
     * El método insertProduct() nos permite añadir un producto a la base de datos.
     * @param producto Nombre del producto a agregar.
     * @param precio Precio del producto a agregar.
     * @param cantidad Cantidad del producto a agregar.
     * @param descripcion Descripción del producto a añadir.
     * @return `true`, Si se realiza la inserción del producto.
     */
    fun insertProduct(producto: String, precio: Double, cantidad: Int, descripcion: String): Boolean {
        val ps = conexion!!.prepareStatement(Modificaciones.insertProduct)
        ps.setString(1, producto)
        ps.setInt(3, cantidad)
        ps.setDouble(2, precio)
        ps.setString(4, descripcion)
        var rs = -1
        try {
            rs = ps.executeUpdate()
        } catch (e: Exception) {
            println("Producto ya existente en el sistema")
        }
        return rs > 0
    }

    /**
     * La función deleteProduct() Nos permite eliminar un producto del inventario.
     * @param producto Nombre del producto a borrar.
     * @return `true`, Si el producto ha sido borrado correctamente.
     */
    fun deleteProduct(producto: String): Boolean {
        val ps = conexion!!.prepareStatement(Modificaciones.deleteProduct)
        ps.setString(1, producto)
        var rs = -1
        try {
            rs = ps.executeUpdate()
        } catch (e: Exception) {
            println("Error a la hora de la eliminación de $producto")
        }
        return rs > 0
    }

    /**
     * El método updateProductPrice nos permite modificar el precio de un producto.
     * @param producto Nombre del producto que deseamos modificar.
     * @param precio Nuevo precio del producto.
     * @return Nos devuelve `true` si el producto ha sido modificado correctamente.
     */
    fun updateProductPrice(producto: String, precio: Double): Boolean {
        val ps = conexion!!.prepareStatement(Modificaciones.updateProductPrice)
        ps.setDouble(1, precio)
        ps.setString(2, producto)
        var rs = -1
        try {
            rs = ps.executeUpdate()
        } catch (e: Exception) {
            println("Error en la actualización del precio de $producto")
        }
        return rs > 0

    }

    /**
     * El método updateProductAmount nos permite modificar la cantidad de un producto.
     * @param producto Nombre del producto que deseamos modificar.
     * @param cantidad Nuevo cantidad del producto.
     * @return Nos devuelve `true` si el producto ha sido modificado correctamente.
     */
    fun updateProductAmount(producto: String, cantidad: Int): Boolean {
        val ps = conexion!!.prepareStatement(Modificaciones.updateProductAmount)
        ps.setInt(1, cantidad)
        ps.setString(2, producto)
        var rs = -1
        try {
            rs = ps.executeUpdate()
        } catch (e: Exception) {
            println("Error en la actualización del precio de $producto")
        }
        return rs > 0
    }
}

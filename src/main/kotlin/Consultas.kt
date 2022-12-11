object Consultas {
    const val selectUser= "SELECT USER,PASSWORD FROM USERS WHERE USER=? AND PASSWORD=?"
    const val selectProductos="SELECT * FROM INVENTARIO"
    const val selectProductoByName= "SELECT * FROM INVENTARIO WHERE PRODUCTO=?"
    const val selectProductosByPrecio= "SELECT * FROM INVENTARIO WHERE PRECIO=?"
}
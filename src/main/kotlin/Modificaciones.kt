object Modificaciones {
    const val insertUser= "INSERT INTO USERS VALUE(?,?)"
    const val insertProduct="INSERT INTO INVENTARIO VALUES(?,?,?,?)"
    const val updateProductPrice="UPDATE INVENTARIO SET PRECIO=? WHERE PRODUCTO=?"
    const val updateProductAmount="UPDATE INVENTARIO SET CANTIDAD=? WHERE PRODUCTO=?"
    const val deleteProduct="DELETE FROM INVENTARIO WHERE PRODUCTO=?"
}
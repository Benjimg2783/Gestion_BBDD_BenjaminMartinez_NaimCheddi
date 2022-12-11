fun main(args: Array<String>) {
    val gestor=GestorBBDD.getInstance()
    val gui = Gui(gestor)
    gui.welcome()
    if (gestor!=null){
        gestor.insertProduct("pan", 0.35, 10, "")
        println(gestor.selectProduct("gambas"))
        gestor.selectAllProducts()
        gestor.deleteProduct("pan")
        gestor.selectAllProducts()
        gestor.updateProductPrice("gambas", 9.50)
        gestor.updateProductAmount("gambas", 10)
        gestor.selectAllProducts()
    }

}
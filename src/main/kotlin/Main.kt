fun main(args: Array<String>) {
    val gestor=GestorBBDD.getInstance()
    val gui = Gui(gestor)
    gui.welcome()
    gui.inventory()
    
}
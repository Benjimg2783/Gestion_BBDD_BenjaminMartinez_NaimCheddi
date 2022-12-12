fun main(args: Array<String>) {
    val gestor = GestorBBDD.getInstance()
    val gui = Gui(gestor)
    if (gui.welcome()) gui.inventory()


}
fun main() {
    val gestor = GestorBBDD.getInstance()
    val gui = Gui(gestor)
    if (gui.welcome()) gui.inventory()


}
package christmas.model

class Menus {
    private val menus: List<Menu> = loadMenus()

    private fun loadMenus(): List<Menu> {
        return listOf(
            Menu(MenuType.APPETIZER, "양송이수프", 6000),
            Menu(MenuType.APPETIZER, "타파스", 5500),
            Menu(MenuType.APPETIZER, "시저샐러드", 8000),
            Menu(MenuType.MAIN, "티본스테이크", 55000),
            Menu(MenuType.MAIN, "바비큐립", 54000),
            Menu(MenuType.MAIN, "해산물파스타", 35000),
            Menu(MenuType.MAIN, "크리스마스파스타", 25000),
            Menu(MenuType.DESSERT, "초코케이크", 15000),
            Menu(MenuType.DESSERT, "아이스크림", 5000),
            Menu(MenuType.DRINK, "제로콜라", 3000),
            Menu(MenuType.DRINK, "레드와인", 60000),
            Menu(MenuType.DRINK, "샴페인", 25000)
        )
    }

    fun findByName(name: String): Menu? {
        return menus.find { it.name == name }
    }

    fun findAll(): List<Menu> {
        return menus
    }
}
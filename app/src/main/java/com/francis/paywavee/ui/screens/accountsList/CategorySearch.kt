package com.francis.paywavee.ui.screens.accountsList

enum class CategorySearch() {
    All,
    Utilities,
    Rent,
    Food,
    Cloths,
    Entertainment,
    Shopping,
    Others;

    companion object {
        fun getOptions(): List<String>{
            val options = mutableListOf<String>()
            values().forEach { category ->
                options.add(category.name)
            }
            return options
        }
    }
}
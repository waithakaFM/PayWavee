package com.francis.paywavee.model

enum class Category() {
    Utilities,
    Rent,
    Food,
    Cloths,
    Entertainment,
    Shopping,
    Others;

    companion object {
        fun getByName( name: String?): Category{
            values().forEach { category ->
                if (name == category.name)
                    return category
            }
            return Utilities
        }

        fun getOptions(): List<String>{
            val options = mutableListOf<String>()
            values().forEach { category ->
                options.add(category.name)
            }
            return options
        }
    }
}
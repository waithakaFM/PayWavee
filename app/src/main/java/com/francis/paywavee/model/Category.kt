package com.francis.paywavee.model

enum class Category {
    Others,
    Utilities,
    Rent,
    Food,
    Cloths;

    companion object {
        fun getByName( name: String?): Category{
            values().forEach { category ->
                if (name == category.name)
                    return category
            }
            return Others
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
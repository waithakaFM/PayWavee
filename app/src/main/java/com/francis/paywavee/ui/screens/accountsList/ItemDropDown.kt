package com.francis.paywavee.ui.screens.accountsList

enum class ItemDropDown(val title: String) {
    Edit("Edit before paying"),
    Favorite("Make As Favorite"),
    Delete("Delete");

    companion object {
        fun getByTitle(title: String): ItemDropDown {
            values().forEach { action -> if (title == action.title) return action }

            return Edit
        }

        fun getOptions(hasEditOption: Boolean): List<String> {
            val options = mutableListOf<String>()
            values().forEach { taskAction ->
                if (hasEditOption || taskAction != Edit) {
                    options.add(taskAction.title)
                }
            }
            return options
        }
    }
}
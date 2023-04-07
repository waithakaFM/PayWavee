package com.francis.paywavee.model

import com.google.firebase.firestore.DocumentId

data class Item(
    @DocumentId val id: String = "",
    val category: String = "",
    val entity: String = "",
    val payBill: String ="",
    val accountNumber: String = "",
    val phoneNumber: String = "",
    val completed: Boolean = false
)

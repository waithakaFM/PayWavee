package com.francis.paywavee.model

import com.google.firebase.firestore.DocumentId

data class Transaction(
    @DocumentId val id: String = "",
    val category: String = "",
    val amount: Int = 0
)

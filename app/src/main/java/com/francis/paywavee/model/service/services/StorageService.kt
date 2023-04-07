package com.francis.paywavee.model.service.services

import com.francis.paywavee.model.Item
import com.francis.paywavee.model.Transaction
import kotlinx.coroutines.flow.Flow


interface StorageService {

    val items: Flow<List<Item>>
    val transaction: Flow<List<Transaction>>

    suspend fun getItem(itemId: String): Item?
    fun queryItem(category: String): Flow<List<Item>>
    suspend fun save(item: Item): String
    suspend fun update(item: Item)
    suspend fun delete(itemId: String)
    suspend fun deleteAllForUser(userId: String)

    suspend fun saveTransaction(transaction: Transaction): String
    fun queryTransaction(category: String): Flow<List<Transaction>>
}
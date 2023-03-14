package com.francis.paywavee.model.service.services

import com.francis.paywavee.model.Item
import kotlinx.coroutines.flow.Flow


interface StorageService {

    val items: Flow<List<Item>>

    suspend fun getItem(itemId: String): Item?

    suspend fun save(item: Item): String
    suspend fun update(item: Item)
    suspend fun delete(itemId: String)
    suspend fun deleteAllForUser(userId: String)
}
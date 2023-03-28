package com.francis.paywavee.model.service.implimentation

import com.francis.paywavee.model.Item
import com.francis.paywavee.model.service.services.AccountService
import com.francis.paywavee.model.service.services.StorageService
import com.francis.paywavee.model.service.trace
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.asDeferred
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StorageServiceImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: AccountService
): StorageService {

    /**
     * For Cloud Firestore, you will add a snapshot listener to the Firestore
     * collection that stores the documents that represent the item displayed
     * in PayWave
     */

    override val items: Flow<List<Item>>
        get() =
            auth.currentUser.flatMapLatest { user ->
                currentCollection(user.id)
                    .orderBy("entity")
                    .snapshots()
                    .map { snapshot -> snapshot.toObjects() }
            }

    /**
     * Gets the item to update
     */
    override suspend fun getItem(itemId: String): Item? =
        currentCollection(auth.currentUserId)
            .document(itemId)
            .get()
            .await()
            .toObject()

    /**
     * Query Items from the database
     */
    override fun queryItem(category: String): Flow<List<Item>> {
        return currentCollection(auth.currentUserId)
            .whereEqualTo("category", category)
            .snapshots()
            .map { snapshot -> snapshot.toObjects() }
    }


    /**
     * save the items into fireStore
     */
    override suspend fun save(item: Item): String =
        trace(SAVE_ITEM_TRACE) {
            currentCollection(auth.currentUserId)
                .add(item)
                .await()
                .id
        }

    /**
     * Updates the item to update
     */
    override suspend fun update(item: Item): Unit =
        trace(UPDATE_ITEM_TRACE){
            currentCollection(auth.currentUserId)
                .document(item.id)
                .set(item)
                .await()
        }

    /**
     * Delete the selected item
     */
    override suspend fun delete(itemId: String) {
        currentCollection(auth.currentUserId)
            .document(itemId)
            .delete()
            .await()
    }

    /**
     * Deletes all the users collection after he has logged out the app
     */
    // TODO: It's not recommended to delete on the client:
    // https://firebase.google.com/docs/firestore/manage-data/delete-data#kotlin+ktx_2
    override suspend fun deleteAllForUser(userId: String) {
        val matchingTasks = currentCollection(userId).get().await()
        matchingTasks.map { it.reference.delete().asDeferred() }.awaitAll()
    }


    private fun currentCollection(uid: String): CollectionReference =
        firestore.collection(USER_COLLECTION)
            .document(uid)
            .collection(ITEM_COLLECTION)

    companion object {
        private const val USER_COLLECTION = "users"
        private const val ITEM_COLLECTION = "items"
        private const val SAVE_ITEM_TRACE = "saveItem"
        private const val UPDATE_ITEM_TRACE = "updateItem"
    }
}
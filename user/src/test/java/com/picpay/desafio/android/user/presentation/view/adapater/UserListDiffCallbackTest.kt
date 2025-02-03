package com.picpay.desafio.android.user.presentation.view.adapater

import com.picpay.desafio.android.user.presentation.model.UserModel
import com.picpay.desafio.android.user.presentation.view.adapter.UserListDiffCallback
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class UserListDiffCallbackTest {

    @Test
    fun `should return true when items are the same`() {
        val oldList = listOf(
            UserModel(id = 1, name = "name", username = "username", img = "img"),
            UserModel(id = 2, name = "name2", username = "username2", img = "img2"),
        )
        val newList = listOf(
            UserModel(id = 1, name = "name", username = "username", img = "img"),
            UserModel(id = 2, name = "name2", username = "username2", img = "img2"),
        )

        val diffCallback = UserListDiffCallback(oldList, newList)
        assertTrue(diffCallback.areItemsTheSame(0, 0))
        assertTrue(diffCallback.areItemsTheSame(1, 1))
    }

    @Test
    fun `should return false when items are not the same`() {
        val oldList = listOf(
            UserModel(id = 1, name = "name", username = "username", img = "img"),
            UserModel(id = 2, name = "name2", username = "username2", img = "img2"),
        )
        val newList = listOf(
            UserModel(id = 4, name = "name4", username = "username4", img = "img4"),
            UserModel(id = 3, name = "name3", username = "username3", img = "img3"),
        )

        val diffCallback = UserListDiffCallback(oldList, newList)
        assertFalse(diffCallback.areItemsTheSame(0, 0))
        assertFalse(diffCallback.areItemsTheSame(1, 1))
    }

    @Test
    fun `should return true when the content are the same`() {
        val oldList = listOf(
            UserModel(id = 1, name = "name", username = "username", img = "img"),
        )
        val newList = listOf(
            UserModel(id = 1, name = "name", username = "username", img = "img"),
        )

        val diffCallback = UserListDiffCallback(oldList, newList)
        assertTrue(diffCallback.areContentsTheSame(0, 0))
    }

    @Test
    fun `should return false when the content are not the same`() {
        val oldList = listOf(
            UserModel(id = 1, name = "name", username = "username", img = "img"),
        )
        val newList = listOf(
            UserModel(id = 2, name = "name2", username = "username2", img = "img2"),
        )

        val diffCallback = UserListDiffCallback(oldList, newList)
        assertFalse(diffCallback.areContentsTheSame(0, 0))
    }

    @Test
    fun `should return correct list size`() {
        val oldList = listOf(
            UserModel(id = 1, name = "name", username = "username", img = "img"),
            UserModel(id = 2, name = "name2", username = "username2", img = "img2"),
            UserModel(id = 3, name = "name3", username = "username3", img = "img3"),
        )
        val newList = listOf(
            UserModel(id = 4, name = "name4", username = "username4", img = "img4"),
            UserModel(id = 3, name = "name3", username = "username3", img = "img3"),
        )

        val diffCallback = UserListDiffCallback(oldList, newList)

        assertEquals(diffCallback.getOldListSize(), 3)
        assertEquals(diffCallback.getNewListSize(), 2)
    }
}
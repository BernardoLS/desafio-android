package com.picpay.desafio.android.user.data.local

class UserLocalDataSource(private val userDao: UserDao) {
    fun fetchUsers(): List<UserEntity> = userDao.fetchUsers()

    suspend fun insertUsers(users: List<UserEntity>) {
       userDao.insertUsers(users)
    }
}
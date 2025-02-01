package com.picpay.desafio.android.user.data.local

class UserLocalDataSource(private val userDao: UserDao) {
    fun getUsers(): List<UserEntity> = userDao.getUsers()

    suspend fun insertUsers(users: List<UserEntity>) {
       userDao.insertUsers(users)
    }
}
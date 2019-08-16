package com.thsai.jetpack.db.repository

import androidx.lifecycle.LiveData
import com.thsai.jetpack.db.dao.UserDao
import com.thsai.jetpack.db.data.User
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext


/**
 * 用户处理仓库
 */
class UserRepository private constructor(private val userDao: UserDao) {
    /**
     * 登录用户
     */
    fun login(account: String, pwd: String): LiveData<User?> = userDao.login(account, pwd)

    /**
     * 注册用户
     */
    suspend fun register(account: String, pwd: String, email: String) = withContext(IO) {
        userDao.insertUser(
            User(
                account,
                pwd,
                email,
                "https://raw.githubusercontent.com/mCyp/Photo/master/1560651318109.jpeg"
            )
        )
    }


    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(userDao: UserDao): UserRepository {
            return instance ?: synchronized(this) {
                instance ?: UserRepository(userDao).also { instance = it }
            }
        }
    }
}
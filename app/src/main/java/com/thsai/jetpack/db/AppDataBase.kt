package com.thsai.jetpack.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.thsai.jetpack.db.dao.FavouriteShoeDao
import com.thsai.jetpack.db.dao.ShoeDao
import com.thsai.jetpack.db.dao.UserDao
import com.thsai.jetpack.db.data.FavouriteShoe
import com.thsai.jetpack.db.data.Shoe
import com.thsai.jetpack.db.data.User
import com.thsai.jetpack.worker.ShoeWorker

@Database(entities = [User::class, Shoe::class, FavouriteShoe::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao

    abstract fun shoeDao(): ShoeDao

    abstract fun favouriteShoeDao(): FavouriteShoeDao

    companion object {

        @Volatile
        private var instance: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDataBase(context)
                    .also { instance = it }
            }
        }

        private fun buildDataBase(context: Context): AppDataBase {
            return Room.databaseBuilder(context.applicationContext, AppDataBase::class.java, "jetpack")
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Log.v("wq","DataBase onCreate")
                        val oneTimeWorkRequest = OneTimeWorkRequestBuilder<ShoeWorker>().build()
                        WorkManager.getInstance(context.applicationContext).enqueue(oneTimeWorkRequest)
                    }
                })
                .build()
        }
    }
}
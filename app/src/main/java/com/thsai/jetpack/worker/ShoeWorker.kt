package com.thsai.jetpack.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.thsai.jetpack.db.RepositoryProvider
import com.thsai.jetpack.db.data.Shoe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ShoeWorker(context: Context, workerParameters: WorkerParameters) : CoroutineWorker(context, workerParameters) {


    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            applicationContext.assets.open("shoes.json").use {
                JsonReader(it.reader()).use { reader ->
                    val shoeType = object : TypeToken<List<Shoe>>() {}.type
                    val shoeList: List<Shoe> = Gson().fromJson(reader, shoeType)
                    val shoeRepository = RepositoryProvider.providerShoeRepository(applicationContext)
                    shoeRepository.insertShoes(shoeList)
                    for (i in 0..2) {
                        for (shoe in shoeList) {
                            shoe.id += shoeList.size
                        }
                        shoeRepository.insertShoes(shoeList)
                    }
                    Result.success()
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            Result.failure()
        }
    }
}
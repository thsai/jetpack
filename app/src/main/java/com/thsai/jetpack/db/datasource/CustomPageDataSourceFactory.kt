package com.thsai.jetpack.db.datasource

import androidx.paging.DataSource
import com.joe.jetpackdemo.db.datasource.CustomPageDataSource
import com.thsai.jetpack.db.data.Shoe
import com.thsai.jetpack.db.repository.ShoeRepository

/**
 * 构建CustomPageDataSource的工厂
 */
class CustomPageDataSourceFactory(private val shoeRepository: ShoeRepository):DataSource.Factory<Int,Shoe>() {
    override fun create(): DataSource<Int, Shoe> {
        return CustomPageDataSource(shoeRepository)
    }
}
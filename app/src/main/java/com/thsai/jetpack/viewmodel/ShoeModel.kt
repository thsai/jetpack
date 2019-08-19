package com.thsai.jetpack.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.thsai.jetpack.common.createPagerList
import com.thsai.jetpack.db.data.Shoe
import com.thsai.jetpack.db.datasource.CustomPageDataSourceFactory
import com.thsai.jetpack.db.repository.ShoeRepository

class ShoeModel constructor(private val shoeRepository: ShoeRepository) : ViewModel() {
    private val brand = MutableLiveData<String>().apply {
        value = ALL
    }

    // 鞋子集合的观察类
    val shoes: LiveData<PagedList<Shoe>> = brand.switchMap {
        Log.v("wq", "switchMap: $it")
        // Room数据库查询，只要知道返回的是LiveData<List<Shoe>>即可
        if (it == ALL) {
            // LivePagedListBuilder<Int,Shoe>( shoeRepository.getAllShoes(),PagedList.Config.Builder()
            LivePagedListBuilder(
                CustomPageDataSourceFactory(shoeRepository) // DataSourceFactory
                , PagedList.Config.Builder()
                    .setPageSize(10) // 分页加载的数量
                    .setEnablePlaceholders(false) // 当item为null是否使用PlaceHolder展示
                    .setInitialLoadSizeHint(10) // 预加载的数量
                    .build()
            )
                .build()
            //shoeRepository.getAllShoes()
        } else {
            val array: Array<String> =
                when (it) {
                    NIKE -> arrayOf("Nike", "Air Jordan")
                    ADIDAS -> arrayOf("Adidas")
                    else -> arrayOf(
                        "Converse", "UA"
                        , "ANTA"
                    )
                }
            shoeRepository.getShoesByBrand(array)
                .createPagerList(6, 6)
        }
    }

    fun setBrand(brand: String) {
        this.brand.value = brand
//        this.brand.map {
//            Log.v("wq", "map: $brand")
//        }
    }

    companion object {
        const val ALL = "所有"
        const val NIKE = "Nike"
        const val ADIDAS = "Adidas"
        const val OTHER = "other"
    }
}
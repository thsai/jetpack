package com.thsai.jetpack.common

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList


fun <T> DataSource.Factory<Int, T>.createPagerList(pageSize: Int, defaultSize: Int): LiveData<PagedList<T>> {
    return LivePagedListBuilder<Int, T>(
        this, PagedList.Config.Builder().setPageSize(pageSize)
            .setEnablePlaceholders(false).setInitialLoadSizeHint(defaultSize).build()
    ).build()
}
package com.thsai.jetpack.db.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 用户表
 */
@Entity(tableName = "user")
data class User(
    @ColumnInfo(name = "user_account") val acccount: String,
    @ColumnInfo(name = "user_pwd") val pwd: String,
    @ColumnInfo(name = "user_name") val name: String,
    @ColumnInfo(name = "user_url") var headImage: String
    //, @Embedded val address: Address // 地址
    //, @Ignore val state: Int  //Ignore声明某个字段只是临时用，不存储在数据库中。
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0
}
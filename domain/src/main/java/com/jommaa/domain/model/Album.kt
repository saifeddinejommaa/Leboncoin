package com.jommaa.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "album")
data class Album(@ColumnInfo(name = "album_id")var AlbumId:Int, @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var Id: Int, @ColumnInfo(name = "title") var title : String,  @ColumnInfo(name = "url")var url: String, @ColumnInfo(name = "thumbnail_url") var thumbnailUrl: String  ) {


}
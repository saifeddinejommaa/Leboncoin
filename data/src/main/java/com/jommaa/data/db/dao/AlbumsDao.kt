package com.jommaa.data.db.dao

import androidx.room.*
import com.jommaa.data.response.AlbumResp
import io.reactivex.Completable
import com.jommaa.domain.model.Album

@Dao
interface AlbumsDao {
    @Query("SELECT * FROM album")
    fun getAll(): List<AlbumResp>

    @Query("SELECT * FROM album WHERE id=:Id")
    fun loadById(Id: Int): List<AlbumResp>

    @Query("SELECT count(*) from album")
    fun getAlbumsCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg album: AlbumResp?)

    @Delete
    fun delete(album: AlbumResp)
}
package com.jommaa.data.db.dao

import androidx.room.*
import io.reactivex.Completable
import com.jommaa.domain.model.Album

@Dao
interface AlbumsDao {
    @Query("SELECT * FROM album")
    fun getAll(): List<Album>

    @Query("SELECT * FROM album WHERE id=:Id")
    fun loadById(Id: Int): List<Album>

    @Query("SELECT count(*) from album")
    fun getAlbumsCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg album: Album?)

    @Delete
    fun delete(album: Album)
}
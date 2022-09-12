package com.jommaa.data.mappers

import com.jommaa.data.dto.AlbumDto
import com.jommaa.domain.entities.Album
import javax.inject.Inject

class AlbumMapper @Inject constructor() {

    fun albumDTOtoAlbum(albumDto: AlbumDto): Album {
        return Album(
            albumId = albumDto.albumId,
            id = albumDto.id,
            title = albumDto.title,
            url = albumDto.url,
            thumbnailUrl = albumDto.thumbnailUrl
        )
    }
}
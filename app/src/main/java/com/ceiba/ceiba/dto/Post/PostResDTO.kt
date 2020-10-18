package com.ceiba.ceiba.dto.Post

import androidx.annotation.Keep
import com.ceiba.ceiba.models.PostBind
import com.squareup.moshi.Json

@Keep
data class PostResDTO (
    @field:Json(name="userId") val userId : Int,
    @field:Json(name="id") val id : Int,
    @field:Json(name="title") val title : String,
    @field:Json(name="body") val body : String
) {

    companion object {

        fun mapPostResDTOtoPostBind(listPostResDTO: List<PostResDTO>?) : List<PostBind> {
            val listPostBind = arrayListOf<PostBind>()
            listPostResDTO?.forEach { postResDTO ->
                listPostBind.add(
                    PostBind(
                        id = postResDTO.id,
                        title = postResDTO.title,
                        body = postResDTO.body
                    )
                )
            }
            return listPostBind
        }

    }

}
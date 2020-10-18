package com.ceiba.ceiba.dto.User

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class GeoResDTO (
    @field:Json(name="lat") val lat : String,
    @field:Json(name="lng") val lng : String
)
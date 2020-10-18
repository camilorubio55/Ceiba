package com.ceiba.ceiba.dto.User

import androidx.annotation.Keep
import com.ceiba.ceiba.dto.User.GeoResDTO
import com.squareup.moshi.Json

@Keep
data class AddressResDTO (
    @field:Json(name="street") val street : String,
    @field:Json(name="suite") val suite : String,
    @field:Json(name="city") val city : String,
    @field:Json(name="zipcode") val zipcode : String,
    @field:Json(name="geo") val geo : GeoResDTO?
)
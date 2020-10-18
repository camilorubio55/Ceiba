package com.ceiba.ceiba.dto.User

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
data class CompanyResDTO (
    @field:Json(name="name") val name : String,
    @field:Json(name="catchPhrase") val catchPhrase : String,
    @field:Json(name="bs") val bs : String
)
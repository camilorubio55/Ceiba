package com.ceiba.ceiba.dto.User

import androidx.annotation.Keep
import com.ceiba.ceiba.db.entities.UserDB
import com.ceiba.ceiba.models.UserBind
import com.squareup.moshi.Json

@Keep
data class UserResDTO (
    @field:Json(name="id") val id : Int,
    @field:Json(name="name") val name : String,
    @field:Json(name="username") val username : String,
    @field:Json(name="email") val email : String,
    @field:Json(name="address") val address : AddressResDTO?,
    @field:Json(name="phone") val phone : String,
    @field:Json(name="website") val website : String,
    @field:Json(name="company") val company : CompanyResDTO?
) {

    companion object {

        fun mapUserResDTOtoUserBind (listUserResDTO: List<UserResDTO>?) : List<UserBind> {
            val listUserBind = arrayListOf<UserBind>()
            listUserResDTO?.forEach { userResDTO ->
                listUserBind.add(
                    UserBind(
                        id = userResDTO.id,
                        name = userResDTO.name,
                        phone = userResDTO.phone,
                        email = userResDTO.email
                    )
                )
            }
            return listUserBind
        }

        fun mapUserDBtoUserBind (listUserDB: List<UserDB>?) : List<UserBind> {
            val listUserBind = arrayListOf<UserBind>()
            listUserDB?.forEach { userDB ->
                listUserBind.add(
                    UserBind(
                        id = userDB.id,
                        name = userDB.name,
                        phone = userDB.phone,
                        email = userDB.email
                    )
                )
            }
            return listUserBind
        }

        fun mapUserResDTOtoUserDB (listUserResDTO: List<UserResDTO>?) : List<UserDB> {
            val listUserDB = arrayListOf<UserDB>()
            listUserResDTO?.forEach { userResDTO ->
                listUserDB.add(
                    UserDB(
                        id = userResDTO.id,
                        name = userResDTO.name,
                        phone = userResDTO.phone,
                        email = userResDTO.email
                    )
                )
            }
            return listUserDB
        }

    }

}
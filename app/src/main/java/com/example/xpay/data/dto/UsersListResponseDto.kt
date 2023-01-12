package com.example.xpay.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class UsersListResponseDto(
    @Json(name = "limit")
    val limit: Int?,
    @Json(name = "skip")
    val skip: Int?,
    @Json(name = "total")
    val total: Int?,
    @Json(name = "users")
    val users: List<User?>?
) : Parcelable {
    @JsonClass(generateAdapter = true)
    @Parcelize
    data class User(
        @Json(name = "address")
        val address: Address?,
        @Json(name = "age")
        val age: Int?,
        @Json(name = "bank")
        val bank: Bank?,
        @Json(name = "birthDate")
        val birthDate: String?,
        @Json(name = "bloodGroup")
        val bloodGroup: String?,
        @Json(name = "company")
        val company: Company?,
        @Json(name = "domain")
        val domain: String?,
        @Json(name = "ein")
        val ein: String?,
        @Json(name = "email")
        val email: String?,
        @Json(name = "eyeColor")
        val eyeColor: String?,
        @Json(name = "firstName")
        val firstName: String?,
        @Json(name = "gender")
        val gender: String?,
        @Json(name = "hair")
        val hair: Hair?,
        @Json(name = "height")
        val height: Int?,
        @Json(name = "id")
        val id: Int?,
        @Json(name = "image")
        val image: String?,
        @Json(name = "ip")
        val ip: String?,
        @Json(name = "lastName")
        val lastName: String?,
        @Json(name = "macAddress")
        val macAddress: String?,
        @Json(name = "maidenName")
        val maidenName: String?,
        @Json(name = "password")
        val password: String?,
        @Json(name = "phone")
        val phone: String?,
        @Json(name = "ssn")
        val ssn: String?,
        @Json(name = "university")
        val university: String?,
        @Json(name = "userAgent")
        val userAgent: String?,
        @Json(name = "username")
        val username: String?,
        @Json(name = "weight")
        val weight: Double?
    ) : Parcelable {
        @JsonClass(generateAdapter = true)
        @Parcelize
        data class Address(
            @Json(name = "address")
            val address: String?,
            @Json(name = "city")
            val city: String?,
            @Json(name = "coordinates")
            val coordinates: Coordinates?,
            @Json(name = "postalCode")
            val postalCode: String?,
            @Json(name = "state")
            val state: String?
        ) : Parcelable {
            @JsonClass(generateAdapter = true)
            @Parcelize
            data class Coordinates(
                @Json(name = "lat")
                val lat: Double?,
                @Json(name = "lng")
                val lng: Double?
            ) : Parcelable
        }

        @JsonClass(generateAdapter = true)
        @Parcelize
        data class Bank(
            @Json(name = "cardExpire")
            val cardExpire: String?,
            @Json(name = "cardNumber")
            val cardNumber: String?,
            @Json(name = "cardType")
            val cardType: String?,
            @Json(name = "currency")
            val currency: String?,
            @Json(name = "iban")
            val iban: String?
        ) : Parcelable

        @JsonClass(generateAdapter = true)
        @Parcelize
        data class Company(
            @Json(name = "address")
            val address: Address?,
            @Json(name = "department")
            val department: String?,
            @Json(name = "name")
            val name: String?,
            @Json(name = "title")
            val title: String?
        ) : Parcelable {
            @JsonClass(generateAdapter = true)
            @Parcelize
            data class Address(
                @Json(name = "address")
                val address: String?,
                @Json(name = "city")
                val city: String?,
                @Json(name = "coordinates")
                val coordinates: Coordinates?,
                @Json(name = "postalCode")
                val postalCode: String?,
                @Json(name = "state")
                val state: String?
            ) : Parcelable {
                @JsonClass(generateAdapter = true)
                @Parcelize
                data class Coordinates(
                    @Json(name = "lat")
                    val lat: Double?,
                    @Json(name = "lng")
                    val lng: Double?
                ) : Parcelable
            }
        }

        @JsonClass(generateAdapter = true)
        @Parcelize
        data class Hair(
            @Json(name = "color")
            val color: String?,
            @Json(name = "type")
            val type: String?
        ) : Parcelable
    }
}
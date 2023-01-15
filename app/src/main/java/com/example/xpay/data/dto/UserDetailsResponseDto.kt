package com.example.xpay.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.example.xpay.domain.model.UserDetailsResponse
import com.example.xpay.domain.model.UserListResponse

@JsonClass(generateAdapter = true)
@Parcelize

data class UserDetailsResponseDto(
    @Json(name = "address")
    val address: AddressDto?,
    @Json(name = "age")
    val age: Int?,
    @Json(name = "bank")
    val bank: BankDto?,
    @Json(name = "birthDate")
    val birthDate: String?,
    @Json(name = "bloodGroup")
    val bloodGroup: String?,
    @Json(name = "company")
    val company: CompanyDto?,
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
    val hair: HairDto?,
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

    fun toUser(): UserDetailsResponse {
        return UserDetailsResponse(
            address = address?.toAddress(),
            age = age ?: 0,
            bank = bank?.toBank(),
            birthDate = birthDate ?: "",
            bloodGroup = bloodGroup ?: "",
            company = company?.toCompany(),
            domain = domain ?: "",
            ein = ein ?: "",
            email = email ?: "",
            eyeColor = eyeColor ?: "",
            firstName = firstName ?: "",
            gender = gender ?: "",
            hair = hair?.toHair(),
            height = height ?: 0,
            id = id ?: 0,
            image = image ?: "",
            ip = ip ?: "",
            lastName = lastName ?: "",
            macAddress = macAddress ?: "",
            maidenName = maidenName ?: "",
            password = password ?: "",
            phone = phone ?: "",
            ssn = ssn ?: "",
            university = university ?: "",
            userAgent = userAgent ?: "",
            username = username ?: "",
            weight = weight ?: 0.0,


            )
    }

    @JsonClass(generateAdapter = true)
    @Parcelize
    data class AddressDto(
        @Json(name = "address")
        val address: String?,
        @Json(name = "city")
        val city: String?,
        @Json(name = "coordinates")
        val coordinates: CoordinatesDto?,
        @Json(name = "postalCode")
        val postalCode: String?,
        @Json(name = "state")
        val state: String?
    ) : Parcelable {

        fun toAddress(): UserDetailsResponse.Address {
            return UserDetailsResponse.Address(
                address = address ?: "",
                city = city ?: "",
                coordinates = coordinates?.toCoordinates(),
                postalCode = postalCode ?: "",
                state = state ?: ""
            )
        }

        @JsonClass(generateAdapter = true)
        @Parcelize
        data class CoordinatesDto(
            @Json(name = "lat")
            val lat: Double?,
            @Json(name = "lng")
            val lng: Double?
        ) : Parcelable {
            fun toCoordinates(): UserDetailsResponse.Address.Coordinates {
                return UserDetailsResponse.Address.Coordinates(
                    lat = lat ?: 0.0,
                    lng = lng ?: 0.0
                )
            }
        }
    }

    @JsonClass(generateAdapter = true)
    @Parcelize
    data class BankDto(
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
    ) : Parcelable {
        fun toBank(): UserDetailsResponse.Bank {
            return UserDetailsResponse.Bank(
                cardExpire = cardExpire ?: "",
                cardNumber = cardNumber ?: "",
                cardType = cardType ?: "",
                currency = currency ?: "",
                iban = iban ?: ""
            )
        }
    }

    @JsonClass(generateAdapter = true)
    @Parcelize
    data class CompanyDto(
        @Json(name = "address")
        val address: AddressDto?,
        @Json(name = "department")
        val department: String?,
        @Json(name = "name")
        val name: String?,
        @Json(name = "title")
        val title: String?
    ) : Parcelable {

        fun toCompany(): UserDetailsResponse.Company {
            return UserDetailsResponse.Company(
                address = address?.toAddressCompany(),
                department = department ?: "",
                name = name ?: "",
                title = title ?: ""

            )
        }

        @JsonClass(generateAdapter = true)
        @Parcelize
        data class AddressDto(
            @Json(name = "address")
            val address: String?,
            @Json(name = "city")
            val city: String?,
            @Json(name = "coordinates")
            val coordinates: CoordinatesDto?,
            @Json(name = "postalCode")
            val postalCode: String?,
            @Json(name = "state")
            val state: String?
        ) : Parcelable {

            fun toAddressCompany(): UserDetailsResponse.Company.Address {
                return UserDetailsResponse.Company.Address(
                    address = address ?: "",
                    city = city ?: "",
                    coordinates = coordinates?.toCompanyCoordinates(),
                    postalCode = postalCode ?: "",
                    state = state ?: ""
                )
            }

            @JsonClass(generateAdapter = true)
            @Parcelize
            data class CoordinatesDto(
                @Json(name = "lat")
                val lat: Double?,
                @Json(name = "lng")
                val lng: Double?
            ) : Parcelable {
                fun toCompanyCoordinates(): UserDetailsResponse.Company.Address.Coordinates {
                    return UserDetailsResponse.Company.Address.Coordinates(
                        lat = lat ?: 0.0,
                        lng = lng ?: 0.0
                    )
                }
            }
        }
    }

    @JsonClass(generateAdapter = true)
    @Parcelize
    data class HairDto(
        @Json(name = "color")
        val color: String?,
        @Json(name = "type")
        val type: String?
    ) : Parcelable {
        fun toHair(): UserDetailsResponse.Hair {
            return UserDetailsResponse.Hair(
                color = color ?: "",
                type = type ?: "",
            )
        }
    }
}

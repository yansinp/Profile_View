package com.example.xpay.domain.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

data class UserListResponse(
    val limit: Int?,
    val skip: Int?,
    val total: Int?,
    val users: List<User?>?
) : Parcelable {
    data class User(

        val address: Address?,
        val age: Int?,
        val bank: Bank?,
        val birthDate: String?,
        val bloodGroup: String?,
        val company: Company?,
        val domain: String?,
        val ein: String?,
        val email: String?,
        val eyeColor: String?,
        val firstName: String?,
        val gender: String?,
        val hair: Hair?,
        val height: Int?,
        val id: Int?,
        val image: String?,
        val ip: String?,
        val lastName: String?,
        val macAddress: String?,
        val maidenName: String?,
        val password: String?,
        val phone: String?,
        val ssn: String?,
        val university: String?,
        val userAgent: String?,
        val username: String?,
        val weight: Double?
    ) : Parcelable {
        data class Address(
            val address: String?,
            val city: String?,
            val coordinates: Coordinates?,
            val postalCode: String?,
            val state: String?
        ) : Parcelable {
            data class Coordinates(
                val lat: Double?,
                val lng: Double?
            ) : Parcelable
        }

        data class Bank(
            val cardExpire: String?,
            val cardNumber: String?,
            val cardType: String?,
            val currency: String?,
            val iban: String?
        ) : Parcelable


        data class Company(
            val address: Address?,
            val department: String?,
            val name: String?,
            val title: String?
        ) : Parcelable {
            data class Address(
                val address: String?,
                val city: String?,
                val coordinates: Coordinates?,
                val postalCode: String?,
                val state: String?
            ) : Parcelable {
                data class Coordinates(
                    val lat: Double?,
                    val lng: Double?
                ) : Parcelable
            }
        }

        data class Hair(
            val color: String?,
            val type: String?
        ) : Parcelable
    }
}
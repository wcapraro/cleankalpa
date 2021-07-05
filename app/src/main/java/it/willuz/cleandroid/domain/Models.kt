package it.willuz.cleandroid.domain

import java.util.*

data class QuoteModel(val id: Int, val quote: String, val author: AuthorModel?, val date: Date)

data class AuthorModel(val firstName: String, val lastName: String, val role: RoleModel)

enum class RoleModel {
    Geek,
    CoffeeDrinker,
    ZenMaster,
    Martyr
}

val AuthorModel.displayName: String get() = "$firstName $lastName"
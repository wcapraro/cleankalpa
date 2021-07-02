package it.willuz.cleandroid.domain

data class QuoteModel(val id: Int, val quote: String, val author: AuthorModel?)

data class AuthorModel(val firstName: String, val lastName: String, val role: RoleModel)

enum class RoleModel {
    Geek,
    CoffeeDrinker,
    ZenMaster,
    Martyr
}
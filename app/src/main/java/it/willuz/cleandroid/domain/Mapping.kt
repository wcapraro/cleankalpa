package it.willuz.cleandroid.domain

import it.willuz.cleandroid.entity.Author
import it.willuz.cleandroid.entity.Quote
import it.willuz.cleandroid.entity.Role

fun Quote.asQuoteModel(author: AuthorModel): QuoteModel =
    QuoteModel(this.id,
        this.message,
        author
    )

fun Author.asAuthorModel(): AuthorModel =
    AuthorModel(this.firstName, this.lastName, this.role.asRoleModel())

fun String.asRoleModel(): RoleModel {
    return when (this) {
        Role.SoftwareEngineer.name -> RoleModel.Geek
        Role.Martyr.name -> RoleModel.Martyr
        Role.SuperUser.name -> RoleModel.ZenMaster
        else -> RoleModel.CoffeeDrinker
    }
}
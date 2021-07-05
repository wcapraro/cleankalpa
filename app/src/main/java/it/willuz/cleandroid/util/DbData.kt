package it.willuz.cleandroid.util

import it.willuz.cleandroid.data.entity.Author
import it.willuz.cleandroid.data.entity.Quote
import it.willuz.cleandroid.data.entity.Role
import java.util.*

object DBData {
    val quotez: List<Quote> = listOf(
        Quote(0, "Quote 1", 0, Date().time),
        Quote(1, "Quote 2", 1, Date().time),
        Quote(2, "Quote 3", 0, Date().time),
        Quote(3, "Quote 4", 0, Date().time),
        Quote(4, "Quote 5", 1, Date().time),
        Quote(5, "Quote 6", 1, Date().time),
        Quote(6, "Quote 7", 0, Date().time),
        Quote(7, "Quote 8", 1, Date().time),
        Quote(8, "Quote 9", 1, Date().time),
        Quote(9, "Quote 10", 0, Date().time)
    )

    val authorz: List<Author> = listOf(
        Author(0, "AA.VV", "", Role.SoftwareEngineer.name),
        Author(1, "Wiluz", "", Role.SoftwareEngineer.name)
    )
}
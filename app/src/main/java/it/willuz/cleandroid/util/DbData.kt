package it.willuz.cleandroid.util

import it.willuz.cleandroid.data.entity.Author
import it.willuz.cleandroid.data.entity.Quote
import it.willuz.cleandroid.data.entity.Role
import java.util.*

object DBData {
    val quotez: List<Quote> = listOf(
        Quote(0, "Io non lo prendo il lunedì, preferisco prenderlo il giovedì", 2, Date().time),
        Quote(1, "I terroristi nascono perché li mettono a lavorare sui videocitofoni", 5, Date().time),
        Quote(2, "Io non sputo mai, ingoio tutto (riferito al vino, ndr)", 0, Date().time),
        Quote(3, "Ma è così difficile fare sto videocitofono?", 6, Date().time),
        Quote(4, "Mattias, la maschera!", 7, Date().time),
        Quote(5, "Se i trans si facessero tutti operare sarebbe la fine delle donne", 0, Date().time),
        Quote(6, "Me ne fotto!", 2, Date().time),
        Quote(7, "Simona mani di Fatal", 8, Date().time),
        Quote(8, "Eri troppo succoso in quella posizione", 9, Date().time),
        Quote(9, "C'è keysì e keynot", 2, Date().time),
        Quote(10, "Abbiamo un gateway frigido", 4, Date().time),
        Quote(11, "Faccio un passo avanti e due indietro. Sto facendo il moonwalk praticamente.", 0, Date().time),
        Quote(12, "Puoi anche aprirmi e anche se ci riuscissi entreresti in posti che non posso mostrarti.", 9, Date().time),
    )

    val authorz: List<Author> = listOf(
        Author(0, "AA.VV", "", Role.SoftwareEngineer.name),
        Author(1, "Wiluz", "", Role.SoftwareEngineer.name),
        Author(2, "Sallustio", "", Role.Martyr.name),
        Author(3, "Moro", "", Role.Martyr.name),
        Author(4, "Paolé", "", Role.Martyr.name),
        Author(5, "Cobien", "", Role.SuperUser.name),
        Author(6, "Tonnolo", "", Role.Martyr.name),
        Author(7, "Tizio del Paintball", "", Role.SuperUser.name),
        Author(8, "Simone", "", Role.SoftwareEngineer.name),
        Author(9, "Fede", "", Role.SoftwareEngineer.name),
    )
}
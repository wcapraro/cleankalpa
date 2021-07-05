package it.willuz.cleandroid.data.repository

import it.willuz.cleandroid.data.entity.Author
import it.willuz.cleandroid.data.entity.Quote

interface LocalDataSource {
    suspend fun getQuotes(): List<Quote>
    suspend fun getQuote(id: Int): Quote?
    suspend fun getAuthor(id: Int): Author?
}
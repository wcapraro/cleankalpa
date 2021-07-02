package it.willuz.cleandroid.repository

import it.willuz.cleandroid.entity.Quote

interface LocalDataSource {
    suspend fun getQuotes(): List<Quote>
}
package it.willuz.cleandroid.repository

import it.willuz.cleandroid.entity.Quote
import it.willuz.cleandroid.entity.db.AuthorDAO
import it.willuz.cleandroid.entity.db.QuotesDAO

class QuotesRepository(
    private val quotesDAO: QuotesDAO,
    private val authorDAO: AuthorDAO): LocalDataSource
{
    override suspend fun getQuotes(): List<Quote> {
        return quotesDAO.getAllQuotes()
    }
}
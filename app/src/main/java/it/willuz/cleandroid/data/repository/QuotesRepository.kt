package it.willuz.cleandroid.data.repository

import it.willuz.cleandroid.entity.Author
import it.willuz.cleandroid.entity.Quote
import it.willuz.cleandroid.data.db.AuthorDAO
import it.willuz.cleandroid.data.db.QuotesDAO

class QuotesRepository(
    private val quotesDAO: QuotesDAO,
    private val authorDAO: AuthorDAO
): LocalDataSource
{
    override suspend fun getQuotes(): List<Quote> {
        return quotesDAO.getAllQuotes()
    }

    override suspend fun getQuote(id: Int): Quote? {
        return quotesDAO.getQuote(id)
    }

    override suspend fun getAuthor(id: Int): Author? {
        return authorDAO.getAuthor(id)
    }
}
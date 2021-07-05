package it.willuz.cleandroid.domain

import it.willuz.cleandroid.data.repository.LocalDataSource

interface IGetQuoteDetailsUseCase {
    suspend fun getQuote(id: Int): QuoteModel?
}

class GetQuoteDetailsUseCase(private val repo: LocalDataSource,
                             private val getAuthorUseCase: IGetAuthorUseCase):
    IGetQuoteDetailsUseCase {

    override suspend fun getQuote(id: Int): QuoteModel? {
        repo.getQuote(id)?.let { q ->
            getAuthorUseCase.getAuthor(q.author)?.let { a ->
                return q.asQuoteModel(a)
            }
        }
        return null
    }
}
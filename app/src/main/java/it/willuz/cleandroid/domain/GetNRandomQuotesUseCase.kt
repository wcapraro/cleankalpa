package it.willuz.cleandroid.domain

import it.willuz.cleandroid.data.repository.LocalDataSource

interface IGetRandomQuotesUseCase {
    suspend fun getRandomQuotes(n: Int): List<QuoteModel>
}

class GetRandomQuotesUseCase(private val repo: LocalDataSource,
                             private val getAuthorUseCase: IGetAuthorUseCase)
    : IGetRandomQuotesUseCase
{
    override suspend fun getRandomQuotes(n: Int): List<QuoteModel> {
        val random = repo.getQuotes()
            .mapNotNull {
                getAuthorUseCase.getAuthor(it.author)?.let { a ->
                    it.asQuoteModel(a)
                }
            }.shuffled()

        return if (n > random.size) random else random.subList(0, n)
    }

}


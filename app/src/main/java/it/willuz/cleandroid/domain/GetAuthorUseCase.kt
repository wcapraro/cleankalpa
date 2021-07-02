package it.willuz.cleandroid.domain

import it.willuz.cleandroid.repository.LocalDataSource

interface IGetAuthorUseCase {
    suspend fun getAuthor(id: Int): AuthorModel?
}

class GetAuthorUseCase(private val repo: LocalDataSource): IGetAuthorUseCase {
    override suspend fun getAuthor(id: Int): AuthorModel? {
        return repo.getAuthor(id)?.asAuthorModel()
    }
}
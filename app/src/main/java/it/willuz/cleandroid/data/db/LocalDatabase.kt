package it.willuz.cleandroid.data.db

import android.content.Context
import androidx.room.*
import it.willuz.cleandroid.data.entity.Author
import it.willuz.cleandroid.data.entity.Quote

@Dao
interface QuotesDAO {

    @Query("SELECT * FROM QUOTE")
    suspend fun getAllQuotes(): List<Quote>

    @Query("SELECT * from QUOTE where id = :id")
    suspend fun getQuote(id: Int): Quote?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuotes(vararg quotes: Quote)
}

@Dao
interface AuthorDAO {
    @Query("SELECT * FROM AUTHOR where id = :id")
    suspend fun getAuthor(id: Int): Author?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAuthors(vararg authors: Author)
}

@Database(entities = [Quote::class, Author::class], version = 1)
abstract class LocalDatabase: RoomDatabase() {
    companion object {
        private var instance: LocalDatabase? = null

        fun getInstance(context: Context): LocalDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext,
                    LocalDatabase::class.java, "my.db")
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return instance!!
        }
    }
    abstract fun quotesDao(): QuotesDAO
    abstract fun authorDao(): AuthorDAO
}
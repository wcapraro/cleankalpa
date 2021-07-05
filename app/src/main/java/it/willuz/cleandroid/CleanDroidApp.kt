package it.willuz.cleandroid

import android.app.Application
import it.willuz.cleandroid.data.db.LocalDatabase
import it.willuz.cleandroid.util.DBData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CleanDroidApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initDb()
    }

    private fun initDb() {
        GlobalScope.launch {
            LocalDatabase.getInstance(this@CleanDroidApp).apply {
                authorDao().insertAuthors(*DBData.authorz.toTypedArray())
                quotesDao().insertQuotes(*DBData.quotez.toTypedArray())
            }
        }
    }
}
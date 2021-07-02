package it.willuz.cleandroid

import android.app.Application
import it.willuz.cleandroid.entity.db.LocalDatabase

class CleanDroidApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initDb()
    }

    private fun initDb() = LocalDatabase.getInstance(this)
}
package com.mariana.adv160420017uts.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Donation::class), version = 3)
abstract class DonationDatabase :RoomDatabase(){
    abstract fun donationDao(): DonationDao

    companion object{
        @Volatile private var instance: DonationDatabase ?= null
        private val LOCK = Any()

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                DonationDatabase::class.java, "donasidb")
                .build()

        operator fun invoke(context: Context) {
            if(instance != null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }
}
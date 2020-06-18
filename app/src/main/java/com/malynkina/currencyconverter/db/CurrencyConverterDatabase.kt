package com.malynkina.currencyconverter.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.malynkina.data.currencyrates.db.dao.CurrencyRatesDAO
import com.malynkina.data.currencyrates.db.model.CurrencyRatesEntity
import com.malynkina.data.currencyrates.db.model.StringMapConverter

@Database(
    entities = [
        CurrencyRatesEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(StringMapConverter::class)
abstract class CurrencyConverterDatabase: RoomDatabase() {

    abstract fun currencyRatesDAO() : CurrencyRatesDAO

}
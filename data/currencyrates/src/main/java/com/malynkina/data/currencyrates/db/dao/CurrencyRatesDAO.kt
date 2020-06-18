package com.malynkina.data.currencyrates.db.dao

import androidx.annotation.WorkerThread
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.malynkina.data.currencyrates.db.model.CurrencyRatesEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface CurrencyRatesDAO {

    @WorkerThread
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currencyRates: CurrencyRatesEntity)

    @WorkerThread
    @Query("SELECT * FROM currencyrates WHERE baseCurrency = :baseCurrency LIMIT 1")
    fun get(baseCurrency: String): Single<CurrencyRatesEntity>
}
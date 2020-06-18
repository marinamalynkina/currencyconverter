package com.malynkina.data.currencyrates.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.malynkina.data.currencyrates.model.CyrrencyRatesModel
import java.lang.reflect.Type
import java.math.BigDecimal


@Entity(tableName = "currencyrates")
@TypeConverters(StringMapConverter::class)
data class CurrencyRatesEntity(
    @PrimaryKey
    override val baseCurrency: String,
    override val timeAdded: Long,
    override val rates: Map<String, BigDecimal>
) : CyrrencyRatesModel

class StringMapConverter() {

    @TypeConverter
    fun fromMap(map: Map<String, BigDecimal>): String? {
        return Gson().toJson(map)
    }

    @TypeConverter
    fun fromString(value: String?): Map<String, BigDecimal> {
        val mapType: Type = object : TypeToken<Map<String, BigDecimal>>() {}.getType()
        return Gson().fromJson(value, mapType)
    }
}


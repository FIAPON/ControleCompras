package com.estudo.android.controlecompras.model

import java.text.NumberFormat
import java.util.*

data class Item (
    val label: String,
    var total: Double,
    var qtd: Double = 0.0,
    var value: Double = 0.0
) {

    private val format: NumberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())

    fun getValueInCurrency() : String{
        return format.format(total)
    }

    fun getQtdStr() : String{
        if (qtd.compareTo(0) == 0){
            return ""
        } else if (Math.ceil(qtd) == Math.floor(qtd)){
            return qtd.toInt().toString()
        } else {
            return qtd.toString()
        }
    }

    fun getValueStr() : String{
        if (value.compareTo(0) == 0){
            return ""
        } else if (Math.ceil(value) == Math.floor(value)){
            return value.toInt().toString()
        } else {
            return value.toString()
        }
    }

}
package com.estudo.android.controlecompras.mvvm.viewmodel

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.estudo.android.controlecompras.R
import com.estudo.android.controlecompras.mvp.base.App
import java.text.NumberFormat
import java.util.*

class ListItensViewModel : ViewModel() {

    private var limit: Double? = null
    private val format: NumberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())

    var status = MutableLiveData<String>()

    fun readLimit(intent: Intent) {
        limit = intent.getDoubleExtra("limit", 0.0)

        limit?.let {
            App.context?.let { safeContext ->
                val totalStr: String = format.format(it)
                status.value = safeContext.getString(R.string.show_status, totalStr, "R$0,00")
            }
        }
    }

    fun newParcial(value: Double) {
        limit?.let { safeLimit ->
            val totalStr: String = format.format(safeLimit)
            val parcialStr: String = format.format(value)
            App.context?.let { safeContext ->
                status.value = safeContext.getString(
                    R.string.show_status,
                    totalStr,
                    parcialStr
                )
            }
        }
    }

}
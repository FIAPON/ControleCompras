package com.estudo.android.controlecompras.mvp.presenter

import android.content.Intent
import com.estudo.android.controlecompras.R
import com.estudo.android.controlecompras.mvp.base.App
import com.estudo.android.controlecompras.mvp.view.ListItensActivity
import kotlinx.android.synthetic.main.activity_list_itens.*
import java.text.NumberFormat
import java.util.*

class ListItensPresenter(private val view : ListItensContract.View) : ListItensContract.Presenter {

    private var limit: Double? = null
    private val format: NumberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())

    override fun readLimit(intent: Intent) {
        limit = intent.getDoubleExtra("limit", 0.0)

        limit?.let {
            App.context?.let {safeContext ->
                val totalStr: String = format.format(it)
                view.showNewStatus(safeContext.getString(R.string.show_status, totalStr, "R$0,00"))
            }
        }
    }

    override fun newParcial(value: Double) {
        limit?.let { safeLimit ->
            val totalStr: String = format.format(safeLimit)
            val parcialStr: String = format.format(value)
            App.context?.let { safeContext ->
                view.showNewStatus(
                    safeContext.getString(
                        R.string.show_status,
                        totalStr,
                        parcialStr
                    )
                )
            }
        }
    }

    override fun start() {}

}
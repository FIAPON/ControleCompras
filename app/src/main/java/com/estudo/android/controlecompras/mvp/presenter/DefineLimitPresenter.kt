package com.estudo.android.controlecompras.mvp.presenter

import android.content.Intent
import com.estudo.android.controlecompras.mvp.base.App
import com.estudo.android.controlecompras.mvp.view.ListItensActivity

class DefineLimitPresenter(private val view : DefineLimitContract.View) : DefineLimitContract.Presenter {

    override fun limitIsValid(limit: String) {
        if (limit.trim().length > 0) {
            App.context?.let { safeContext ->
                val intent = Intent(safeContext, ListItensActivity::class.java)
                intent.putExtra("limit", limit.toDouble())
                safeContext.startActivity(intent)
            }

        } else {
            view.showErrorInLimitValue()
        }
    }

    override fun start() {

    }

}
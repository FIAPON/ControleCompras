package com.estudo.android.controlecompras.mvp.presenter

import android.content.Intent
import com.estudo.android.controlecompras.mvp.base.BasePresenter
import com.estudo.android.controlecompras.mvp.base.BaseView

interface ListItensContract {

    interface View : BaseView<ListItensPresenter> {
        fun showNewStatus(status: String)
    }

    interface Presenter : BasePresenter {
        fun readLimit(intent: Intent)
        fun newParcial(value: Double)
    }

}
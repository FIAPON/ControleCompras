package com.estudo.android.controlecompras.mvp.presenter

import com.estudo.android.controlecompras.mvp.base.BasePresenter
import com.estudo.android.controlecompras.mvp.base.BaseView

interface DefineLimitContract {

    /**
     * Nossa Activity precisa implementar os métodos definidos abaixo
     */
    interface View : BaseView<DefineLimitPresenter> {
        fun showErrorInLimitValue()
    }

    /**
     * Nosso Presenter precisa implementar os seguintes métodos
     */
    interface Presenter : BasePresenter {
        fun limitIsValid(limit: String)
    }

}
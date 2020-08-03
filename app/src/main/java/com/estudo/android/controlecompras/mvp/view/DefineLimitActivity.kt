package com.estudo.android.controlecompras.mvp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.estudo.android.controlecompras.R
import com.estudo.android.controlecompras.mvp.presenter.DefineLimitContract
import com.estudo.android.controlecompras.mvp.presenter.DefineLimitPresenter
import kotlinx.android.synthetic.main.activity_main.*

class DefineLimitActivity : AppCompatActivity(), DefineLimitContract.View {

    override fun showErrorInLimitValue() {
        edtValue.setError("Insira um valor válido para seguir!")
    }

    override lateinit var presenter : DefineLimitPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = DefineLimitPresenter(this)
        presenter.start()

        btnGo.setOnClickListener {
            presenter.limitIsValid(edtValue.text.toString())
        }
    }

}
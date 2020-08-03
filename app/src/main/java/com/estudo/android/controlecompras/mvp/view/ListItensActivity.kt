package com.estudo.android.controlecompras.mvp.view

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.estudo.android.controlecompras.R
import com.estudo.android.controlecompras.adapter.MyAdapter
import com.estudo.android.controlecompras.model.Item
import com.estudo.android.controlecompras.mvp.presenter.ListItensContract
import com.estudo.android.controlecompras.mvp.presenter.ListItensPresenter
import kotlinx.android.synthetic.main.activity_list_itens.*

class ListItensActivity : AppCompatActivity(), View.OnKeyListener, ListItensContract.View {

    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: MyAdapter

    override lateinit var presenter : ListItensPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_itens)

        presenter = ListItensPresenter(this)
        presenter.readLimit(intent)

        edtNewItem.setOnKeyListener(this@ListItensActivity)

        viewManager = LinearLayoutManager(this)
        viewAdapter =
            MyAdapter() {
                presenter.newParcial(it)
            }

        recycler.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun onKey(view: View?, keyCode: Int, event: KeyEvent?): Boolean {
        event?.let {
            if ((it.getAction() == KeyEvent.ACTION_DOWN) &&
                (keyCode == KeyEvent.KEYCODE_ENTER)) {
                viewAdapter.add(
                    Item(
                        label = edtNewItem.text.toString(),
                        total = 0.0
                    )
                )
                edtNewItem.setText("")
                return true;
            }
        }

        return false
    }

    override fun showNewStatus(status: String) {
        txtStatus.setText(status)
    }

}
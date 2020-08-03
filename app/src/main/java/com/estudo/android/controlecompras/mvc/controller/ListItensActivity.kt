package com.estudo.android.controlecompras.mvc.controller

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.estudo.android.controlecompras.R
import com.estudo.android.controlecompras.adapter.MyAdapter
import com.estudo.android.controlecompras.model.Item
import kotlinx.android.synthetic.main.activity_list_itens.*
import java.text.NumberFormat
import java.util.*

class ListItensActivity : AppCompatActivity(), View.OnKeyListener {

    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: MyAdapter

    private var limit: Double? = null
    private val format: NumberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_itens)

        limit = intent.getDoubleExtra("limit", 0.0)

        limit?.let {
            val totalStr: String = format.format(it)
            txtStatus.setText(getString(R.string.show_status, totalStr, "R$0,00"))
        }

        edtNewItem.setOnKeyListener(this@ListItensActivity)

        viewManager = LinearLayoutManager(this)
        viewAdapter =
            MyAdapter() {
                limit?.let { safeLimit ->
                    val totalStr: String = format.format(safeLimit)
                    val parcialStr: String = format.format(it)
                    txtStatus.setText(
                        getString(
                            R.string.show_status,
                            totalStr,
                            parcialStr
                        )
                    )
                }
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

}
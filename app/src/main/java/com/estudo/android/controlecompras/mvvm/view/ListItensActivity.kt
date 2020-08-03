package com.estudo.android.controlecompras.mvvm.view

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.estudo.android.controlecompras.R
import com.estudo.android.controlecompras.databinding.ActivityListItensMvvmBinding
import com.estudo.android.controlecompras.model.Item
import com.estudo.android.controlecompras.mvvm.adapter.MyAdapter
import com.estudo.android.controlecompras.mvvm.viewmodel.ListItensViewModel
import kotlinx.android.synthetic.main.activity_list_itens.*

class ListItensActivity : AppCompatActivity(), View.OnKeyListener {

    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: ListItensViewModel = ViewModelProvider(this).get(ListItensViewModel::class.java)

        val binding: ActivityListItensMvvmBinding = DataBindingUtil.setContentView(this, R.layout.activity_list_itens_mvvm)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.readLimit(intent)

        edtNewItem.setOnKeyListener(this@ListItensActivity)

        viewManager = LinearLayoutManager(this)
        viewAdapter =
            MyAdapter() {
                viewModel.newParcial(it)
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
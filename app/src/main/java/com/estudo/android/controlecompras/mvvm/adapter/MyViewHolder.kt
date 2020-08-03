package com.estudo.android.controlecompras.mvvm.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.estudo.android.controlecompras.databinding.ItemListMvvmBinding
import com.estudo.android.controlecompras.model.Item

class MyViewHolder(
    view: View,
    private val makeCalNow: () -> Unit,
    private val deleteItem: (position: Int) -> Unit
) : RecyclerView.ViewHolder(view) {

    val binding: ItemListMvvmBinding? = DataBindingUtil.bind(view)

    var position: Int? = null

    init {
        binding?.edtQtd?.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calcValue()
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        binding?.edtValue?.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calcValue()
            }
            override fun afterTextChanged(s: Editable?) {}
        })


        binding?.txtItemLabel?.setOnLongClickListener {
            position?.let {
                deleteItem(it)
            }

            true
        }

    }

    fun calcValue(){
        val qtd = binding?.edtQtd?.text.toString()
        val value = binding?.edtValue?.text.toString()

        var total: Double = 0.0;
        try {
            total = qtd.toDouble() * value.toDouble()
        } catch (exc: NumberFormatException){}

        position?.let {
            binding?.item?.total = total
            binding?.item?.qtd = try { qtd.toDouble() } catch(exception: java.lang.NumberFormatException) { 0.0 }
            binding?.item?.value = try { value.toDouble() } catch(exception: java.lang.NumberFormatException) { 0.0 }
            binding?.txtTotalItem?.text = binding?.item?.getValueInCurrency()
            makeCalNow()
        }

    }

    fun bind(pos: Int, item: Item){
        binding?.item = item
        binding?.executePendingBindings()

        if (item.qtd.compareTo(0) != 0){
            binding?.edtQtd?.setText(item.qtd.toString())
        }

        if (item.value.compareTo(0) != 0){
            binding?.edtValue?.setText(item.value.toString())
        }

        position = pos
    }

}
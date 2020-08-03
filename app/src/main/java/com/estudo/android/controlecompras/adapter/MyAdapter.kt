package com.estudo.android.controlecompras.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.estudo.android.controlecompras.R
import com.estudo.android.controlecompras.model.Item
import com.estudo.android.controlecompras.model.ItensSource
import kotlinx.android.synthetic.main.item_list.view.*
import java.lang.NumberFormatException

class MyAdapter(
    val returnSum: (Double) -> Unit
) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    val dataSource = ItensSource()

    class MyViewHolder(
        view: View,
        private val makeCalNow: () -> Unit) : RecyclerView.ViewHolder(view) {

        val labelTextView: AppCompatTextView = view.txtItemLabel
        val edtQtd = view.edtQtd;
        val edtValue = view.edtValue;
        val txtTotalItem = view.txtTotalItem

        var position: Int? = null
        var item: Item? = null

        init {
            edtQtd.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    calcValue()
                }
                override fun afterTextChanged(s: Editable?) {}
            })

            edtValue.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    calcValue()
                }
                override fun afterTextChanged(s: Editable?) {}
            })
        }

        fun calcValue(){
            val qtd = edtQtd.text.toString()
            val value = edtValue.text.toString()

            var total: Double = 0.0;
            try {
                total = qtd.toDouble() * value.toDouble()
            } catch (exc: NumberFormatException){}

            txtTotalItem.text = "R$ ${total}"

            position?.let {
                item?.total = total
                makeCalNow()
            }

        }

        fun bind(pos: Int, i: Item){
            position = pos
            item = i
            labelTextView.text = i.label
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return MyViewHolder(
            view,
            {
                returnSum(dataSource.myDataset.sumByDouble { item -> item.total })
            })
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(position, dataSource.myDataset[position])
        holder.labelTextView.setOnLongClickListener {
            dataSource.myDataset.removeAt(position)
            notifyItemRemoved(position)
            returnSum(dataSource.myDataset.sumByDouble { item ->  item.total })
            true
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount() = dataSource.myDataset.size

    fun add(item: Item) {
        dataSource.myDataset.add(item)
        notifyItemInserted(dataSource.myDataset.size - 1)
    }
}

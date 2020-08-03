package com.estudo.android.controlecompras.mvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.estudo.android.controlecompras.R
import com.estudo.android.controlecompras.model.Item
import com.estudo.android.controlecompras.model.ItensSource

class MyAdapter(
    val returnSum: (Double) -> Unit
) : RecyclerView.Adapter<MyViewHolder>() {

    val dataSource = ItensSource()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_list_mvvm, parent, false),
            {
                returnSum(dataSource.myDataset.sumByDouble { item -> item.total })
            },
            deleteItem = { position ->
                dataSource.myDataset.removeAt(position)
                notifyItemRemoved(position)
                returnSum(dataSource.myDataset.sumByDouble { item ->  item.total })
            }
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(position, dataSource.myDataset[position])
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

package com.makashovadev.a23_4.delegate

import android.view.ContentInfo
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.makashovadev.a23_4.databinding.CountableBinding
import com.makashovadev.a23_4.item.Countable
import com.makashovadev.a23_4.item.Item

class CountableDelegateAdapter : AbsListItemAdapterDelegate<Countable, Item, CountableDelegateAdapter.CountableViewHolder>() {
    private lateinit var countableBinding: CountableBinding

    class CountableViewHolder(private val countableBinding: CountableBinding) : RecyclerView.ViewHolder(countableBinding.root) {

        fun onBind(item: Countable) {
            countableBinding.icon.setImageResource(item.idIcon)
            countableBinding.count.text = item.count
            countableBinding.textName.text = item.text_name
            countableBinding.textDesc.text = item.text_desc
        }
    }

    override fun isForViewType(item: Item, items: MutableList<Item>, position: Int): Boolean {
        return item is Countable
    }

    override fun onCreateViewHolder(parent: ViewGroup): CountableViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        countableBinding = CountableBinding.inflate(layoutInflater, parent, false)
        return CountableViewHolder(countableBinding)
    }


    override fun onBindViewHolder(item: Countable, holder: CountableViewHolder, payloads: MutableList<Any>) {
        holder.onBind(item)
    }
}
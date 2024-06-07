package com.makashovadev.a23_4.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.makashovadev.a23_4.item.Ad
import com.makashovadev.a23_4.item.Item
import com.makashovadev.a23_4.databinding.AdBinding

class AdDelegateAdapter : AbsListItemAdapterDelegate<Ad, Item, AdDelegateAdapter.AdViewHolder>() {
    private lateinit var adBinding: AdBinding

    class AdViewHolder(private val adBinding: AdBinding) : RecyclerView.ViewHolder(adBinding.root) {
        fun onBind(item: Ad) {
            adBinding.title.text = item.title
            adBinding.content.text = item.content
        }
    }

    override fun isForViewType(item: Item, items: MutableList<Item>, position: Int): Boolean {
        return item is Ad
    }

    override fun onCreateViewHolder(parent: ViewGroup): AdViewHolder{
        val layoutInflater = LayoutInflater.from(parent.context)
        adBinding = AdBinding.inflate(layoutInflater, parent, false)
        return AdViewHolder(adBinding)
    }

    override fun onBindViewHolder(item: Ad, holder: AdViewHolder, payloads: MutableList<Any>) {
        holder.onBind(item)
    }

}
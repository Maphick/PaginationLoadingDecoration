package com.makashovadev.a23_4.adapter

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.makashovadev.a23_4.item.Item
import com.makashovadev.a23_4.delegate.AdDelegateAdapter
import com.makashovadev.a23_4.delegate.CountableDelegateAdapter
import com.makashovadev.a23_4.delegate.ProductDelegateAdapter

class ProductAdapter() : ListDelegationAdapter<List<Item>>() {

    init {
        delegatesManager.addDelegate(ProductDelegateAdapter())
        delegatesManager.addDelegate(AdDelegateAdapter())
        delegatesManager.addDelegate(CountableDelegateAdapter())
    }

    override fun setItems(items: List<Item>?) {


        super.setItems(items)
        notifyDataSetChanged()
    }
}
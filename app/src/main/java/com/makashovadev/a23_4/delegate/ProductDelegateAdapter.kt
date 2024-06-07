package com.makashovadev.a23_4.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.makashovadev.a23_4.item.Item
import com.makashovadev.a23_4.databinding.ProductBinding
import com.makashovadev.a23_4.item.Product

class ProductDelegateAdapter : AbsListItemAdapterDelegate<Product, Item, ProductDelegateAdapter.ProductViewHolder>() {
    private lateinit var productBinding: ProductBinding

    class ProductViewHolder(private val productBinding: ProductBinding) :
        RecyclerView.ViewHolder(productBinding.root) {
        fun onBind(item: Product) {
            productBinding.icon.setImageResource(item.idIcon)
            productBinding.textName.text = item.name
            productBinding.textDesc.text = item.desc
        }
    }

    override fun isForViewType(item: Item, items: MutableList<Item>, position: Int): Boolean {
        return item is Product
    }

    override fun onCreateViewHolder(parent: ViewGroup): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        productBinding = ProductBinding.inflate(layoutInflater, parent, false)
        return ProductViewHolder(productBinding)
    }


    override fun onBindViewHolder(item: Product, holder: ProductViewHolder, payloads: MutableList<Any>) {
        holder.onBind(item)
    }
}
package com.makashovadev.a23_4

import androidx.recyclerview.widget.DiffUtil
import com.makashovadev.a23_4.item.Item
import com.makashovadev.a23_4.item.Product

class ProductDiff(val oldList: ArrayList<Item>, val newList: ArrayList<Item>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    //Элементы одинаковые
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        //oldList[oldItemPosition].id == newList[newItemPosition].id
        return oldList[oldItemPosition].hashCode() == newList[newItemPosition].hashCode()
    }

    //Содержимое одинаковое
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldProduct = oldList[oldItemPosition]
        val newProduct = newList[newItemPosition]
        return oldProduct.hashCode() == newProduct.hashCode()

        /*oldProduct.name == newProduct.name &&
                oldProduct.desc == newProduct.desc &&
                oldProduct.idIcon == newProduct.idIcon*/
    }
}
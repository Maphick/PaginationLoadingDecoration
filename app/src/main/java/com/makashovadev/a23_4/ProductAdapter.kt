package com.skill_factory.unit4

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.makashovadev.a23_4.item.Ad
import com.makashovadev.a23_4.item.Item
import com.makashovadev.a23_4.databinding.AdBinding
import com.makashovadev.a23_4.databinding.ProductBinding
import com.makashovadev.a23_4.item.Product
import java.util.ArrayList

const val ITEM_VIEW_TYPE_PRODUCT = 0
const val ITEM_VIEW_TYPE_AD = 1

class ProductAdapterOld(var data: ArrayList<Item>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var productBinding: ProductBinding
    private lateinit var adBinding: AdBinding



    override fun getItemViewType(position: Int): Int {
        return if (data[position] is Product) {
            ITEM_VIEW_TYPE_PRODUCT
        } else if (data[position] is Ad) {
            ITEM_VIEW_TYPE_AD
        } else {
            throw IllegalArgumentException("Invalid Item View type")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        if (viewType == ITEM_VIEW_TYPE_PRODUCT) {
            productBinding = ProductBinding.inflate(layoutInflater, parent, false)
            return  ProductViewHolder(productBinding)
        }
        else if (viewType == ITEM_VIEW_TYPE_AD) {
            adBinding = AdBinding.inflate(layoutInflater, parent, false)
            return  AdViewHolder(adBinding)
        } else {
            throw IllegalArgumentException("Invalid viewType")
        }


        /*val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemBinding.inflate(layoutInflater, parent, false)
        return  ViewHolder(binding)*/
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == ITEM_VIEW_TYPE_PRODUCT) {
            val h = holder as ProductViewHolder
            val item = data[position] as Product
            h.onBind(item)
        } else if (getItemViewType(position) == ITEM_VIEW_TYPE_AD) {
            val h = holder as AdViewHolder
            val item = data[position] as Ad
            h.onBind(item)
        }
    }


    /*
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(data[position])
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList < Any > ) {
        if (payloads.isEmpty())
            super.onBindViewHolder(holder, position, payloads)
        else {
            payloads.find {
                it is String && it == "icon"
            }
                .let {
                    holder.setImageResource(data[position].idIcon)
                }
            payloads.find {
                it is String && it == "name"
            }
                .let {
                    holder.setTextName(data[position].name)
                }
            payloads.find {
                it is String && it == "desc"
            }
                .let {
                    holder.setTextDesc(data[position].desc)
                }
        }
    }
    */

    override fun getItemCount(): Int {
        return data.size
    }

    // Ctrl + Cmd + G - мультикурсор
    // Ctrl + Shift + R - рефакторинг переменной
    class ProductViewHolder(private val productBinding: ProductBinding) : RecyclerView.ViewHolder(productBinding.root){

        fun onBind(item: Product) {

            productBinding.icon.setImageResource(item.idIcon)
            productBinding.textName.text = item.name
            productBinding.textDesc.text = item.desc
        }
        fun setImageResource(icon: Int) {
            productBinding.icon.setImageResource(icon)
        }
        fun setTextName(textName: String)
        {
            productBinding.textName.text = textName
        }
        fun setTextDesc(textDesc: String)
        {
            productBinding.textDesc.text = textDesc
        }
    }

    class AdViewHolder(private val adBinding: AdBinding) : RecyclerView.ViewHolder(adBinding.root){
        fun onBind(item: Ad) {
            adBinding.title.text  = item.title
            adBinding.content.text = item.content
        }
    }




}
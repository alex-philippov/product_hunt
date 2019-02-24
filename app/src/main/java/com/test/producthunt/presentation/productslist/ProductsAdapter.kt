package com.test.producthunt.presentation.productslist

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.test.producthunt.R
import com.test.producthunt.domain.Product
import kotlinx.android.synthetic.main.rv_product_item.view.*

class ProductsAdapter(
    private var products: List<Product>,
    val onClick: (product: Product) -> Unit
) : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, type: Int)
        = ProductViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_product_item, parent, false))

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    fun updateProducts(products: List<Product>) {
        this.products = products
        notifyDataSetChanged()
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(product: Product) = with(itemView) {
            val upvotes = "${resources.getString(R.string.product_item_upvotes)} ${product.upvotes}"

            setOnClickListener { onClick(product) }
            tv_product_item_name.text = product.name
            tv_product_item_description.text = product.description
            tv_product_item_upvotes.text = upvotes
            Glide.with(context).load(product.thumbUrl).into(iv_product_item_thumb)
        }
    }
}
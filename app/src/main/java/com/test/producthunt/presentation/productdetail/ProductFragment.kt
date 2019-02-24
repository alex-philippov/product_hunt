package com.test.producthunt.presentation.productdetail


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide

import com.test.producthunt.R
import com.test.producthunt.domain.Product
import kotlinx.android.synthetic.main.fragment_product.*
import kotlinx.android.synthetic.main.toolbar.*

private const val ARG_PRODUCT = "product"

class ProductFragment : Fragment() {
    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            product = it.getSerializable(ARG_PRODUCT) as Product
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_product, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar_title.text = product.name
        val upvotes = "${resources.getString(R.string.product_item_upvotes)} ${product.upvotes}"
        tv_product_name.text = product.name
        tv_product_description.text = product.name
        tv_product_upvotes.text = upvotes
        Glide.with(this).load(product.screenShotUrl).into(iv_product_screen)
        b_product_get_it.setOnClickListener { openLink(product.productUrl) }
    }

    private fun openLink(url: String) {
        val intent = Intent().apply {
            data = Uri.parse(url)
        }
        startActivity(intent)
    }

    companion object {
        @JvmStatic
        fun newInstance(product: Product) =
            ProductFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PRODUCT, product)
                }
            }
    }
}

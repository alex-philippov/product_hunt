package com.test.producthunt.presentation.productslist

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView

import com.test.producthunt.R
import com.test.producthunt.domain.Product
import com.test.producthunt.domain.Topic
import com.test.producthunt.presentation.common.Lce
import com.test.producthunt.presentation.productdetail.ProductFragment
import kotlinx.android.synthetic.main.fragment_products.*
import kotlinx.android.synthetic.main.toolbar.*

class ProductsFragment : Fragment() {

    lateinit var viewModel: ProductsViewModel
    lateinit var adapter: ProductsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProductsViewModel::class.java)
        adapter = ProductsAdapter(listOf()) { product ->
            fragmentManager!!
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fl_main_container, ProductFragment.newInstance(product))
                .commit()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_products, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        srl_list_refresh.setOnRefreshListener { viewModel.updateProducts(toolbar_title.text.toString()) }
        rv_products.layoutManager = LinearLayoutManager(context)
        rv_products.adapter = adapter

        viewModel.productsStore.observe(this) {
            Log.d("HUI DEBUG", it.toString())
            when (it) {
                is Lce.Success -> successProducts(it.data)
                is Lce.Error -> error()
                is Lce.Loading -> loading()
            }
        }
        viewModel.topicsStore.observe(this) {
            Log.d("HUI DEBUG", it.toString())
            when (it) {
                is Lce.Success -> successTopics(it.data)
                is Lce.Error -> error()
                is Lce.Loading -> loading()
            }
        }
        viewModel.currentTopicStore.observe(this) {
            when (it) {
                is Lce.Success -> {
                    toolbar_title.text = it.data.name
                }
            }
        }

        if (savedInstanceState == null) {
            viewModel.updateTopics()
        }
        toolbar_title.setOnClickListener {
            if (viewModel.topicsStore.state() is Lce.Success) {
                showPopUp(viewModel.topicsStore.state().data!!)
            }
        }
    }

    private fun successTopics(list: List<Topic>) {
        if (viewModel.currentTopicStore.state() !is Lce.Success) {
            viewModel.updateCurrentTopic(list.first().name)
            viewModel.updateProducts(list.first().name)
        }
    }

    private fun successProducts(list: List<Product>) {
        adapter.updateProducts(list)
        rv_products.visibility = View.VISIBLE
        srl_list_refresh.isRefreshing = false
        tv_products_error.visibility = View.GONE
    }

    private fun error() {
        tv_products_error.visibility = View.VISIBLE
        srl_list_refresh.isRefreshing = false
        rv_products.visibility = View.GONE
    }

    private fun loading() {
        srl_list_refresh.isRefreshing = true
    }

    private fun showPopUp(list: List<Topic>) {
        PopupMenu(context, toolbar_title)
            .apply {
                list.forEach { this.menu.add(it.name) }
                setOnMenuItemClickListener {
                    viewModel.updateCurrentTopic(it.title.toString())
                    viewModel.updateProducts(it.title.toString())
                    true }
            }
            .show()
    }

}

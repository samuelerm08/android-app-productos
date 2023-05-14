package com.example.aplicacionandroid

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProductAdapter(val context: Context) :
    ListAdapter<Product, ProductAdapter.ViewHolder>(DiffCallBack) {
    lateinit var onItemClickListener: (Product) -> Unit

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val productName: TextView = view.findViewById(R.id.textViewProductName)
        private val category: TextView = view.findViewById(R.id.textViewCategory)
        private val productImage: ImageView = view.findViewById(R.id.productImage)
        private val companyImage: ImageView = view.findViewById(R.id.companyImage)
        fun bind(product: Product) {
            productName.text = "Product: ${product.name}"
            category.text = "Category: ${product.category.toString()}"
            val image = when (product.company) {
                Company.Microsoft -> R.drawable.microsoft
                Company.Jetbrains -> R.drawable.jetbrains
                Company.Kingston -> R.drawable.kingston
                Company.Gigabyte -> R.drawable.gigabyte
            }
            companyImage.setImageResource(image)
            Glide.with(context)
                .load(product.url)
                .into(productImage)
            view.setOnClickListener {
                onItemClickListener(product)
            }
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.activity_items, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val team = getItem(position)
        holder.bind(team)
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
}
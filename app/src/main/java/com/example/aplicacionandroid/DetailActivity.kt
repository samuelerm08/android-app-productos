package com.example.aplicacionandroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailActivity : AppCompatActivity() {
    private lateinit var productName: TextView
    private lateinit var productPrice: TextView
    private lateinit var productImage: ImageView
    private lateinit var returnButton: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        productImage = findViewById(R.id.productImageDetail)
        productName = findViewById(R.id.productNameDetail)
        productPrice = findViewById(R.id.productPriceDetail)
        returnButton = findViewById(R.id.returnButton)

        val bundle = intent.extras
        val pName = bundle?.getString("name", "")
        val pPrice = bundle?.getString("price", "")
        val pUrl = bundle?.getString("url") ?: ""

        productName.text = pName
        productPrice.text = "$${pPrice}"
        Glide.with(applicationContext)
            .load(pUrl)
            .into(productImage)

        returnButton.setOnClickListener {
            val intentToMainActivity = Intent(this, MainActivity::class.java)
            startActivity(intentToMainActivity)
        }
    }
}
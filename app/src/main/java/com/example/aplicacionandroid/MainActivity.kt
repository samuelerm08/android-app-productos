package com.example.aplicacionandroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter
    private lateinit var onSessionText: TextView
    private lateinit var endSessionButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        onSessionText = findViewById(R.id.onSessionTextView)
        endSessionButton = findViewById(R.id.endSessionButton)

        val bundle = intent.extras
        val textToAttach = bundle?.getString("email", "")

        onSessionText.text = "Hello ${textToAttach}!"

        adapter = ProductAdapter(applicationContext)
        recyclerView.adapter = adapter

        adapter.submitList(getProductList())
        adapter.onItemClickListener = { product ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("name", product.name)
            intent.putExtra("price", product.price.toString())
            intent.putExtra("url", product.url)
            startActivity(intent)
        }
        endSessionButton.setOnClickListener {
            val intentToEndSession = Intent(this, Login::class.java)
            startActivity(intentToEndSession)
        }
    }
    private fun getProductList() : MutableList<Product>? {
        return mutableListOf(
            Product(1,"SSD 240GB", 49.99f, Category.Hardware, Company.Kingston, "https://media.kingston.com/kingston/product/ktc-product-ssd-a400-sa400s37-240gb-3-zm-lg.jpg"),
            Product(2,"Pen-drive 64GB", 24.99f, Category.Hardware, Company.Kingston, "https://http2.mlstatic.com/D_NQ_NP_705012-MLA31653964760_082019-O.jpg"),
            Product(3,"Motherboard", 525.50f, Category.Hardware, Company.Gigabyte, "https://w7.pngwing.com/pngs/722/81/png-transparent-motherboard-gigabyte-technology-lga-1150-gigabyte-ga-x99-ud4-atx-others.png"),
            Product(4,"Graphic Card", 434.99f, Category.Hardware, Company.Gigabyte, "https://e7.pngegg.com/pngimages/531/760/png-clipart-graphics-cards-video-adapters-geforce-gigabyte-technology-gddr5-sdram-scalable-link-interface-nvidia-electronics-computer.png"),
            Product(5,"Microsoft Visual Studio 2022", 0f, Category.Software, Company.Microsoft, "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2c/Visual_Studio_Icon_2022.svg/1200px-Visual_Studio_Icon_2022.svg.png"),
            Product(6,"Microsoft SQL Server Management Studio", 0f, Category.Software, Company.Microsoft, "https://banner2.cleanpng.com/20180614/sg/kisspng-microsoft-sql-server-sql-server-management-studio-transactional-analysis-5b2207401c5992.0038138215289567361161.jpg"),
            Product(7,"PyCharm", 0f, Category.Software, Company.Jetbrains, "https://e7.pngegg.com/pngimages/805/274/png-clipart-black-and-white-pc-logo-pycharm-logo-icons-logos-emojis-tech-companies.png"),
            Product(8,"Android Studio", 0f, Category.Software, Company.Jetbrains, "https://upload.wikimedia.org/wikipedia/commons/thumb/9/95/Android_Studio_Icon_3.6.svg/1900px-Android_Studio_Icon_3.6.svg.png")
        )
    }
}
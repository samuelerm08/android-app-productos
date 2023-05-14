package com.example.aplicacionandroid

data class Product(
    val id: Int,
    val name: String,
    val price: Float,
    val category: Category,
    val company: Company,
    val url: String
)
enum class Category { Hardware, Software }
enum class Company { Microsoft, Jetbrains, Gigabyte, Kingston }
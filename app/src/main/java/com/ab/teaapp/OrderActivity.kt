package com.ab.teaapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class OrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_order)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.order)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val productSpinner = findViewById<Spinner>(R.id.productSpinner)
        val sugarCheckBox = findViewById<CheckBox>(R.id.sugarCheckBox)
        val milkCheckBox = findViewById<CheckBox>(R.id.milkCheckBox)
        val orderButton = findViewById<Button>(R.id.orderButton)

        // Пример данных для Spinner
        val products = arrayOf("Чай", "Кофе")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, products)
        productSpinner.adapter = adapter

        orderButton.setOnClickListener {
            val selectedProduct = productSpinner.selectedItem.toString()
            val sugar = sugarCheckBox.isChecked
            val milk = milkCheckBox.isChecked

            // Формирование текста заказа
            val order = StringBuilder()
            order.append("Заказ: $selectedProduct\n")
            if (sugar) order.append("Добавлено: Сахар\n")
            if (milk) order.append("Добавлено: Молоко\n")

            // Переход к SummaryActivity с передачей данных
            val intent = Intent(this, SummaryActivity::class.java)
            intent.putExtra("ORDER_SUMMARY", order.toString())
            startActivity(intent)
        }
    }
}
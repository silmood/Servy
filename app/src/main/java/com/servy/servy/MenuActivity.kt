package com.servy.servy

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    val menuAdapter : MenuAdapter by lazy {
        MenuAdapter(this, MenuRepo.getMenu())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        listaPlatillos.layoutManager = LinearLayoutManager(this)
        listaPlatillos.adapter = menuAdapter

    }

}

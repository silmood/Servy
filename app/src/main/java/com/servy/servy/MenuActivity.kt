package com.servy.servy

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId == R.id.item_accept){
            val total = sumaTotalDe(menuAdapter.orden)
            val intent = EntregaActivity.buildIntent(total)

            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }

    private fun sumaTotalDe(orden: List<Platillo>): Float {
        var total : Float = 0f
        orden.forEach { platillo -> total += platillo.precio }

        return total
    }

}

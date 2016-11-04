package com.servy.servy

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.servy.servy.common.startActivityWithSimpleIntent
import kotlinx.android.synthetic.main.activity_delivery_code.*

const val LONGITUD_CODIGO_SERVY : Int = 4

class ServyCodeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery_code)

        inputCodigo.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun afterTextChanged(p0: Editable?) { }

            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                buttonAceptar.isEnabled = text?.length == LONGITUD_CODIGO_SERVY
            }
        })

        buttonAceptar.setOnClickListener { enviarCodigo() }
    }

    private fun enviarCodigo() {
        startActivityWithSimpleIntent(MenuActivity::class)
    }
}

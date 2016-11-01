package com.servy.servy.view

import android.content.Context
import android.content.res.TypedArray
import android.support.v7.widget.AppCompatEditText
import android.util.AttributeSet
import android.view.inputmethod.InputMethodManager
import com.servy.servy.R

class HintResizeableEditText : AppCompatEditText{

    private var hintTextSize : Int = 0
    private var normalTextSize: Float = 0f

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet){
        init(context, attributeSet)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        init(context, attrs)
    }


    private fun init(context: Context, attributeSet: AttributeSet?) {
       if(attributeSet == null)
           return

        val typedArray : TypedArray = context.obtainStyledAttributes(attributeSet, R.styleable.HintResizeableEditText)
        hintTextSize = typedArray.getDimensionPixelSize(R.styleable.HintResizeableEditText_hintTextSize, context.resources.getDimensionPixelSize(R.dimen.default_hint_size))
        normalTextSize = textSize
        textSize = hintTextSize.toFloat()

        setOnFocusChangeListener { view, hasFocus ->
            textSize = if (!hasFocus && text.length == 0) hintTextSize.toFloat() else normalTextSize
            val imm : InputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
        }

        typedArray.recycle()
    }
}

package com.now.three_days.config

import android.util.Log
import android.view.View
import androidx.databinding.BindingAdapter

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("visible")
    fun visibleIf(view:View, isVisible:Boolean){

        view.visibility = if(isVisible) View.VISIBLE else View.GONE

    }

}
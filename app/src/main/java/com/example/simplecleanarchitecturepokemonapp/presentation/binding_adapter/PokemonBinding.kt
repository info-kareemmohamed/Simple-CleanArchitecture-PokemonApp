package com.example.simplecleanarchitecturepokemonapp.presentation.binding_adapter

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import coil.load
import com.example.simplecleanarchitecturepokemonapp.R
import com.example.simplecleanarchitecturepokemonapp.common.Constants.BASE_URL_IMAGE

@BindingAdapter("imageUrl", "progressBar")
fun loadImage(view: ImageView, url: String?, progressBar: ProgressBar?) {
    progressBar?.visibility = if (url.isNullOrEmpty()) View.GONE else View.VISIBLE

    view.load(if (url.isNullOrEmpty()) "" else "$BASE_URL_IMAGE${url[url.length-2]}.png" ) {
        listener(
            onStart = {
                progressBar?.visibility = View.VISIBLE
            },
            onSuccess = { _, _ ->
                progressBar?.visibility = View.GONE
            },
            onError = { _, _ ->
                progressBar?.visibility = View.GONE
                view.setImageResource(R.drawable.ic_error)
            }
        )
    }
}

@BindingAdapter("customVisibility")
fun customVisibility(view: View, visibility: Boolean) {
    view.visibility = if (visibility) View.VISIBLE else View.GONE
}
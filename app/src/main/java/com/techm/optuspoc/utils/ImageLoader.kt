package com.techm.optuspoc.utils

import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.techm.optuspoc.R
import java.lang.Exception

/*
* LoadImage - load image using @picasso lib
* #url - Link to load image from path
* */
@BindingAdapter(value = ["imageUrl"], requireAll = false)
fun ImageView.loadImage(url: String?) {
    Picasso
        .get()
        .load(url)
        .placeholder(R.drawable.no_image)
        .error(R.drawable.no_image)
        .into(this, object: Callback {
            override fun onSuccess() {
                Log.d("TAG", "onSuccess")
            }

            override fun onError(e: Exception?) {
                Toast.makeText(context, "An error occurred", Toast.LENGTH_SHORT).show()
            }

        })
}
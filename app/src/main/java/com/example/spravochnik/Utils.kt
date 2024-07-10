package com.example.spravochnik

import android.media.Image
import android.widget.ImageView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import coil.request.ImageRequest
import java.text.NumberFormat
import java.util.*

fun FormatNumber(num: Long): String{
    return NumberFormat.getInstance().format(num)
}

suspend fun loadSVG(imageView: ImageView, url: String){
    if(url.lowercase(Locale.ENGLISH).endsWith("svg")){
        val imageLoader = ImageLoader.Builder(imageView.context)
            .components{
                add(SvgDecoder.Factory())
            }
            .build()
        val request = ImageRequest.Builder(imageView.context)
            .data(url)
            .target(imageView)
            .build()
        imageLoader.execute(request)
    }
    else{
        imageView.load(url)
    }
}
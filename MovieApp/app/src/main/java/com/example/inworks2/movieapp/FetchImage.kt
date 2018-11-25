package com.example.inworks2.movieapp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.widget.ImageView
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.net.URL
import java.util.*
import java.util.stream.Stream

class FetchImage : AsyncTask<ImageRequest, Void, Bitmap>() {
    var imageReq : ImageRequest? = null

    override fun doInBackground(vararg request: ImageRequest?): Bitmap {
        for (req in request){
            imageReq=req
        }

        var stream = URL(imageReq?.uri).openStream()
        return BitmapFactory.decodeStream(stream)
    }

    override fun onPostExecute(result: Bitmap?) {
        super.onPostExecute(result)
        imageReq?.imageView?.setImageBitmap(result)
    }
}


package com.example.inworks2.movieapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var disposable: Disposable? = null
    private val api_key: String = "5377531419038311d9944a2749e4026a"

    private  val mobileService by lazy  {
        ApiService.create()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        beginSearch()


    }

    private fun beginSearch() {
        disposable = mobileService.getTopMovies((api_key))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {result : MovieList -> showResult(result)}
                )
    }

    private fun showResult(result : MovieList) {
        Toast.makeText(this, result.total_results.toString(),Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }
}

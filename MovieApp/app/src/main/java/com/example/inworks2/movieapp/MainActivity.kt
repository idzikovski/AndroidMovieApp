package com.example.inworks2.movieapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var getMoviesDisposable: Disposable? = null
    private var getConfigurationDisposable: Disposable?=null
    private val apiKey: String = "5377531419038311d9944a2749e4026a"

    private  var layoutManager : RecyclerView.LayoutManager? = null
    private  var adapter : MoviesAdapter? =null

    private  val mobileService by lazy  {
        ApiService.create()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        beginSearch()
    }

    private fun beginSearch() {
        getConfigurationDisposable=mobileService.getConfiguration(apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    conf: Configuration ->
                    run {
                        MyApplication.config = conf
                        Log.d("MainActivity", "Vrati konfiguracija")
                        getMoviesDisposable = mobileService.getTopMovies((apiKey))
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe { result : MovieList -> showResult(result)}
                    }
                }
    }

    private fun showResult(result : MovieList) {
        Log.d("MainActivity", "Vrati filmovi")
        adapter=MoviesAdapter(result.results, this)
        layoutManager=LinearLayoutManager(this)
        recyclerView.adapter=adapter
        recyclerView.layoutManager=layoutManager
    }

    override fun onDestroy() {
        super.onDestroy()
        getMoviesDisposable?.dispose()
    }
}

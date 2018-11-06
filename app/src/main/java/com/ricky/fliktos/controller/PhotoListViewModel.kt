package com.ricky.fliktos.controller

import com.ricky.fliktos.model.Item
import com.ricky.fliktos.model.PhotoFeed
import com.ricky.fliktos.network.FlickrServiceAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PhotoListViewModel(private var listener: Listener) {
    interface Listener {
        fun isLoading(): Boolean
        fun loaded()
        fun failed()
    }

    private val flickerService: FlickrServiceAPI = FlickrServiceAPI.create()
    val list: MutableList<Item> = ArrayList()

    fun fetch(tags: List<String>) {
        flickerService.getPublicPhotoFeed(tags)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleResponse, this::handleError)
    }

    private fun handleResponse(photoFeed: PhotoFeed) {
        list.clear()
        photoFeed.items?.let { list.addAll(0, it) }
        listener.loaded()
    }

    private fun handleError(t: Throwable) {
        listener.failed()
    }
}
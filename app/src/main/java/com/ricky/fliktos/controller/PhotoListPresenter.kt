package com.ricky.fliktos.controller

import com.ricky.fliktos.model.Item
import com.ricky.fliktos.model.PhotoFeed
import com.ricky.fliktos.network.FlickrServiceAPI
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PhotoListPresenter(
    private var listener: Listener,
    private var processScheduler: Scheduler = Schedulers.io(),
    private var androidScheduler: Scheduler = AndroidSchedulers.mainThread()
) {
    /**
     * Listener to be implemented by the view to receive updates.
     */
    interface Listener {
        /**
         * PhotoFeed loading is in progress.
         */
        fun isLoading(): Boolean

        /**
         * PhotoFeed is loaded and the view should show the new content.
         */
        fun loaded()

        /**
         * PhotoFeed loading failed and the view should handle the error.
         */
        fun failed()
    }

    private val flickerService: FlickrServiceAPI = FlickrServiceAPI.create()
    val list: MutableList<Item> = mutableListOf()

    /**
     * Fetch the PhotoFeed.
     *
     * @param tags list of tags to include, if not empty.
     */
    fun fetch(tags: List<String>) {
        flickerService.getPublicPhotoFeed(tags)
            .subscribeOn(processScheduler)
            .observeOn(androidScheduler)
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
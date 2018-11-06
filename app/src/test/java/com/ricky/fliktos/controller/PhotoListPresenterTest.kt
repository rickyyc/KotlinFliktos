package com.ricky.fliktos.controller

import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations


class PhotoListPresenterTest {
    private val testSchduler = TestScheduler()

    private var loaded_invoke = false

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @After
    fun tearDown() {
    }

    /**
     * Note: the test case depends on external service. This needs to be fixed for CI pipeline.
     */
    @Test
    fun fetch() {
        lateinit var photoListPresenter : PhotoListPresenter

        val listener: PhotoListPresenter.Listener = object : PhotoListPresenter.Listener {
            override fun isLoading(): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun loaded() {
                loaded_invoke = photoListPresenter.list.isNotEmpty()
            }

            override fun failed() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }

        photoListPresenter = PhotoListPresenter(listener, testSchduler, testSchduler)

        photoListPresenter.fetch(emptyList())

        testSchduler.triggerActions()

        Assert.assertTrue(loaded_invoke)
    }
}
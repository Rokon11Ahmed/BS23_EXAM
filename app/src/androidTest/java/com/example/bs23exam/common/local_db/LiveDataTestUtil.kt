package com.example.bs23exam.common.local_db

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException


/**
 * Get the value from a LiveData object. We're waiting for LiveData to emit, for 2 seconds.
 * Once we got a notification via onChanged, we stop observing.
 */
@Throws(InterruptedException::class)
fun <T> LiveData<T>.getOrAwaitValue(): T? {
    var data :T? = null
    val latch = CountDownLatch(1)

    val observer: Observer<T> = object : Observer<T> {
        override fun onChanged(t: T) {
            data = t
            this@getOrAwaitValue.removeObserver(this)
            latch.countDown()
        }
    }
    this.observeForever(observer)
    try {
        if (!latch.await(2, TimeUnit.SECONDS)){
            throw TimeoutException( "Times Up")
        }
    }finally {
        this.removeObserver(observer)
    }
    return data as T?
}
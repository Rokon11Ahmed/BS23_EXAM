package com.example.bs23exam.utils

object Base {

    @JvmStatic
    val stgBaseUrl: String
        external get

    external fun stgBaseUrl(): String

    init {
        System.loadLibrary("bs23exam")
    }
}
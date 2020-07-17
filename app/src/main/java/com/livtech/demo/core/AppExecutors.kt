package com.livtech.demo.core

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

object AppExecutors {
    private val diskIO: Executor
    private val networkIO: Executor
    private val mainThread: Executor

    init {
        diskIO = Executors.newSingleThreadExecutor()
        networkIO = Executors.newFixedThreadPool(3)
        mainThread = MainThreadExecutor()
    }
    @JvmStatic
    fun diskIO(): Executor {
        return diskIO
    }
    @JvmStatic
    fun networkIO(): Executor {
        return networkIO
    }
    @JvmStatic
    fun mainThread(): Executor {
        return mainThread
    }

    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())
        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }
}
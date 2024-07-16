package com.example.openeye

import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

open class BaseApp: Application() {
    companion object {
        lateinit var mContext: BaseApp
    }

    override fun onCreate() {
        super.onCreate()
        mContext=this
    }
}
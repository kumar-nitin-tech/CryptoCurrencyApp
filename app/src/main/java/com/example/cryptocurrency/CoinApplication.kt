package com.example.cryptocurrency

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//This Application class to give dagger hilt information about the application so that dagger have application context if needed
@HiltAndroidApp
class CoinApplication : Application()
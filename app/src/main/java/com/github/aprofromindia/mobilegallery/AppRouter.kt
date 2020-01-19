package com.github.aprofromindia.mobilegallery

import android.app.Activity
import android.content.Intent
import com.github.aprofromindia.mobilegallery.detail.DetailActivity

class AppRouter {
    fun showDetail(activity: Activity) {
        activity.startActivity(Intent(activity, DetailActivity::class.java))
    }
}
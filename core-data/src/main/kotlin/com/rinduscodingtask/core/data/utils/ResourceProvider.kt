package com.rinduscodingtask.core.data.utils

import android.content.Context

class ResourceProvider(private val context: Context) {

    fun getString(resourceId: Int): String {
        return context.getString(resourceId)
    }
}

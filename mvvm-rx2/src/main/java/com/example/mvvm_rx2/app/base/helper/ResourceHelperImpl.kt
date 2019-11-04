package com.example.mvvm_rx2.app.base.helper

import android.app.Application
import com.example.mvvm_rx2.model.base.helper.ResourceHelper

/**
 * @author burkd
 * @since 2019-11-05
 */
class ResourceHelperImpl(
        private val context: Application
): ResourceHelper {
    override fun getString(stringResId: Int): String = context.getString(stringResId)
}
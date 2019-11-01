package com.example.mvvm_rx2.app.helper

import android.content.Context
import com.example.mvvm_rx2.model.base.helper.MessageHelper

/**
 * @author burkd
 * @since 2019-11-01
 */
class MessageHelperImpl(
        val context: Context
) : MessageHelper {
    override fun showingGeneralToast(message: Int) {
    }

    override fun showingGeneralToast(message: String) {
    }

    override fun showingErrorToast(message: Int) {
    }

    override fun showingErrorToast(message: String) {
    }


}
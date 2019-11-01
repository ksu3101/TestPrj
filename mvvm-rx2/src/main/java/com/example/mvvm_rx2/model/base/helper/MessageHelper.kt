package com.example.mvvm_rx2.model.base.helper

import androidx.annotation.StringRes

/**
 * @author burkd
 * @since 2019-11-01
 */
interface MessageHelper {

    fun showingGeneralToast(@StringRes message: Int)

    fun showingGeneralToast(message: String)

    fun showingErrorToast(@StringRes message: Int)

    fun showingErrorToast(message: String)

}
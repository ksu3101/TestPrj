package com.example.domain.domain

import com.example.domain.base.BaseLifecycleOwnViewModel

/**
 *
 * @author ksu3101
 * @since 2019-10-16
 */
class ToasterViewModel(

) : BaseLifecycleOwnViewModel<MessageState>() {


    override fun render(state: MessageState): Boolean {

        return true
    }
}
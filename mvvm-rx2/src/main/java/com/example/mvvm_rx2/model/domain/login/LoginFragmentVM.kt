package com.example.mvvm_rx2.model.domain.login

import com.example.mvvm_rx2.R
import com.example.mvvm_rx2.model.base.BaseLifecycleOwnViewModel
import com.example.mvvm_rx2.model.base.CombinedLiveData
import com.example.mvvm_rx2.model.base.helper.MessageHelper
import com.example.mvvm_rx2.model.domain.AppStore


/**
 * @author burkd
 * @since 2019-11-01
 */
class LoginFragmentVM(
        val appStore: AppStore,
        val messageHelper: MessageHelper
) : BaseLifecycleOwnViewModel<LoginState>() {

    val userId = CombinedLiveData<String>()
    val passWord = CombinedLiveData<String>()

    override fun render(state: LoginState): Boolean {
        // nothing to do ..
        return true
    }

    fun onClickedLoginButton() {
        login(userId.get(), passWord.get())
    }

    private fun login(userId: String?, pw: String?) {
        if (userId.isNullOrEmpty() || pw.isNullOrEmpty()) {
            messageHelper.showingErrorToast(R.string.login_error_commmon)
            return
        }
        appStore.dispatch(UserLoginAction(userId, pw))
    }

}
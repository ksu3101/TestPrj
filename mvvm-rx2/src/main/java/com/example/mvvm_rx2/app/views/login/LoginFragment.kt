package com.example.mvvm_rx2.app.views.login

import com.example.mvvm_rx2.R
import com.example.mvvm_rx2.model.base.BaseFragment
import com.example.mvvm_rx2.model.domain.login.LoginFragmentVM
import com.example.mvvm_rx2.model.domain.login.UserAuthState
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author burkd
 * @since 2019-11-01
 */
class LoginFragment: BaseFragment<UserAuthState>() {

    val loginFragVM : LoginFragmentVM by viewModel()

    companion object {
        fun newInstance(): LoginFragment = LoginFragment()
    }

    override fun getLayoutId(): Int = R.layout.login_fragment

}
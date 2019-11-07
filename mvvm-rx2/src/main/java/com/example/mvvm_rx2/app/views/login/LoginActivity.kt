package com.example.mvvm_rx2.app.views.login

import android.os.Bundle
import com.example.mvvm_rx2.R
import com.example.mvvm_rx2.extension.replaceFragment
import com.example.mvvm_rx2.model.base.BaseActivity

/**
 * @author burkd
 * @since 2019-11-01
 */
class LoginActivity: BaseActivity() {

    override fun getLayoutId(): Int = R.layout.login_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        replaceFragment(
                replaceFragment = LoginFragment.newInstance(),
                containerResId = R.id.login_activity_container
        )
    }

}
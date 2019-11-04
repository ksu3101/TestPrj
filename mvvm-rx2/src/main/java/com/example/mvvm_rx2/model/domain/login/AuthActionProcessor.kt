package com.example.mvvm_rx2.model.domain.login

import com.example.mvvm_rx2.R
import com.example.mvvm_rx2.extension.rx.actionTransformer
import com.example.mvvm_rx2.extension.rx.andActionObservable
import com.example.mvvm_rx2.extension.rx.createActionProcessor
import com.example.mvvm_rx2.model.base.helper.ResourceHelper
import com.example.mvvm_rx2.model.base.redux.Action
import com.example.mvvm_rx2.model.base.redux.ActionProcessor
import com.example.mvvm_rx2.model.base.redux.Store
import com.example.mvvm_rx2.model.domain.AppStore
import com.example.mvvm_rx2.model.domain.common.MessageAction
import com.example.mvvm_rx2.model.domain.common.ShowingErrorToast
import com.example.mvvm_rx2.model.domain.common.ShowingGeneralToast
import com.example.mvvm_rx2.model.domain.login.dto.User
import com.example.mvvm_rx2.model.domain.login.dto.isAvailableUser
import io.reactivex.Observable

/**
 * @author burkd
 * @since 2019-11-05
 */
class AuthActionProcessor(
        appStore: AppStore,
        repo: AuthRepository,
        resourceHelper: ResourceHelper
) : ActionProcessor<UserAuthState> {
    companion object {
        private val DUMMY_USER = User("", "")
    }

    override fun run(action: Observable<Action>, store: Store<UserAuthState>): Observable<out Action> {
        return action.compose(actionProcessor)
    }

    private val actionProcessor = createActionProcessor { shared ->
        arrayOf(
                shared.ofType(UserLoginAction::class.java).compose(login),
                shared.ofType(UserLogoutAction::class.java).compose(logOut)
        )
    }

    private val login = actionTransformer<UserLoginAction> { action ->
        repo.login(action.userId, action.passWord)
                .defaultIfEmpty(DUMMY_USER)
                .map<Action> {
                    if (it.isAvailableUser()) {
                        UserLoginSuccess(it)
                    } else {
                        throw IllegalArgumentException(resourceHelper.getString(R.string.login_error_not_available_infos))
                    }
                }
                .onErrorReturn { handleError(it) }
                .toObservable()
    }

    private val logOut = actionTransformer<UserLogoutAction> { action ->
        repo.logOut(action.userId)
                .andActionObservable {
                    appStore.dispatch(ShowingGeneralToast(R.string.logout_success_guide))   // check after dispatch stream.
                    UserLogoutSuccess
                }
    }

    private fun handleError(throwable: Throwable, action: Action? = null): MessageAction {
        return ShowingErrorToast(message = throwable.message)
    }

}

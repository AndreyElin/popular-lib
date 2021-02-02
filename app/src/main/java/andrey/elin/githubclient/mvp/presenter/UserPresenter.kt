package andrey.elin.githubclient.mvp.presenter

import andrey.elin.githubclient.mvp.view.UserView
import andrey.elin.githubclient.navigation.Screens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UserPresenter(val router: Router) :
        MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun backPressed(): Boolean {
        router.navigateTo(Screens.UsersScreen())
        return true
    }
}
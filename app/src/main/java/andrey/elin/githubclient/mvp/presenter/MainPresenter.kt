package andrey.elin.githubclient.mvp.presenter

import moxy.MvpPresenter
import andrey.elin.githubclient.mvp.view.MainView
import andrey.elin.githubclient.navigation.Screens
import ru.terrakok.cicerone.Router

class MainPresenter(val router: Router) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        router.replaceScreen(Screens.UsersScreen())
    }

    fun backClicked() {
        router.exit()
    }

}
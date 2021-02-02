package andrey.elin.githubclient.mvp.presenter

import moxy.MvpPresenter
import andrey.elin.githubclient.mvp.model.entity.GithubUser
import andrey.elin.githubclient.mvp.model.entity.GithubUsersRepo
import andrey.elin.githubclient.mvp.presenter.list.IUserListPresenter
import andrey.elin.githubclient.mvp.view.UsersView
import andrey.elin.githubclient.mvp.view.list.UserItemView
import andrey.elin.githubclient.navigation.Screens
import android.content.ContentValues.TAG
import android.util.Log
import ru.terrakok.cicerone.Router

class UsersPresenter(val usersRepo: GithubUsersRepo, val router: Router) : MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            // TODO:
            val login = usersListPresenter.users[itemView.pos].login
            router.navigateTo(Screens.UserScreen(login))
        }
    }

    private fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
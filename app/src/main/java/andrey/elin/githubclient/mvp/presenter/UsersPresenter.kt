package andrey.elin.githubclient.mvp.presenter

import andrey.elin.githubclient.mvp.model.entity.GithubUser
import andrey.elin.githubclient.mvp.model.repo.IGithubUsersRepo
import andrey.elin.githubclient.mvp.presenter.list.IUserListPresenter
import andrey.elin.githubclient.mvp.view.UsersView
import andrey.elin.githubclient.mvp.view.list.UserItemView
import andrey.elin.githubclient.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class UsersPresenter(
    val mainThreadScheduler: Scheduler
) :
    MvpPresenter<UsersView>() {

    @Inject
    lateinit var usersRepo: IGithubUsersRepo
    @Inject
    lateinit var router: Router

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.login?.let { view.setLogin(it) }
            user.avatarUrl?.let { view.loadAvatar(it) }
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            val user = usersListPresenter.users[itemView.pos]
            router.navigateTo(Screens.UserScreen(user))
        }
    }

    private fun loadData() {
        usersRepo.getUsers()
            .observeOn(mainThreadScheduler)
            .subscribe({ users ->
                usersListPresenter.users.clear()
                usersListPresenter.users.addAll(users)
                viewState.updateList()
            }, {
                println("Error: ${it.message}")
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        viewState.release()
    }
}
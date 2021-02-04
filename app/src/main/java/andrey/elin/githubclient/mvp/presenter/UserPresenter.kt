package andrey.elin.githubclient.mvp.presenter

import andrey.elin.githubclient.mvp.model.entity.GithubRepository
import andrey.elin.githubclient.mvp.model.entity.GithubUser
import andrey.elin.githubclient.mvp.model.entity.room.Database
import andrey.elin.githubclient.mvp.model.repo.IGithubRepositoriesRepo
import andrey.elin.githubclient.mvp.presenter.list.IRepositoryListPresenter
import andrey.elin.githubclient.mvp.view.UserView
import andrey.elin.githubclient.mvp.view.list.RepositoryItemView
import andrey.elin.githubclient.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class UserPresenter (val user: GithubUser ) : MvpPresenter<UserView>() {

    @Inject
    lateinit var mainThreadScheduler: Scheduler
    @Inject
    lateinit var database: Database
    @Inject
    lateinit var router: Router
    @Inject
    lateinit var repositoriesRepo: IGithubRepositoriesRepo

    class RepositoriesListPresenter : IRepositoryListPresenter {
        val repositories = mutableListOf<GithubRepository>()
        override var itemClickListener: ((RepositoryItemView) -> Unit)? = null
        override fun getCount() = repositories.size

        override fun bindView(view: RepositoryItemView) {
            val repository = repositories[view.pos]
            repository.name?.let { view.setName(it) }
        }
    }

    val repositoriesListPresenter = RepositoriesListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        repositoriesListPresenter.itemClickListener = { itemView ->
            val repository = repositoriesListPresenter.repositories[itemView.pos]
            router.navigateTo(Screens.RepositoryScreen(repository))
        }
    }

    fun loadData() {
        repositoriesRepo.getRepositories(user)
            .observeOn(mainThreadScheduler)
            .subscribe({ repositories ->
                repositoriesListPresenter.repositories.clear()
                repositoriesListPresenter.repositories.addAll(repositories)
                viewState.updateList()
            }, {
                println("Error: ${it.message}")
            })
    }

    fun backPressed(): Boolean {
        router.navigateTo(Screens.UsersScreen())
        return true
    }
}
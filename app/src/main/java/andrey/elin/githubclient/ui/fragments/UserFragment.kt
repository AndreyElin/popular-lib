package andrey.elin.githubclient.ui.fragments

import andrey.elin.githubclient.ApiHolder
import andrey.elin.githubclient.App
import andrey.elin.githubclient.R
import andrey.elin.githubclient.mvp.model.cache.room.RoomGithubRepositoriesCache
import andrey.elin.githubclient.mvp.model.entity.GithubUser
import andrey.elin.githubclient.mvp.model.entity.room.Database
import andrey.elin.githubclient.mvp.model.repo.retrofit.RetrofitGithubRepositoriesRepo
import andrey.elin.githubclient.mvp.presenter.UserPresenter
import andrey.elin.githubclient.mvp.view.UserView
import andrey.elin.githubclient.ui.BackButtonListener
import andrey.elin.githubclient.ui.adapter.ReposotoriesRVAdapter
import andrey.elin.githubclient.ui.network.AndroidNetworkStatus
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_user.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {
    companion object {
        private const val USER_ARG = "user"

        fun newInstance(user: GithubUser) = UserFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_ARG, user)
            }
        }
    }

    var adapter: ReposotoriesRVAdapter? = null

    val presenter: UserPresenter by moxyPresenter {
        val user = arguments?.getParcelable<GithubUser>(USER_ARG) as GithubUser

        UserPresenter(user, AndroidSchedulers.mainThread(),
            RetrofitGithubRepositoriesRepo(ApiHolder().api, AndroidNetworkStatus(App.instance), RoomGithubRepositoriesCache(Database.getInstance())),
            App.instance.router
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        View.inflate(context, R.layout.fragment_user, null)

    override fun init() {
        rv_repositories.layoutManager = LinearLayoutManager(context)
        adapter = ReposotoriesRVAdapter(presenter.repositoriesListPresenter)
        rv_repositories.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}
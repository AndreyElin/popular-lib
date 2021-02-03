package andrey.elin.githubclient.ui.fragments

import andrey.elin.githubclient.ApiHolder
import andrey.elin.githubclient.App
import andrey.elin.githubclient.R
import andrey.elin.githubclient.mvp.model.entity.room.Database
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_users.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import andrey.elin.githubclient.mvp.model.repo.retrofit.RetrofitGithubUsersRepo
import andrey.elin.githubclient.mvp.presenter.UsersPresenter
import andrey.elin.githubclient.mvp.view.UsersView
import andrey.elin.githubclient.ui.BackButtonListener
import andrey.elin.githubclient.ui.adapter.UsersRVAdapter
import andrey.elin.githubclient.ui.image.GlideImageLoader
import andrey.elin.githubclient.ui.network.AndroidNetworkStatus
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    companion object {
        fun newInstance() = UsersFragment()
    }

    val presenter: UsersPresenter by moxyPresenter { UsersPresenter(AndroidSchedulers.mainThread(),
        RetrofitGithubUsersRepo(ApiHolder().api, AndroidNetworkStatus(App.instance), Database.getInstance()),
        App.instance.router) }

    var adapter: UsersRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        View.inflate(context, R.layout.fragment_users, null)

    override fun init() {
        rv_users.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader())
        rv_users.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}
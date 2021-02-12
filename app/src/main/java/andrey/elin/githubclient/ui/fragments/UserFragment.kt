package andrey.elin.githubclient.ui.fragments

import andrey.elin.githubclient.App
import andrey.elin.githubclient.R
import andrey.elin.githubclient.di.repository.RepositorySubComponent
import andrey.elin.githubclient.mvp.model.entity.GithubUser
import andrey.elin.githubclient.mvp.presenter.UserPresenter
import andrey.elin.githubclient.mvp.view.UserView
import andrey.elin.githubclient.ui.BackButtonListener
import andrey.elin.githubclient.ui.MainActivity
import andrey.elin.githubclient.ui.adapter.ReposotoriesRVAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
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

    private var repositorySubComponent: RepositorySubComponent? = null

    val presenter: UserPresenter by moxyPresenter {
        val user = arguments?.getParcelable<GithubUser>(USER_ARG) as GithubUser

        repositorySubComponent = App.instance.initRepositorySubComponent()

        UserPresenter(user).apply {
            repositorySubComponent?.inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = View.inflate(context, R.layout.fragment_user, null)




    override fun init() {
        rv_repositories.layoutManager = LinearLayoutManager(context)
        adapter = ReposotoriesRVAdapter(presenter.repositoriesListPresenter)
        rv_repositories.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun release() {
        repositorySubComponent = null
        App.instance.releaseRepositorySubComponent()
    }

    override fun backPressed() = presenter.backPressed()
}
package andrey.elin.githubclient.ui.fragments

import andrey.elin.githubclient.App
import andrey.elin.githubclient.R
import andrey.elin.githubclient.mvp.presenter.UserPresenter
import andrey.elin.githubclient.mvp.view.UserView
import andrey.elin.githubclient.ui.BackButtonListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_user.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {

    companion object {

        private const val ARG_LOGIN = "login"

        fun newInstance(login: String) =
                UserFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_LOGIN, login)
                    }
                }
    }

    private var login: String? = null

    val presenter: UserPresenter by moxyPresenter {
        UserPresenter(App.instance.router)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            login = it.getString(ARG_LOGIN)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun init() {
        login?.let {
            user_login.text = it
        } ?: let {
            user_login.text = ""
        }
    }

    override fun backPressed(): Boolean {
        presenter.backPressed()
        return true
    }


}
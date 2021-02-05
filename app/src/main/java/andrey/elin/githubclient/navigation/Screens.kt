package andrey.elin.githubclient.navigation

import andrey.elin.githubclient.mvp.model.entity.GithubRepository
import andrey.elin.githubclient.mvp.model.entity.GithubUser
import andrey.elin.githubclient.ui.fragments.RepositoryFragment
import andrey.elin.githubclient.ui.fragments.UserFragment
import andrey.elin.githubclient.ui.fragments.UsersFragment
import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class UsersScreen() : SupportAppScreen() {
        override fun getFragment() = UsersFragment.newInstance()
    }

    class UserScreen(val user: GithubUser) : SupportAppScreen() {
        override fun getFragment() = UserFragment.newInstance(user)
    }

    class RepositoryScreen(val user: GithubRepository) : SupportAppScreen() {
        override fun getFragment() = RepositoryFragment.newInstance(user)
    }
}
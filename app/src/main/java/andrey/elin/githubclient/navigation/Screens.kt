package andrey.elin.githubclient.navigation

import andrey.elin.githubclient.ui.fragments.UserFragment
import andrey.elin.githubclient.ui.fragments.UsersFragment
import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class UsersScreen() : SupportAppScreen() {
        override fun getFragment() = UsersFragment.newInstance()
    }

    class UserScreen(val login: String) : SupportAppScreen() {
        override fun getFragment() = UserFragment.newInstance(login)
    }
}
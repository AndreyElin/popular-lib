package andrey.elin.githubclient.di

import andrey.elin.githubclient.di.modules.*
import andrey.elin.githubclient.mvp.presenter.MainPresenter
import andrey.elin.githubclient.mvp.presenter.UsersPresenter
import andrey.elin.githubclient.ui.MainActivity
import andrey.elin.githubclient.ui.fragments.RepositoryFragment
import andrey.elin.githubclient.ui.fragments.UserFragment
import andrey.elin.githubclient.ui.fragments.UsersFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        DatabaseModule::class,
        CiceroneModule::class,
        RepoModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)

    // ДЗ - избавиться от зависимостей ниже
    fun inject(usersFragment: UsersFragment)
    fun inject(userFragment: UserFragment)
    fun inject(repositoryFragment: RepositoryFragment)
}
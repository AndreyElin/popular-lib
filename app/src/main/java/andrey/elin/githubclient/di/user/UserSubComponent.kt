package andrey.elin.githubclient.di.user

import andrey.elin.githubclient.di.repository.RepositorySubComponent
import andrey.elin.githubclient.di.user.module.UserModule
import andrey.elin.githubclient.mvp.presenter.UsersPresenter
import andrey.elin.githubclient.ui.adapter.UsersRVAdapter
import dagger.Component

@UserScope
@Component(
    modules = [
        UserModule::class

    ]
)
interface UserSubComponent {
    fun repositorySubComponent() : RepositorySubComponent

    fun inject(usersPresenter: UsersPresenter)
    fun inject(usersRVAdapter: UsersRVAdapter)
}
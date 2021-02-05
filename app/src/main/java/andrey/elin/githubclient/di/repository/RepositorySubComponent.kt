package andrey.elin.githubclient.di.repository

import andrey.elin.githubclient.di.repository.module.RepositoryModule
import andrey.elin.githubclient.mvp.presenter.RepositoryPresenter
import andrey.elin.githubclient.mvp.presenter.UserPresenter
import dagger.Subcomponent

@RepositoryScope
@Subcomponent(
    modules = [
        RepositoryModule::class

    ]
)
interface RepositorySubComponent {
    fun inject(userPresenter: UserPresenter)
    fun inject(repositoryPresenter: RepositoryPresenter)
}
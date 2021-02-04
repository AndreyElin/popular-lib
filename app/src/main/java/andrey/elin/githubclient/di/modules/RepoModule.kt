package andrey.elin.githubclient.di.modules

import andrey.elin.githubclient.mvp.model.api.IDataSource
import andrey.elin.githubclient.mvp.model.cache.IGithubRepositoriesCache
import andrey.elin.githubclient.mvp.model.cache.IGithubUsersCache
import andrey.elin.githubclient.mvp.model.network.INetworkStatus
import andrey.elin.githubclient.mvp.model.repo.IGithubRepositoriesRepo
import andrey.elin.githubclient.mvp.model.repo.IGithubUsersRepo
import andrey.elin.githubclient.mvp.model.repo.retrofit.RetrofitGithubRepositoriesRepo
import andrey.elin.githubclient.mvp.model.repo.retrofit.RetrofitGithubUsersRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {
    @Singleton
    @Provides
    fun usersRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IGithubUsersCache) : IGithubUsersRepo =
        RetrofitGithubUsersRepo(api, networkStatus, cache)

    @Singleton
    @Provides
    fun repositoriesRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IGithubRepositoriesCache): IGithubRepositoriesRepo =
        RetrofitGithubRepositoriesRepo(api, networkStatus, cache)
}
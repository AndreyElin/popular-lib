package andrey.elin.githubclient.di.repository.module

import andrey.elin.githubclient.di.repository.RepositoryScope
import andrey.elin.githubclient.mvp.model.api.IDataSource
import andrey.elin.githubclient.mvp.model.cache.IGithubRepositoriesCache
import andrey.elin.githubclient.mvp.model.cache.room.RoomGithubRepositoriesCache
import andrey.elin.githubclient.mvp.model.entity.room.Database
import andrey.elin.githubclient.mvp.model.network.INetworkStatus
import andrey.elin.githubclient.mvp.model.repo.IGithubRepositoriesRepo
import andrey.elin.githubclient.mvp.model.repo.retrofit.RetrofitGithubRepositoriesRepo
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun repositoriesCache(database: Database): IGithubRepositoriesCache {
        return RoomGithubRepositoriesCache(database)
    }

    @RepositoryScope
    @Provides
    fun repositoriesRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IGithubRepositoriesCache): IGithubRepositoriesRepo =
        RetrofitGithubRepositoriesRepo(api, networkStatus, cache)
}
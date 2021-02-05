package andrey.elin.githubclient.mvp.model.repo.retrofit

import andrey.elin.githubclient.mvp.model.api.IDataSource
import andrey.elin.githubclient.mvp.model.cache.IGithubRepositoriesCache
import andrey.elin.githubclient.mvp.model.entity.GithubRepository
import andrey.elin.githubclient.mvp.model.entity.GithubUser
import andrey.elin.githubclient.mvp.model.network.INetworkStatus
import andrey.elin.githubclient.mvp.model.repo.IGithubRepositoriesRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubRepositoriesRepo(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val cache: IGithubRepositoriesCache
) :
    IGithubRepositoriesRepo {
    override fun getRepositories(user: GithubUser) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user.reposUrl?.let { url ->
                    api.getRepositories(url).flatMap { repositories ->
                        cache.putUserRepos(user, repositories).toSingleDefault(repositories)
                    }
                } ?: Single.error<List<GithubRepository>>(RuntimeException("User has no repos url"))
                    .subscribeOn(Schedulers.io())
            } else {
                cache.getUserRepos(user)
            }
        }.subscribeOn(Schedulers.io())
}
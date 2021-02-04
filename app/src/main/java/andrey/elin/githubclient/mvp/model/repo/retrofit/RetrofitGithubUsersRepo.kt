package andrey.elin.githubclient.mvp.model.repo.retrofit

import andrey.elin.githubclient.mvp.model.api.IDataSource
import andrey.elin.githubclient.mvp.model.cache.IGithubUsersCache
import andrey.elin.githubclient.mvp.model.network.INetworkStatus
import andrey.elin.githubclient.mvp.model.repo.IGithubUsersRepo
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val cache: IGithubUsersCache
) : IGithubUsersRepo {
    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUsers().flatMap { users ->
                cache.putUsers(users).toSingleDefault(users)
            }
        } else {
                cache.getUsers()
        }
    }.subscribeOn(Schedulers.io())
}
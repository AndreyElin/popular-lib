package andrey.elin.githubclient.mvp.model.repo.retrofit

import andrey.elin.githubclient.mvp.model.api.IDataSource
import andrey.elin.githubclient.mvp.model.entity.GithubRepository
import andrey.elin.githubclient.mvp.model.entity.GithubUser
import andrey.elin.githubclient.mvp.model.entity.room.Database
import andrey.elin.githubclient.mvp.model.entity.room.RoomGithubRepository
import andrey.elin.githubclient.mvp.model.network.INetworkStatus
import andrey.elin.githubclient.mvp.model.repo.IGithubRepositoriesRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubRepositoriesRepo(val api: IDataSource, val networkStatus: INetworkStatus, val db: Database) :
    IGithubRepositoriesRepo {
    override fun getRepositories(user: GithubUser) = networkStatus.isOnlineSingle().flatMap {isOnline ->
        if (isOnline) {
            user.reposUrl?.let {url ->
                api.getRepositories(url).flatMap {repositories ->
                    Single.fromCallable{
                        val roomUser = user.login?.let { db.userDao.findByLogin(it) } ?: throw RuntimeException("No such user in cache")
                        val roomRepos = repositories.map { RoomGithubRepository(it.id ?: "", it.name ?: "", it.forksCount ?: 0, roomUser.id) }

                        db.repositoryDao.insert(roomRepos)

                        repositories
                    }
                }
            }
        } else {
            Single.fromCallable {
                val roomUser = user.login?.let { db.userDao.findByLogin(it) } ?: throw RuntimeException("No such user in cache")
                db.repositoryDao.findForUser(roomUser.id).map { GithubRepository(it.id, it.name, it.forksCount) }
            }
        }
    }.subscribeOn(Schedulers.io())
}
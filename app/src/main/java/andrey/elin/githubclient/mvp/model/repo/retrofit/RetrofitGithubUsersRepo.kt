package andrey.elin.githubclient.mvp.model.repo.retrofit

import andrey.elin.githubclient.mvp.model.api.IDataSource
import andrey.elin.githubclient.mvp.model.repo.IGithubUsersRepo
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(val api: IDataSource) : IGithubUsersRepo {
    override fun getUsers() = api.getUsers().subscribeOn(Schedulers.io())
}
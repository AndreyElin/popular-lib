package andrey.elin.githubclient.mvp.model.repo

import andrey.elin.githubclient.mvp.model.entity.GithubRepository
import andrey.elin.githubclient.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubRepositoriesRepo {
    fun getRepositories(user: GithubUser): Single<List<GithubRepository>>
}
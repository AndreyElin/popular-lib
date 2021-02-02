package andrey.elin.githubclient.mvp.model.entity

import andrey.elin.githubclient.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Observable

class GithubUsersRepo {
    private val repositories = listOf (
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5")
    )

//    fun getUsers() : List<GithubUser> {
//        return repositories
//    }

    fun getUsers(): Observable<GithubUser> {
        return Observable.fromIterable(repositories)
    }
}
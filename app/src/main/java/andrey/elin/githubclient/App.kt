package andrey.elin.githubclient

import andrey.elin.githubclient.di.AppComponent
import andrey.elin.githubclient.di.DaggerAppComponent
import andrey.elin.githubclient.di.modules.AppModule
import andrey.elin.githubclient.di.repository.RepositorySubComponent
import andrey.elin.githubclient.di.user.UserSubComponent
import android.app.Application

class App : Application() {

    lateinit var appComponent: AppComponent
        private set

    var userSubComponent: UserSubComponent? = null
        private set

    var repositorySubComponent: RepositorySubComponent? = null
        private set


    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun initUserSubComponent() = appComponent.userSubComponent().also {
        userSubComponent = it
    }

    fun releaseUserSubComponent() {
        userSubComponent = null
    }

    fun initRepositorySubComponent() = userSubComponent?.repositorySubComponent().also {
        repositorySubComponent = it
    }

    fun releaseRepositorySubComponent() {
        repositorySubComponent = null
    }
}

package andrey.elin.githubclient

import andrey.elin.githubclient.di.AppComponent
import andrey.elin.githubclient.di.DaggerAppComponent
import andrey.elin.githubclient.di.modules.AppModule
import android.app.Application

class App : Application() {

    lateinit var appComponent: AppComponent


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

}

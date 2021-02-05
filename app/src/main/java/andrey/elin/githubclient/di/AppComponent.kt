package andrey.elin.githubclient.di

import andrey.elin.githubclient.di.modules.*
import andrey.elin.githubclient.di.user.UserSubComponent
import andrey.elin.githubclient.mvp.presenter.MainPresenter
import andrey.elin.githubclient.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        DatabaseModule::class,
        CiceroneModule::class,
        ImageModule::class
    ]
)
interface AppComponent {
    fun userSubComponent(): UserSubComponent

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)

}
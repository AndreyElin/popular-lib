package andrey.elin.githubclient.di.modules

import andrey.elin.githubclient.App
import andrey.elin.githubclient.mvp.model.entity.room.Database
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun database(app: App) = Room.databaseBuilder(app, Database::class.java, Database.DB_NAME).build()

}
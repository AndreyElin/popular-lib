package andrey.elin.githubclient.di.modules

import andrey.elin.githubclient.App
import andrey.elin.githubclient.mvp.model.cache.IGithubRepositoriesCache
import andrey.elin.githubclient.mvp.model.cache.IGithubUsersCache
import andrey.elin.githubclient.mvp.model.cache.room.RoomGithubRepositoriesCache
import andrey.elin.githubclient.mvp.model.cache.room.RoomGithubUsersCache
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

    @Singleton
    @Provides
    fun usersCache(database: Database): IGithubUsersCache {
        return RoomGithubUsersCache(database)
    }

    @Singleton
    @Provides
    fun repositoriesCache(database: Database): IGithubRepositoriesCache {
        return RoomGithubRepositoriesCache(database)
    }
}
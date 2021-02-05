package andrey.elin.githubclient.mvp.model.entity.room

import andrey.elin.githubclient.mvp.model.entity.room.dao.RepositoryDao
import andrey.elin.githubclient.mvp.model.entity.room.dao.UserDao
import androidx.room.RoomDatabase

@androidx.room.Database(entities = [RoomGithubUser::class, RoomGithubRepository::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao

    companion object {
        const val DB_NAME = "database.db"
    }

}
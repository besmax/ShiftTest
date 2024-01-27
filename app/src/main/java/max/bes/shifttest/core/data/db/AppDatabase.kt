package max.bes.shifttest.core.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import max.bes.shifttest.users.data.db.dao.UserDao
import max.bes.shifttest.users.data.db.entities.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract val userDao: UserDao
}
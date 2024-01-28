package max.bes.shifttest.users.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import max.bes.shifttest.users.data.db.entities.UserEntity
import max.bes.shifttest.users.domain.models.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(entity: UserEntity)

    @Insert
    suspend fun insertAllUsers(users: List<UserEntity>)

    @Query("SELECT * FROM users_table")
    suspend fun getAllUsers(): List<UserEntity>

    @Query("DELETE FROM users_table")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM users_table WHERE id=:id")
    suspend fun getUserById(id: Int): User?
}
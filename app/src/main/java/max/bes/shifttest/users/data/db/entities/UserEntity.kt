package max.bes.shifttest.users.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstName: String,
    val lastName: String,
    val country: String,
    val city: String,
    val email: String,
    val phone: String,
    val pictureLarge: String,
    val pictureMedium: String,
    val pictureThumbnail: String,
    val latitude: String,
    val longitude: String
)
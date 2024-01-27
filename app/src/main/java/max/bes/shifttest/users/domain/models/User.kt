package max.bes.shifttest.users.domain.models

data class User(
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
)
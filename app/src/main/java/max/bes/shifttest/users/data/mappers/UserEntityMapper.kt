package max.bes.shifttest.users.data.mappers

import max.bes.shifttest.users.data.db.entities.UserEntity
import max.bes.shifttest.users.domain.models.User

fun UserEntity.map(): User = User(
    id = id,
    firstName = firstName,
    lastName = lastName,
    country = country,
    city = city,
    email = email,
    phone = phone,
    pictureLarge = pictureLarge,
    pictureMedium = pictureMedium,
    pictureThumbnail = pictureThumbnail,
    latitude = latitude,
    longitude = longitude
)
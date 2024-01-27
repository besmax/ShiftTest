package max.bes.shifttest.users.domain.mappers

import max.bes.shifttest.users.data.db.entities.UserEntity
import max.bes.shifttest.users.domain.models.User

fun User.map(): UserEntity = UserEntity(
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
)
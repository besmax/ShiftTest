package max.bes.shifttest.users.data.mappers

import max.bes.shifttest.users.data.db.entities.UserEntity
import max.bes.shifttest.core.data.dto.UserDto
import max.bes.shifttest.users.domain.models.User

fun UserDto.mapToEntity(): UserEntity = UserEntity(
    id = 0,
    firstName = name.first,
    lastName = name.last,
    country = location.country,
    city = location.city,
    email = location.email,
    phone = location.phone,
    pictureLarge = location.picture.large,
    pictureMedium = location.picture.medium,
    pictureThumbnail = location.picture.thumbnail,
)

fun UserDto.map(): User = User(
    id = 0,
    firstName = name.first,
    lastName = name.last,
    country = location.country,
    city = location.city,
    email = location.email,
    phone = location.phone,
    pictureLarge = location.picture.large,
    pictureMedium = location.picture.medium,
    pictureThumbnail = location.picture.thumbnail,
)
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
    email = email,
    phone = phone,
    pictureLarge = picture.large,
    pictureMedium = picture.medium,
    pictureThumbnail = picture.thumbnail,
    latitude = location.coordinates.latitude,
    longitude = location.coordinates.longitude
)

fun UserDto.map(): User = User(
    id = 0,
    firstName = name.first,
    lastName = name.last,
    country = location.country,
    city = location.city,
    email = email,
    phone = phone,
    pictureLarge = picture.large,
    pictureMedium = picture.medium,
    pictureThumbnail = picture.thumbnail,
    latitude = location.coordinates.latitude,
    longitude = location.coordinates.longitude
)
package max.bes.shifttest.core.data.dto

data class UserDto(
    val gender: String,
    val name: NameDto,
    val location: LocationDto,
)

data class NameDto(
    val title: String,
    val first: String,
    val last: String
)

data class LocationDto(
    val street: StreetDto,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
    val coordinates: CoordinatesDto,
    val email: String,
    val phone: String,
    val picture: PictureDto,
)

data class StreetDto(
    val number: Int,
    val name: String
)

data class CoordinatesDto(
    val latitude: String,
    val longitude: String
)

data class PictureDto(
    val large: String,
    val medium: String,
    val thumbnail: String
)

data class InfoDto(
    val seed: String,
    val results: Int,
    val page: Int,
    val version: String
)



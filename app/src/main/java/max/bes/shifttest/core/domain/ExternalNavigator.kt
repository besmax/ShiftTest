package max.bes.shifttest.core.domain

interface ExternalNavigator {

    fun sendEmail(address: String)

    fun makePhoneCall(phoneNumber: String)

    fun openMap(latitude: String, longitude: String)
}
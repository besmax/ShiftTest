package max.bes.shifttest.core.data

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import max.bes.shifttest.R
import max.bes.shifttest.core.domain.ExternalNavigator

class ExternalNavigatorImpl(
    @ApplicationContext private val appContext: Context
) : ExternalNavigator {
    override fun sendEmail(address: String) {
        val emailIntent = Intent().apply {
            data = Uri.parse(appContext.getString(R.string.uri_mailto))
            putExtra(Intent.EXTRA_EMAIL, arrayOf(address))
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            Intent.createChooser(this, null)
        }
        startActivity(appContext, emailIntent, null)
    }

    override fun makePhoneCall(phoneNumber: String) {
        val phoneCallIntent = Intent().apply {
            action = Intent.ACTION_DIAL
            data = Uri.parse(appContext.getString(R.string.tel, phoneNumber))
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            Intent.createChooser(this, null)
        }
        startActivity(appContext, phoneCallIntent, null)
    }

    override fun openMap(latitude: String, longitude: String) {
        val openMapIntent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse(appContext.getString(R.string.coordinates, latitude, longitude))
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            Intent.createChooser(this, null)
        }
        startActivity(appContext, openMapIntent, null)
    }
}
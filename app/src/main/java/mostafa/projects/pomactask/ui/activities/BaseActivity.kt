package mostafa.projects.pomactask.ui.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.icu.lang.UCharacter
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import androidx.annotation.RequiresApi
import mostafa.projects.pomactask.R

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

    fun Activity.showtoast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    // https://stackoverflow.com/a/57925521
    inline fun <reified T : Activity> Context.openActivity(block: Intent.() -> Unit = {}) {
        val intent = Intent(this, T::class.java)
        block(intent)
        startActivity(intent)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    fun <T : Parcelable> Intent.getParcel(key: String = "parcel_key"): T? {
        return this.getBundleExtra("parcel_bundle")?.getParcelable(key)
    }
}
package mostafa.projects.pomactask.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Link(
    val suggested_link_text: String,
    val type: String,
    val url: String
):Parcelable
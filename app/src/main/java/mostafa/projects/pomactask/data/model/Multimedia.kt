package mostafa.projects.pomactask.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Multimedia(
    val height: Int?,
    val src: String?,
    val type: String?,
    val width: Int?
): Parcelable
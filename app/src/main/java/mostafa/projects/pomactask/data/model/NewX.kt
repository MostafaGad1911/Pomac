package mostafa.projects.pomactask.data.model

data class NewX(
    val copyright: String,
    val has_more: Boolean,
    val num_results: Int,
    val results: ArrayList<Result>,
    val status: String
)
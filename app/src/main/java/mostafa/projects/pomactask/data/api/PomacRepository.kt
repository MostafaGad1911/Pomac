package mostafa.projects.pomactask

import mostafa.projects.pomactask.data.model.NewX
import mostafa.projects.pomactask.di.component.DaggerPomacComponent
import mostafa.projects.pomactask.di.component.PomacComponent
import retrofit2.Response
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap

class PomacRepository {

    var pomacComponent: PomacComponent

    @Inject
    lateinit var apiInterface: ApiInterface


    var pomac_headers = HashMap<String, String>()

    @Inject
    constructor() {
        pomacComponent = DaggerPomacComponent.create()

        pomac_headers["Accept"] = "application/json"
        pomac_headers["Content-Type"] = "application/json"

    }


    suspend fun getMovieReview(movie: String): Response<NewX> {
        var newsMap: HashMap<String, String> = HashMap()
        newsMap["query"] = movie
        newsMap["api-key"] = "HlrwLeZNPkwBNexUqATpZoxeBsU3hUE7"
        return apiInterface.getNews(headers = pomac_headers, newsMap = newsMap)
    }


}
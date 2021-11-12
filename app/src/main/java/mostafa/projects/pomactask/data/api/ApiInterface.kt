package mostafa.projects.pomactask

import mostafa.projects.pomactask.data.model.New
import mostafa.projects.pomactask.data.model.NewX
import retrofit2.Response
import retrofit2.http.*


interface ApiInterface {
    @GET("reviews/search.json")
    suspend fun getNews(
        @QueryMap newsMap:HashMap<String , String> ,
        @HeaderMap headers: Map<String, String>
    ): Response<NewX>


}
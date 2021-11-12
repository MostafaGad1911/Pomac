package mostafa.projects.pomactask.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import kotlinx.coroutines.launch
import mostafa.projects.pomactask.PomacRepository
import mostafa.projects.pomactask.data.model.NewX
import mostafa.projects.pomactask.di.component.DaggerPomacComponent
import mostafa.projects.pomactask.di.component.PomacComponent
import okhttp3.Headers
import org.json.JSONObject
import java.lang.Exception
import java.net.ConnectException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import javax.inject.Inject

class PomacViewModel : ViewModel {
    var pomacComponent: PomacComponent

    var reviewsLiveData: MutableLiveData<NewX> = MutableLiveData()
    var unAuthData: MutableLiveData<Boolean> = MutableLiveData()
    var error_msg: MutableLiveData<String> = MutableLiveData()
    var offlineMode: MutableLiveData<Boolean> = MutableLiveData()

    @Inject
    lateinit var pomacRepository: PomacRepository

    @Inject
    constructor() {
        pomacComponent = DaggerPomacComponent.create()
        pomacComponent.inject(this)
    }

    suspend fun getMovieReviews(movie:String) {
        viewModelScope.launch {
            try {
                var reviewsData = pomacRepository.getMovieReview(movie = movie)
                when (reviewsData.code()) {
                    200 -> {
                        reviewsLiveData.postValue(reviewsData.body())
                    }

                    401 -> {
                        unAuthData.postValue(true)
                    }

                    else -> {
                        val errorJsonString = reviewsData.errorBody()?.string()
                        val json: JSONObject = JSONObject(errorJsonString)
                        val msg = json.getString("message")
                        error_msg.postValue(msg)

                    }

                }
            } catch (e: Exception) {
                if (e is UnknownHostException || e is TimeoutException || e is ConnectException) {
                    offlineMode.postValue(true)
                } else {
                    error_msg.postValue(e.message)
                }
            }
        }
    }

}
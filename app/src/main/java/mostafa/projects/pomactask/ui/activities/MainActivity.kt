package mostafa.projects.pomactask.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mostafa.projects.pomactask.R
import mostafa.projects.pomactask.data.model.Result
import mostafa.projects.pomactask.ui.adapter.ReviewsAdapter
import mostafa.projects.pomactask.viewModels.PomacViewModel


class MainActivity : BaseActivity(), ReviewsAdapter.ReviewsController {

    lateinit var movie_reviews_recycler: RecyclerView
    lateinit var movie_reviews_shimmer_lyt: ShimmerFrameLayout

    val pomacViewModel: PomacViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        loadReviews(movie = "godfather")
    }

    fun initViews() {
        movie_reviews_shimmer_lyt = findViewById(R.id.movie_reviews_shimmer_lyt)
        movie_reviews_recycler = findViewById(R.id.movie_reviews_recycler)
        movie_reviews_recycler.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

    }

    fun showloading() {
        movie_reviews_shimmer_lyt.visibility = View.VISIBLE
        movie_reviews_shimmer_lyt.startLayoutAnimation()
        movie_reviews_shimmer_lyt.startShimmer()
    }

    fun hideloading() {
        movie_reviews_shimmer_lyt.visibility = View.GONE
        movie_reviews_shimmer_lyt.stopShimmer()
    }

    fun ObserveReviews() {
        pomacViewModel.reviewsLiveData.observe(this, Observer {
            hideloading()
            var adapter =
                ReviewsAdapter(ctx = this, reviews = it.results!!, reviewsController = this)
            movie_reviews_recycler.adapter = adapter
        })
        pomacViewModel.error_msg.observe(this, Observer {
            showtoast(it)
        })
        pomacViewModel.offlineMode.observe(this, Observer {
            showtoast(getString(R.string.retry_error))
        })
    }

    override fun onBackPressed() {

    }

    fun loadReviews(movie: String) {
        showloading()
        CoroutineScope(Dispatchers.IO).launch {
            pomacViewModel.getMovieReviews(movie = movie)
            withContext(Dispatchers.Main) {
                ObserveReviews()
            }
        }
    }



    override fun getDetails(result: Result) {
        openActivity<ReviewDetails> {
            this.putExtra("review_result",result)
        }
    }
}
package mostafa.projects.pomactask.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.TextView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import mostafa.projects.pomactask.R
import mostafa.projects.pomactask.data.model.Result

class ReviewDetails : BaseActivity() {

    lateinit var reviewData:Result

    lateinit var movie_img:CircleImageView
    lateinit var movie_title_value_txt:TextView
    lateinit var movie_posted_by_value_txt:TextView
    lateinit var movie_rating_value_txt:TextView
    lateinit var movie_summary_value_txt:TextView
    lateinit var movie_date_value_txt:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_details)
        reviewData = intent.getParcelableExtra( "review_result")!!
        initViews()
        setData()

    }

    fun setData() {
        if(reviewData.multimedia != null){
            Glide.with(this).load(reviewData.multimedia?.src).fitCenter().placeholder(R.drawable.godfather)
                .into(movie_img)
        }
        movie_title_value_txt.setText(reviewData.display_title.replaceFirstChar { it.uppercase() })
        movie_posted_by_value_txt.setText(reviewData.byline.replaceFirstChar { it.uppercase() })
        movie_rating_value_txt.setText(reviewData.mpaa_rating)
        movie_summary_value_txt.setText(reviewData.summary_short)
        movie_date_value_txt.setText(reviewData.publication_date)

    }

    fun initViews(){
        movie_img = findViewById(R.id.movie_img)
        movie_title_value_txt = findViewById(R.id.movie_title_value_txt)
        movie_posted_by_value_txt = findViewById(R.id.movie_posted_by_value_txt)
        movie_rating_value_txt = findViewById(R.id.movie_rating_value_txt)
        movie_summary_value_txt = findViewById(R.id.movie_summary_value_txt)
        movie_date_value_txt = findViewById(R.id.movie_date_value_txt)
    }


    override fun onBackPressed() {
        openActivity<MainActivity>()
    }




}
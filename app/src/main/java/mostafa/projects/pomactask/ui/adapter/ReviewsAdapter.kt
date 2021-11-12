package mostafa.projects.pomactask.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import mostafa.projects.pomactask.R
import mostafa.projects.pomactask.data.model.NewX
import mostafa.projects.pomactask.data.model.Result
import java.util.*
import kotlin.collections.ArrayList

class ReviewsAdapter(
    var ctx: Context,
    var reviews: ArrayList<Result>,
    reviewsController: ReviewsController
) :
    RecyclerView.Adapter<ReviewsAdapter.HomeServiceHolder>() {

    var _reviewsController: ReviewsController

    init {
        _reviewsController = reviewsController
    }

    class HomeServiceHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var movie_avatar_img =
            itemView.findViewById<CircleImageView>(R.id.movie_avatar_img)
        var movie_title_txt = itemView.findViewById<TextView>(R.id.movie_title_txt)
        var movie_published_by_value_txt = itemView.findViewById<TextView>(R.id.movie_published_by_value_txt)
        var movie_date_value_txt = itemView.findViewById<TextView>(R.id.movie_date_value_txt)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeServiceHolder {
        return HomeServiceHolder(
            LayoutInflater.from(ctx).inflate(
                R.layout.review_rate,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: HomeServiceHolder, position: Int) {
        var review = reviews.get(position)
        if(review.multimedia != null){
            Glide.with(ctx).load(review.multimedia?.src).fitCenter().placeholder(R.drawable.godfather)
                .into(holder.movie_avatar_img)
        }
        holder.movie_title_txt.setText(review.display_title.replaceFirstChar { it.uppercase() })
        holder.movie_published_by_value_txt.setText(review.byline.replaceFirstChar { it.uppercase() })
        holder.movie_date_value_txt.setText(review.publication_date)

        holder.itemView.setOnClickListener(object :View.OnClickListener{
            override fun onClick(p0: View?) {
                _reviewsController.getDetails(result = review)
            }

        })
    }


    interface ReviewsController{
        fun getDetails(result: Result)
    }



    override fun getItemCount(): Int {
        return reviews.size
    }
}
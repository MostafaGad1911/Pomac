package mostafa.projects.pomactask.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import mostafa.projects.pomactask.ApiInterface
import mostafa.projects.pomactask.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

@Module
class PomacModule {
    lateinit var apiInterface: ApiInterface
    lateinit var retrofit: Retrofit



    @Provides
    fun getService(): ApiInterface {
        apiInterface = getPomacClient().create(ApiInterface::class.java)
        return apiInterface
    }

    @Provides
    fun getPomacClient(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC
        logging.level = HttpLoggingInterceptor.Level.HEADERS
        logging.level = HttpLoggingInterceptor.Level.BODY


        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()
        val gson: Gson = GsonBuilder()
            .setLenient()
            .create()
        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())  // https://stackoverflow.com/a/43588016/7698605
            .client(client)
            .build()
        return retrofit
    }
}
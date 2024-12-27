package co.kr.hoyaho.data.di

import co.kr.hoyaho.data.service.SubwayService
import co.kr.hoyaho.subway.data.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {
    private const val TIME_OUT_COUNT: Long = 10

    @Provides
    @Singleton
    fun provideSubwayService(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
    ): SubwayService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_API_URL)
            .addConverterFactory(converterFactory)
            .client(okHttpClient).build()
            .create(SubwayService::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        @SubwayServiceInterceptor subwayInterceptor: Interceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(TIME_OUT_COUNT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT_COUNT, TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor)
        .addInterceptor(subwayInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideConverterFactory(
        json: Json,
    ): Converter.Factory = json.asConverterFactory("application/json".toMediaType())

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level =
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
    }

    @Provides
    @Singleton
    @SubwayServiceInterceptor
    fun provideSubwayServiceInterceptor(): Interceptor = Interceptor { chain ->
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url

        val newUrl = originalUrl.newBuilder()
            .encodedPath(
                // format: BASE_URL/{API_KEY}/json
                buildString {
                    append("/")
                    append(BuildConfig.API_KEY)
                    append("/")
                    append("json")
                    append(originalUrl.encodedPath)
                },
            )
            .build()

        val newRequest = originalRequest.newBuilder()
            .url(newUrl)
            .build()

        return@Interceptor chain.proceed(newRequest)
    }
}

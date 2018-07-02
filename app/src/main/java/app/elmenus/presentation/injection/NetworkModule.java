package app.elmenus.presentation.injection;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import javax.inject.Singleton;

import app.elmenus.BuildConfig;
import app.elmenus.data.api.headers.NetworkInterceptor;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mohamed Fareed on 30/6/2018.
 * This class includes all network components provides methods
 */

@Module
public class NetworkModule {
    private String mBaseUrl;

    public NetworkModule(String mBaseUrl) {
        this.mBaseUrl = mBaseUrl;
    }

    @Provides
    @Singleton
    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return interceptor;
    }

    @Provides
    @Singleton
    public NetworkInterceptor provideNetworkInterceptor() {
        return NetworkInterceptor.getInstance();
    }

    @Provides
    @Singleton
    public OkHttpClient.Builder provideOkHttpClientBuilder(HttpLoggingInterceptor interceptor,
                                                           NetworkInterceptor networkInterceptor) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.addInterceptor(networkInterceptor);

        if (BuildConfig.DEBUG) clientBuilder.addInterceptor(interceptor);

        return clientBuilder;
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(OkHttpClient.Builder clientBuilder) {
        return clientBuilder.build();
    }

    @Provides
    @Singleton
    public GsonConverterFactory provideGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient client, GsonConverterFactory gsonConverterFactory) {
        return new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .client(client)
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @Provides
    public Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}

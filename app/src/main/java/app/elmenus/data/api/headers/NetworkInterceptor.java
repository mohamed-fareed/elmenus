package app.elmenus.data.api.headers;

import android.support.annotation.NonNull;

import java.io.IOException;

import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Used to add any headers in api requests like platform or content-type, etc...
 * */
@Singleton
public class NetworkInterceptor implements Interceptor {
    private static NetworkInterceptor INSTANCE = null;

    public static NetworkInterceptor getInstance() {
        if (INSTANCE == null)
            return new NetworkInterceptor();

        return INSTANCE;
    }

    private NetworkInterceptor() {
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        request = request.newBuilder().addHeader("Content-Type", "application/json").build();

        return chain.proceed(request);
    }
}

package app.elmenus.presentation.injection;

import android.content.Context;

import app.elmenus.data.api.ItemsApi;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class AppModule {
    private Context mContext;

    public AppModule(Context context) {
        mContext = context;
    }

    @Provides
    Context provideContext() {
        return mContext;
    }

    @Provides
    ItemsApi provideItemsApi(Retrofit retrofit) {
        return retrofit.create(ItemsApi.class);
    }
}

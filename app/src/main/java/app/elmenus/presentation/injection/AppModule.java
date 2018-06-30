package app.elmenus.presentation.injection;

import android.content.Context;

import app.elmenus.data.api.ItemsApi;
import app.elmenus.data.db.AppDatabase;
import app.elmenus.data.repository.ItemsRepository;
import app.elmenus.data.repository.local.ItemsLocalDataSource;
import app.elmenus.data.repository.remote.ItemsRemoteDataSource;
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

    @Provides
    AppDatabase provideAppDatabase(Context context) {
        return AppDatabase.getAppDatabase(context);
    }

    @Provides
    ItemsRemoteDataSource provideItemsRemoteDataSource(ItemsApi itemsApi) {
        return ItemsRemoteDataSource.getInstance(itemsApi);
    }

    @Provides
    ItemsLocalDataSource provideItemsLocalDataSource(AppDatabase appDatabase) {
        return ItemsLocalDataSource.getInstance(appDatabase);
    }

    @Provides
    ItemsRepository provideItemsRepository(ItemsRemoteDataSource itemsRemoteDataSource,
                                           ItemsLocalDataSource itemsLocalDataSource) {
        return ItemsRepository.getInstance(itemsRemoteDataSource, itemsLocalDataSource);
    }
}

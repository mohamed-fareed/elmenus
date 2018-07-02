package app.elmenus.presentation.injection;

import android.content.Context;

import app.elmenus.data.api.ItemsApi;
import app.elmenus.data.db.AppDatabase;
import app.elmenus.data.repository.ItemsRepository;
import app.elmenus.data.repository.local.ItemsLocalDataSource;
import app.elmenus.data.repository.remote.ItemsRemoteDataSource;
import app.elmenus.domain.base.UseCaseHandler;
import app.elmenus.domain.usecases.GetItem;
import app.elmenus.domain.usecases.GetItems;
import app.elmenus.presentation.screens.itemList.ItemListContract;
import app.elmenus.presentation.screens.itemList.ItemListPresenter;
import app.elmenus.presentation.screens.singleItem.SingleItemContract;
import app.elmenus.presentation.screens.singleItem.SingleItemPresenter;
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

    @Provides
    GetItems provideGetItems(ItemsRepository itemsRepository) {
        return new GetItems(itemsRepository);
    }

    @Provides
    UseCaseHandler provideUseCaseHandler() {
        return UseCaseHandler.getInstance();
    }

    @Provides
    ItemListContract.Presenter provideItemListPresenter(UseCaseHandler useCaseHandler,
                                                        GetItems getItems) {
        return new ItemListPresenter(useCaseHandler, getItems);
    }

    @Provides
    GetItem provideGetItem(ItemsRepository itemsRepository) {
        return new GetItem(itemsRepository);
    }

    @Provides
    SingleItemContract.Presenter provideSingleItemPresenter(UseCaseHandler useCaseHandler,
                                                            GetItem getItem) {
        return new SingleItemPresenter(useCaseHandler, getItem);
    }
}

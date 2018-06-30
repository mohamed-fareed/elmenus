package app.elmenus.data.repository.remote;

import app.elmenus.data.api.ItemsApi;
import app.elmenus.data.repository.ItemsDataSource;

public class ItemsRemoteDataSource implements ItemsDataSource {
    private static ItemsRemoteDataSource INSTANCE = null;

    private ItemsApi itemsApi;

    private ItemsRemoteDataSource(ItemsApi itemsApi) {
        this.itemsApi = itemsApi;
    }

    public static ItemsRemoteDataSource getInstance(ItemsApi itemsApi) {
        if (INSTANCE == null)
            return new ItemsRemoteDataSource(itemsApi);

        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}

package app.elmenus.data.repository;

import java.util.List;

import app.elmenus.data.api.callbacks.BaseCallbackWithList;
import app.elmenus.data.api.callbacks.BaseCallbackWithObject;
import app.elmenus.data.models.Item;

public class ItemsRepository implements ItemsDataSource {
    private static ItemsRepository INSTANCE = null;

    private ItemsDataSource itemsRemoteDataSource;
    private ItemsDataSource itemsLocalDataSource;

    private ItemsRepository(ItemsDataSource itemsRemoteDataSource, ItemsDataSource itemsLocalDataSource) {
        this.itemsRemoteDataSource = itemsRemoteDataSource;
        this.itemsLocalDataSource = itemsLocalDataSource;
    }

    public static ItemsRepository getInstance(ItemsDataSource itemsRemoteDataSource, ItemsDataSource itemsLocalDataSource) {
        if (INSTANCE == null)
            return new ItemsRepository(itemsRemoteDataSource, itemsLocalDataSource);

        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getItems(final int page, final BaseCallbackWithList<Item> callback) {
        itemsLocalDataSource.getItems(page, new BaseCallbackWithList<Item>() {
            @Override
            public void success(List<Item> ListOfData) {
                callback.success(ListOfData);
            }

            @Override
            public void error() {
                getItemsFromRemoteDataSource(page, callback);
            }
        });
    }

    @Override
    public void getItem(final long itemId, final BaseCallbackWithObject<Item> callback) {
        itemsLocalDataSource.getItem(itemId, new BaseCallbackWithObject<Item>() {
            @Override
            public void success(Item data) {
                callback.success(data);
            }

            @Override
            public void error() {
                getItemFromRemoteDataSource(itemId, callback);
            }
        });
    }

    private void getItemFromRemoteDataSource(long itemId, BaseCallbackWithObject<Item> callback) {
        itemsRemoteDataSource.getItem(itemId, callback);
    }

    private void getItemsFromRemoteDataSource(int page, final BaseCallbackWithList<Item> callback) {
        itemsRemoteDataSource.getItems(page, callback);
    }
}

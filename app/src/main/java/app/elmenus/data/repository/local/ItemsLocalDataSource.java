package app.elmenus.data.repository.local;

import java.util.ArrayList;
import java.util.List;

import app.elmenus.data.api.callbacks.BaseCallbackWithList;
import app.elmenus.data.api.callbacks.BaseCallbackWithObject;
import app.elmenus.data.db.AppDatabase;
import app.elmenus.data.models.Item;
import app.elmenus.data.repository.ItemsDataSource;

public class ItemsLocalDataSource implements ItemsDataSource {
    private static ItemsLocalDataSource INSTANCE = null;

    private AppDatabase appDatabase;

    public static ItemsLocalDataSource getInstance(AppDatabase appDatabase) {
        if (INSTANCE == null)
            return new ItemsLocalDataSource(appDatabase);

        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    private ItemsLocalDataSource(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    @Override
    public void getItems(int page, BaseCallbackWithList<Item> callback) {
        List<Item> itemList = appDatabase.itemDao().getAll();

        if (itemList == null || itemList.isEmpty()) {
            callback.error();
            return;
        }

        callback.success(itemList);
    }

    @Override
    public void getItem(long itemId, BaseCallbackWithObject<Item> callback) {
        Item item = appDatabase.itemDao().findByKey(itemId);

        if (item != null) callback.success(item);
        else callback.error();
    }

    @Override
    public void saveItems(List<Item> items) {
        appDatabase.itemDao().insertAll(getNewItems(items));
    }

    /**
     * This method should get all saved items
     * compare with new items, get the new non-duplicated ones to add them
     */
    private List<Item> getNewItems(List<Item> items) {
        List<Item> cachedItems = appDatabase.itemDao().getAll();
        List<Item> newItems = new ArrayList<>();

        for (Item item : items) {
            if (!cachedItems.contains(item))
                newItems.add(item);
        }

        return newItems;
    }
}

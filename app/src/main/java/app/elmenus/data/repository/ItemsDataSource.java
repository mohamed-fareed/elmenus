package app.elmenus.data.repository;

import java.util.List;

import app.elmenus.data.api.callbacks.BaseCallbackWithList;
import app.elmenus.data.api.callbacks.BaseCallbackWithObject;
import app.elmenus.data.models.Item;

public interface ItemsDataSource {
    void getItems(int page, BaseCallbackWithList<Item> callback);

    void getItem(long itemId, BaseCallbackWithObject<Item> callback);

    void saveItems(List<Item> items);
}

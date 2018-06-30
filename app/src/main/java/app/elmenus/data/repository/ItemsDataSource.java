package app.elmenus.data.repository;

import app.elmenus.data.api.callbacks.BaseCallbackWithList;
import app.elmenus.data.models.Item;

public interface ItemsDataSource {
    void getItems(int page, BaseCallbackWithList<Item> callback);
}

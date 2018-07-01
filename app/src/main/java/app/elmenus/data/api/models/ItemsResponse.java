package app.elmenus.data.api.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import app.elmenus.data.models.Item;

public class ItemsResponse {
    @SerializedName("items")
    private List<Item> items = new ArrayList<>();

    public List<Item> getItems() {
        return items;
    }
}

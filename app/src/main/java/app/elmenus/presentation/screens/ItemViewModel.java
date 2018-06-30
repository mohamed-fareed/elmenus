package app.elmenus.presentation.screens;

import app.elmenus.data.models.Item;

public class ItemViewModel {
    private long id;
    private String name, photoUrl, description;

    public ItemViewModel(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.photoUrl = item.getPhotoUrl();
        this.description = item.getDescription();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

package app.elmenus.data.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * This represents the items coming from the apis and this list is saved in DB
 * so it have empty constructor and column-info annotation
 */
@Entity
@Parcel
public class Item {
    @SerializedName("id")
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "item_id")
    private long id;

    @SerializedName("name")
    @ColumnInfo(name = "item_name")
    private String name;

    @SerializedName("photoUrl")
    @ColumnInfo(name = "item_photo_url")
    private String photoUrl;

    @SerializedName("description")
    @ColumnInfo(name = "item_description")
    private String description;

    public Item() {
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
}

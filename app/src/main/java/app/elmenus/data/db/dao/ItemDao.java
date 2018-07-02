package app.elmenus.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import app.elmenus.data.models.Item;

@Dao
public interface ItemDao {
    @Query("SELECT * FROM item")
    List<Item> getAll();

    @Query("SELECT * FROM item WHERE `item_id` LIKE :id LIMIT 1")
    Item findByKey(long id);

    @Insert
    void insertAll(List<Item> items);

    @Query("DELETE FROM item")
    void deleteAll();
}

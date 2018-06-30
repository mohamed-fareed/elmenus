package app.elmenus.data.repository.local;

import app.elmenus.data.db.AppDatabase;
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
}

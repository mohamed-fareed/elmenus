package app.elmenus.data.repository;

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
}

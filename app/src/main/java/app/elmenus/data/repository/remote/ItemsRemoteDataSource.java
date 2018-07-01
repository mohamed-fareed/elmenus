package app.elmenus.data.repository.remote;

import java.util.List;
import java.util.Random;

import app.elmenus.data.api.ItemsApi;
import app.elmenus.data.api.callbacks.BaseCallbackWithList;
import app.elmenus.data.api.models.ItemsResponse;
import app.elmenus.data.models.Item;
import app.elmenus.data.repository.ItemsDataSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemsRemoteDataSource implements ItemsDataSource {
    private static ItemsRemoteDataSource INSTANCE = null;

    private ItemsApi itemsApi;

    private ItemsRemoteDataSource(ItemsApi itemsApi) {
        this.itemsApi = itemsApi;
    }

    public static ItemsRemoteDataSource getInstance(ItemsApi itemsApi) {
        if (INSTANCE == null)
            return new ItemsRemoteDataSource(itemsApi);

        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getItems(final int page, final BaseCallbackWithList<Item> callback) {
        itemsApi.getItems(page).enqueue(new Callback<ItemsResponse>() {
            @Override
            public void onResponse(Call<ItemsResponse> call, Response<ItemsResponse> response) {
                if (response.isSuccessful())
                    callback.success(interceptItemIds(page, response.body().getItems()));
                else callback.error();
            }

            @Override
            public void onFailure(Call<ItemsResponse> call, Throwable t) {
                callback.error();
            }
        });
    }

    /**
     * items have the same ids whatever the page is
     * so we need to intercept this and add page factor
     * in practical app these ids should be different
     */
    private List<Item> interceptItemIds(int page, List<Item> items) {
        for (Item item : items) {
            Random rand = new Random();
            int anyRandomNum = rand.nextInt(1000);
            item.setId(item.getId() + page + anyRandomNum);
        }
        return items;
    }
}

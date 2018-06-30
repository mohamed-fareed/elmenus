package app.elmenus.data.repository.remote;

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
    public void getItems(int page, final BaseCallbackWithList<Item> callback) {
        itemsApi.getItems(page).enqueue(new Callback<ItemsResponse>() {
            @Override
            public void onResponse(Call<ItemsResponse> call, Response<ItemsResponse> response) {
                if (response.isSuccessful()) callback.success(response.body().getItems());
                else callback.error();
            }

            @Override
            public void onFailure(Call<ItemsResponse> call, Throwable t) {
                callback.error();
            }
        });
    }
}

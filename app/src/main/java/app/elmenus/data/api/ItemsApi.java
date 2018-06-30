package app.elmenus.data.api;

import app.elmenus.data.api.models.ItemsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ItemsApi {
    @GET("items/{page}")
    Call<ItemsResponse> getItems(@Path("page") int page);
}

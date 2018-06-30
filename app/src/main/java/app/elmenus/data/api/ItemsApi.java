package app.elmenus.data.api;

import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ItemsApi {
    @GET("items/{id}")
    void getItems(@Path("id") int id);
}

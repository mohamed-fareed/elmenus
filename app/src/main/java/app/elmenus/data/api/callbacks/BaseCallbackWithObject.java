package app.elmenus.data.api.callbacks;

public interface BaseCallbackWithObject<T> {
    void success(T data);

    void error();
}

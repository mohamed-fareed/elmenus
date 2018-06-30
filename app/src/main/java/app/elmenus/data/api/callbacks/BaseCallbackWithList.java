package app.elmenus.data.api.callbacks;

import java.util.List;

public interface BaseCallbackWithList<T> {
    void success(List<T> ListOfData);

    void error();
}

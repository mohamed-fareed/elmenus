package app.elmenus.presentation.screens.singleItem;

import app.elmenus.presentation.base.BaseContract;

public interface SingleItemContract {
    interface View extends BaseContract.View {
        void initializeView(String itemName, String itemImage, String itemDescription);
    }

    interface Presenter extends BaseContract.Presenter<BaseContract.View> {
        void start(long itemId);
    }
}

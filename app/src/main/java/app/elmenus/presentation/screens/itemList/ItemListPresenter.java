package app.elmenus.presentation.screens.itemList;

import app.elmenus.presentation.base.BaseContract;
import app.elmenus.presentation.base.BasePresenter;

public class ItemListPresenter extends BasePresenter implements ItemListContract.Presenter {
    private ItemListContract.View mView;

    @Override
    public void registerView(BaseContract.View view) {
        super.registerView(view);
        mView = (ItemListContract.View) view;
    }
}

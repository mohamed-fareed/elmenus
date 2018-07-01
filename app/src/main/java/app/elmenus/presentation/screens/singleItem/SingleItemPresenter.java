package app.elmenus.presentation.screens.singleItem;

import app.elmenus.presentation.base.BaseContract;
import app.elmenus.presentation.base.BasePresenter;

public class SingleItemPresenter extends BasePresenter implements SingleItemContract.Presenter {
    private SingleItemContract.View mView;

    @Override
    public void registerView(BaseContract.View view) {
        super.registerView(view);
        mView = (SingleItemContract.View) view;
    }
}

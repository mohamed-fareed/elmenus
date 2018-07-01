package app.elmenus.presentation.screens.singleItem;

import app.elmenus.data.models.Item;
import app.elmenus.domain.base.UseCase;
import app.elmenus.domain.base.UseCaseHandler;
import app.elmenus.domain.usecases.GetItem;
import app.elmenus.presentation.base.BaseContract;
import app.elmenus.presentation.base.BasePresenter;

public class SingleItemPresenter extends BasePresenter implements SingleItemContract.Presenter {
    private SingleItemContract.View mView;

    private UseCaseHandler useCaseHandler;
    private GetItem getItem;

    public SingleItemPresenter(UseCaseHandler useCaseHandler, GetItem getItem) {
        this.useCaseHandler = useCaseHandler;
        this.getItem = getItem;
    }

    @Override
    public void registerView(BaseContract.View view) {
        super.registerView(view);
        mView = (SingleItemContract.View) view;
    }

    @Override
    public void start(long itemId) {
        mView.showProgress();

        useCaseHandler.execute(getItem, new GetItem.RequestValues(itemId),
                new UseCase.UseCaseCallback<GetItem.ResponseValue>() {
                    @Override
                    public void onSuccess(GetItem.ResponseValue response) {
                        mView.hideProgress();

                        Item item = response.getItem();
                        mView.initializeView(item.getName(), item.getPhotoUrl(),
                                item.getDescription());
                    }

                    @Override
                    public void onError() {
                        mView.hideProgress();
                        mView.showGeneralError();
                    }
                });
    }
}

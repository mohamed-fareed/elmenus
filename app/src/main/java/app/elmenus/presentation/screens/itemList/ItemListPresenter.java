package app.elmenus.presentation.screens.itemList;

import java.util.ArrayList;
import java.util.List;

import app.elmenus.data.models.Item;
import app.elmenus.domain.base.UseCase;
import app.elmenus.domain.base.UseCaseHandler;
import app.elmenus.domain.usecases.GetItems;
import app.elmenus.presentation.base.BaseContract;
import app.elmenus.presentation.base.BasePresenter;

public class ItemListPresenter extends BasePresenter implements ItemListContract.Presenter {
    private ItemListContract.View mView;

    private UseCaseHandler useCaseHandler;
    private GetItems getItems;

    private List<Item> itemList = new ArrayList<>();

    public ItemListPresenter(UseCaseHandler useCaseHandler, GetItems getItems) {
        this.useCaseHandler = useCaseHandler;
        this.getItems = getItems;
    }

    @Override
    public void registerView(BaseContract.View view) {
        super.registerView(view);
        mView = (ItemListContract.View) view;
    }

    @Override
    public void start() {
        mView.initializeView();
        mView.initializeListAdapter();

        getItems(0, false);
    }

    @Override
    public void onLoadMoreTriggered(int page) {
        getItems(page, false);
    }

    @Override
    public void onRefresh() {
        getItems(0, true);
    }

    @Override
    public void onItemClicked(long itemId) {
        mView.goToSingleItem(itemId);
    }

    @Override
    public void onRetryFromErrorLoading(int currentPage) {
        getItems(currentPage, false);
    }

    private void getItems(final int page, final boolean swipeToRefresh) {
        if (swipeToRefresh) mView.showSwipeToRefreshLoading();
        else mView.showProgress();

        if (page != 0) mView.showProgress();

        useCaseHandler.execute(getItems, new GetItems.RequestValues(page * 10),
                new UseCase.UseCaseCallback<GetItems.ResponseValue>() {
                    @Override
                    public void onSuccess(GetItems.ResponseValue response) {
                        if (swipeToRefresh) mView.hideSwipeToRefreshLoading();
                        else mView.hideProgress();

                        if (swipeToRefresh) {
                            itemList.clear();
                            mView.resetList();
                            mView.initializeListAdapter();
                        }

                        if (page != 0) mView.hideProgress();

                        addAllItems(response.getItems());

                        // to make the scroll listener aware of this change
                        mView.incrementAdapterPage();
                        mView.addItems(itemList);
                        mView.setNotLoading();
                    }

                    @Override
                    public void onError() {
                        mView.hideProgress();
                        mView.hideSwipeToRefreshLoading();
                        mView.showListErrorLoading();
                        mView.setNotLoading();
                    }
                });

    }

    private void addAllItems(List<Item> items) {
        for (Item item : items)
            if (!itemList.contains(item))
                itemList.add(item);
    }
}

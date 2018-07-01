package app.elmenus.presentation.screens.itemList;

import java.util.List;

import app.elmenus.data.models.Item;
import app.elmenus.presentation.base.BaseContract;

public interface ItemListContract {
    interface View extends BaseContract.View {
        void initializeView();

        void initializeListAdapter();

        void showItemListLoading();

        void hideItemListLoading();

        void showListErrorLoading();

        void hideListErrorLoading();

        void showSwipeToRefreshLoading();

        void hideSwipeToRefreshLoading();

        void addItems(List<Item> items);

        void setItems(List<Item> items);

        void resetList();

        void setNotLoading();

        void incrementAdapterPage();

        void goToSingleItem(long itemId);
    }

    interface Presenter extends BaseContract.Presenter<BaseContract.View> {
        void start();

        void onLoadMoreTriggered(int page);

        void onRefresh();

        void onItemClicked(long itemId);

        void onRetryFromErrorLoading(int currentPage);

        void onCommentBodyChanged(CharSequence commentBody);
    }
}

package app.elmenus.presentation.screens.itemList;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.airbnb.epoxy.EpoxyRecyclerView;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;

import java.util.List;

import javax.inject.Inject;

import app.elmenus.ElMenusApp;
import app.elmenus.R;
import app.elmenus.data.models.Item;
import app.elmenus.presentation.adapters.EndlessRecyclerViewScrollListener;
import app.elmenus.presentation.adapters.ItemsAdapter;
import app.elmenus.presentation.base.BaseContract;
import app.elmenus.presentation.base.BaseFragment;
import app.elmenus.presentation.utils.NavigationManager;
import app.elmenus.presentation.views.items.ItemView;
import butterknife.BindColor;
import butterknife.BindView;

@FragmentWithArgs
public class ItemListFragment extends BaseFragment implements ItemListContract.View, SwipeRefreshLayout.OnRefreshListener, ItemView.Listener, View.OnClickListener {
    @Inject
    ItemListContract.Presenter mPresenter;

    @BindView(R.id.items_rv)
    EpoxyRecyclerView itemsRV;
    @BindView(R.id.swipe_to_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindColor(R.color.colorAccent)
    int colorAccent;

    private ItemsAdapter itemsAdapter;

    private EndlessRecyclerViewScrollListener scrollListener;
    private boolean loading = true;

    public ItemListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeToolbar(true, getString(R.string.app_name), false);
        mPresenter.start();
    }

    @Override
    public BaseContract.Presenter<BaseContract.View> getPresenter() {
        return mPresenter;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_item_list;
    }

    @Override
    protected void injectDependencies() {
        ((ElMenusApp) getActivity().getApplication()).getAppComponent().inject(this);
    }

    @Override
    public void showProgress() {
        itemsAdapter.setLoading(true);
    }

    @Override
    public void hideProgress() {
        itemsAdapter.setLoading(false);
    }

    @Override
    public void initializeView() {
        scrollListener = new EndlessRecyclerViewScrollListener((LinearLayoutManager) itemsRV.getLayoutManager()) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (!loading) {
                    loading = true;
                    mPresenter.onLoadMoreTriggered(page);
                }
            }
        };
        itemsRV.addOnScrollListener(scrollListener);

        // swipe to refresh layout
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(colorAccent);
    }

    @Override
    public void initializeListAdapter() {
        itemsAdapter = new ItemsAdapter();
        itemsAdapter.setItemListener(this);
        itemsAdapter.setErrorViewListener(this);

        itemsRV.setController(itemsAdapter);
    }

    @Override
    public void showListErrorLoading() {
        itemsAdapter.setErrorLoading(true);
    }

    @Override
    public void hideListErrorLoading() {
        itemsAdapter.setErrorLoading(false);
    }

    @Override
    public void showSwipeToRefreshLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideSwipeToRefreshLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void addItems(List<Item> items) {
        itemsAdapter.addItems(items);
    }

    @Override
    public void setItems(List<Item> items) {
        itemsAdapter.setItems(items);
    }

    @Override
    public void resetList() {
        scrollListener.resetState();
        itemsAdapter.clear();
    }

    @Override
    public void setNotLoading() {
        loading = false;
    }

    @Override
    public void incrementAdapterPage() {
        scrollListener.incrementPage();
    }

    @Override
    public void goToSingleItem(long itemId) {
        NavigationManager.goToSingleItem(this, itemId);
    }

    @Override
    public void onRefresh() {
        mPresenter.onRefresh();
    }

    @Override
    public void onItemClicked(long itemId) {
        mPresenter.onItemClicked(itemId);
    }

    @Override
    public void onClick(View v) {
        mPresenter.onRetryFromErrorLoading(scrollListener.getPage());
    }
}
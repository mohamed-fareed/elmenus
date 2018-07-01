package app.elmenus.presentation.adapters;

import android.view.View;

import com.airbnb.epoxy.AutoModel;
import com.airbnb.epoxy.EpoxyController;

import java.util.ArrayList;
import java.util.List;

import app.elmenus.data.models.Item;
import app.elmenus.presentation.screens.ItemViewModel;
import app.elmenus.presentation.views.ErrorLoadingViewModel_;
import app.elmenus.presentation.views.items.ItemListLoadingViewModel_;
import app.elmenus.presentation.views.items.ItemView;
import app.elmenus.presentation.views.items.ItemViewModel_;

public class ItemsAdapter extends EpoxyController {
    @AutoModel
    ItemListLoadingViewModel_ loadingViewModel_;
    @AutoModel
    ErrorLoadingViewModel_ errorLoadingViewModel_;

    private boolean loading;
    private boolean errorLoading;

    private ItemView.Listener itemListener;
    private View.OnClickListener errorViewListener;

    private List<Item> itemList = new ArrayList<>();

    @Override
    protected void buildModels() {
        for (Item item : itemList) {
            ItemViewModel itemViewModel = new ItemViewModel(item);

            new ItemViewModel_().id(itemViewModel.getId())
                    .item(itemViewModel).listener(itemListener).addTo(this);
        }

        loadingViewModel_.addIf(loading, this);
        errorLoadingViewModel_.listener(errorViewListener).addIf(errorLoading, this);
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
        requestModelBuild();
    }

    public void setErrorLoading(boolean errorLoading) {
        this.errorLoading = errorLoading;
        requestModelBuild();
    }

    public void setItemListener(ItemView.Listener itemListener) {
        this.itemListener = itemListener;
    }

    public void setErrorViewListener(View.OnClickListener errorViewListener) {
        this.errorViewListener = errorViewListener;
    }

    public void addItems(List<Item> newItems) {
        for (Item item : newItems)
            if (!itemList.contains(item))
                itemList.add(item);

        requestModelBuild();
    }

    public void setItems(List<Item> items) {
        this.itemList = items;

        requestModelBuild();
    }

    public void clear() {
        itemList.clear();
        requestModelBuild();
    }
}

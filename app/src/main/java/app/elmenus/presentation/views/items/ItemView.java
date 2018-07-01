package app.elmenus.presentation.views.items;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.epoxy.AfterPropsSet;
import com.airbnb.epoxy.CallbackProp;
import com.airbnb.epoxy.ModelProp;
import com.airbnb.epoxy.ModelView;

import app.elmenus.R;
import app.elmenus.presentation.screens.ItemViewModel;
import app.elmenus.presentation.utils.GlideApp;
import app.elmenus.presentation.views.BaseView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
public class ItemView extends BaseView {

    @BindView(R.id.item_image)
    ImageView itemImage;
    @BindView(R.id.item_name)
    TextView itemName;
    @BindView(R.id.item_description)
    TextView itemDescription;

    private ItemViewModel item;
    private Listener listener;

    public ItemView(Context context) {
        super(context, null);
    }

    public ItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @OnClick(R.id.item_container)
    public void onItemClicked() {
        if (listener != null) listener.onItemClicked(item.getId());
    }

    @CallbackProp
    public void setListener(@Nullable Listener listener) {
        this.listener = listener;
    }

    @ModelProp
    public void setItem(ItemViewModel viewModel) {
        this.item = viewModel;
    }

    @AfterPropsSet
    public void draw() {
        GlideApp.with(getContext())
                .load(item.getPhotoUrl())
                .centerCrop()
                .error(R.color.colorImagePlaceHolder)
                .into(itemImage);

        itemName.setText(item.getName());
        itemDescription.setText(item.getDescription());
    }

    @Override
    public int getViewLayout() {
        return R.layout.item_view_layout;
    }

    @Override
    public void identifyViews(View view) {
        ButterKnife.bind(this, view);
    }

    public interface Listener {
        void onItemClicked(long itemId);
    }
}

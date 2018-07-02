package app.elmenus.presentation.views.items;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.airbnb.epoxy.ModelView;
import com.facebook.shimmer.ShimmerFrameLayout;

import app.elmenus.R;
import app.elmenus.presentation.views.BaseView;
import butterknife.BindView;
import butterknife.ButterKnife;

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
public class ItemListLoadingView extends BaseView {
    @BindView(R.id.items_loading_view_container)
    ShimmerFrameLayout itemsLoadingView;

    public ItemListLoadingView(Context context) {
        super(context, null);
    }

    public ItemListLoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        itemsLoadingView.startShimmer();
    }

    @Override
    public int getViewLayout() {
        return R.layout.item_list_loading_view_layout;
    }

    @Override
    public void identifyViews(View view) {
        ButterKnife.bind(this, view);
    }
}

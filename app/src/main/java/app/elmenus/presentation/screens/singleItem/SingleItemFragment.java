package app.elmenus.presentation.screens.singleItem;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;

import javax.inject.Inject;

import app.elmenus.ElMenusApp;
import app.elmenus.R;
import app.elmenus.presentation.base.BaseContract;
import app.elmenus.presentation.base.BaseFragment;
import app.elmenus.presentation.utils.GlideApp;
import butterknife.BindView;

@FragmentWithArgs
public class SingleItemFragment extends BaseFragment implements SingleItemContract.View {
    @Inject
    SingleItemContract.Presenter mPresenter;

    @BindView(R.id.app_bar_layout)
    View appBarLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.item_image)
    ImageView itemImage;
    @BindView(R.id.item_description)
    TextView itemDescription;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Arg
    long itemId;

    public SingleItemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeToolbar(true, null, true);
        mPresenter.start(itemId);
    }

    @Override
    public BaseContract.Presenter<BaseContract.View> getPresenter() {
        return mPresenter;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_single_item;
    }

    @Override
    protected void injectDependencies() {
        ((ElMenusApp) getActivity().getApplication()).getAppComponent().inject(this);
    }

    @Override
    public void showProgress() {
        appBarLayout.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        appBarLayout.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void initializeView(String itemName, String itemImageUrl, String itemDescriptionTxt) {
        GlideApp.with(getContext())
                .load(itemImageUrl)
                .centerCrop()
                .error(R.color.colorImagePlaceHolder)
                .into(itemImage);

        collapsingToolbar.setTitle(itemName);
        itemDescription.setText(itemDescriptionTxt);
    }
}
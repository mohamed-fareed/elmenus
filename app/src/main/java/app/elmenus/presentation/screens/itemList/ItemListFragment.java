package app.elmenus.presentation.screens.itemList;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;

import javax.inject.Inject;

import app.elmenus.ElMenusApp;
import app.elmenus.R;
import app.elmenus.presentation.base.BaseContract;
import app.elmenus.presentation.base.BaseFragment;

@FragmentWithArgs
public class ItemListFragment extends BaseFragment implements ItemListContract.View {
    @Inject
    ItemListContract.Presenter mPresenter;

    public ItemListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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

    }

    @Override
    public void hideProgress() {

    }
}
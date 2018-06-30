package app.elmenus.presentation.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.fragmentargs.FragmentArgs;

import app.elmenus.ElMenusApp;
import app.elmenus.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Mohamed Farid on 11/3/2017.
 * It holds all common functions for any fragment
 */

public abstract class BaseFragment extends Fragment implements BaseContract.View {
    @Nullable
    @BindView(R.id.toolbar)
    public Toolbar toolbar;
    @Nullable
    @BindView(R.id.fragment_container)
    View fragmentContainer;

    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentArgs.inject(this);
        ((ElMenusApp) getActivity().getApplication()).getAppComponent().inject(this);
    }

    public abstract BaseContract.Presenter<BaseContract.View> getPresenter();

    @LayoutRes
    protected abstract int getLayoutRes();

    protected abstract void injectDependencies();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutRes(), container, false);
        unbinder = ButterKnife.bind(this, view);

        injectDependencies();

        getPresenter().registerView(this);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        getPresenter().unregisterView();
        unbinder.unbind();
    }

    @Override
    public void initializeToolbar(boolean enableToolbar, String screenName, boolean enableHomeButton) {
        if (!enableToolbar) {
            toolbar.setVisibility(View.GONE);
            return;
        }

        toolbar.setTitle(screenName);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(enableHomeButton);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(enableHomeButton);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().onBackPressed();
            }
        });
    }

    @Override
    public void showGeneralError() {
        if (fragmentContainer != null) {
            Snackbar.make(fragmentContainer, getString(R.string.error_general), Snackbar.LENGTH_LONG)
                    .show();
        }
    }

    @Override
    public void exit() {
        getActivity().finish();
    }
}

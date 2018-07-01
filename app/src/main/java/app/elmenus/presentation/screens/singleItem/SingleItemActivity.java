package app.elmenus.presentation.screens.singleItem;

import android.support.v4.app.Fragment;

import app.elmenus.presentation.base.BaseFragmentActivity;

public class SingleItemActivity extends BaseFragmentActivity {
    @Override
    protected Fragment getFragment() {
        return new SingleItemFragmentBuilder().build();
    }
}

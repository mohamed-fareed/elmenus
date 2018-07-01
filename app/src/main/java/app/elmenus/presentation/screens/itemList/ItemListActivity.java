package app.elmenus.presentation.screens.itemList;

import android.support.v4.app.Fragment;

import app.elmenus.presentation.base.BaseFragmentActivity;

public class ItemListActivity extends BaseFragmentActivity {
    @Override
    protected Fragment getFragment() {
        return new ItemListFragmentBuilder().build();
    }
}

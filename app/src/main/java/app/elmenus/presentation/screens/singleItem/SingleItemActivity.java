package app.elmenus.presentation.screens.singleItem;

import android.support.v4.app.Fragment;

import app.elmenus.presentation.base.BaseFragmentActivity;
import app.elmenus.presentation.utils.Constants;

public class SingleItemActivity extends BaseFragmentActivity {
    @Override
    protected Fragment getFragment() {
        long itemId = getIntent().getLongExtra(Constants.ITEM_ID, 0);
        return new SingleItemFragmentBuilder(itemId).build();
    }
}

package app.elmenus.presentation.screens.singleItem;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.transition.Slide;
import android.view.Window;

import app.elmenus.presentation.base.BaseFragmentActivity;
import app.elmenus.presentation.utils.Constants;

public class SingleItemActivity extends BaseFragmentActivity {
    @Override
    protected Fragment getFragment() {
        long itemId = getIntent().getLongExtra(Constants.ITEM_ID, 0);
        return new SingleItemFragmentBuilder(itemId).build();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            getWindow().setExitTransition(new Slide());
        }

        super.onCreate(savedInstanceState);
    }
}

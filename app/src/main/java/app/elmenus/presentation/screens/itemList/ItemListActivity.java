package app.elmenus.presentation.screens.itemList;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.transition.Slide;
import android.view.Window;

import app.elmenus.presentation.base.BaseFragmentActivity;

public class ItemListActivity extends BaseFragmentActivity {
    @Override
    protected Fragment getFragment() {
        return new ItemListFragmentBuilder().build();
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

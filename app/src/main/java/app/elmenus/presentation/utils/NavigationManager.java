package app.elmenus.presentation.utils;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;

import app.elmenus.presentation.screens.singleItem.SingleItemActivity;

public class NavigationManager {
    public static void goToSingleItem(Fragment fragment, long itemId) {
        Intent intent = new Intent(fragment.getActivity(), SingleItemActivity.class);
        intent.putExtra(Constants.ITEM_ID, itemId);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fragment.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(fragment.getActivity()).toBundle());
        } else {
            fragment.startActivity(intent);
        }
    }
}

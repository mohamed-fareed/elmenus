package app.elmenus.presentation.utils;

import android.content.Intent;
import android.support.v4.app.Fragment;

import app.elmenus.presentation.screens.singleItem.SingleItemActivity;

public class NavigationManager {
    public static void goToSingleItem(Fragment fragment, long itemId) {
        Intent intent = new Intent(fragment.getActivity(), SingleItemActivity.class);
        intent.putExtra(Constants.ITEM_ID, itemId);
        fragment.startActivity(intent);
    }
}

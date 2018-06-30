package app.elmenus.presentation.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import app.elmenus.ElMenusApp;

/**
 * All activities should inherit from this class
 * It will have all activities common functions
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ElMenusApp) getApplication()).getAppComponent().inject(this);
    }
}

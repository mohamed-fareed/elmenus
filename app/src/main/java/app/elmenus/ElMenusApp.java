package app.elmenus;

import android.app.Application;

import app.elmenus.presentation.injection.AppComponent;
import app.elmenus.presentation.injection.AppModule;
import app.elmenus.presentation.injection.NetworkModule;

public class ElMenusApp extends Application {
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(getApplicationContext()))
                .networkModule(new NetworkModule(BASE_URL))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}

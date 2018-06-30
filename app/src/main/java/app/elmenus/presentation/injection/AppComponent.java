package app.elmenus.presentation.injection;

import javax.inject.Singleton;

import app.elmenus.presentation.base.BaseActivity;
import app.elmenus.presentation.base.BaseFragment;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {
    void inject(BaseActivity baseActivity);

    void inject(BaseFragment baseFragment);
}

package app.elmenus.presentation.base;

public interface BaseContract {
    interface View {
        void initializeToolbar(boolean enableToolbar, String screenName, boolean enableHomeButton);

        void showProgress();

        void hideProgress();

        void showGeneralError();

        void showSnackbarMessage(String message);

        void exit();
    }

    interface Presenter<T extends BaseContract.View> {
        void registerView(T view);

        void unregisterView();

        T getView();

        void onBackPressed();
    }
}

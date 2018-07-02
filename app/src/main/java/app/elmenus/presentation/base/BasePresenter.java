package app.elmenus.presentation.base;

/**
 * Created by Mohamed Farid on 11/6/2017.
 */

public abstract class BasePresenter implements BaseContract.Presenter<BaseContract.View> {
    private BaseContract.View mView;

    @Override
    public void registerView(BaseContract.View view) {
        mView = view;
    }

    @Override
    public void unregisterView() {
        mView = DUMMY_VIEW;
    }

    @Override
    public BaseContract.View getView() {
        return mView;
    }

    @Override
    public void onBackPressed() {
        mView.exit();
    }

    private static final BaseContract.View DUMMY_VIEW = new BaseContract.View() {
        @Override
        public void initializeToolbar(boolean enableToolbar, String screenName, boolean enableHomeButton) {

        }

        @Override
        public void showProgress() {

        }

        @Override
        public void hideProgress() {

        }

        @Override
        public void showGeneralError() {

        }

        @Override
        public void exit() {

        }
    };
}

package app.elmenus.presentation.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.airbnb.epoxy.CallbackProp;
import com.airbnb.epoxy.ModelView;

import app.elmenus.R;
import butterknife.ButterKnife;
import butterknife.OnClick;

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
public class ErrorLoadingView extends BaseView {
    private OnClickListener listener;

    public ErrorLoadingView(Context context) {
        super(context, null);
    }

    public ErrorLoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @CallbackProp
    public void setListener(@Nullable OnClickListener listener) {
        this.listener = listener;
    }

    @OnClick(R.id.error_view_container)
    public void onRetryClicked(View view) {
        if (listener != null) listener.onClick(view);
    }

    @Override
    public int getViewLayout() {
        return R.layout.error_view_layout;
    }

    @Override
    public void identifyViews(View view) {
        ButterKnife.bind(this, view);
    }
}

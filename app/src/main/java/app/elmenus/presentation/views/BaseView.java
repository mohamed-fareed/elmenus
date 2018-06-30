package app.elmenus.presentation.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public abstract class BaseView extends LinearLayout {
    protected LayoutInflater inflater;

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(getViewLayout(), this, true);

        // setting global layout params for all child views
        LayoutParams layoutParams = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        this.setLayoutParams(layoutParams);

        identifyViews(view);
    }

    public abstract int getViewLayout();

    public abstract void identifyViews(View view);
}

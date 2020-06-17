package studio.zebro.circularrecyclerview;

import android.view.View;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RotateXScaleYViewMode implements ItemViewMode {

    private float mScaleRatio = 0.001f;
    private float mRontationRatio = 0.2f;
    private int last;
    private int first;

    public RotateXScaleYViewMode(){}

    public RotateXScaleYViewMode(float scaleRatio, float rontationRatio) {
        mScaleRatio = scaleRatio;
        mRontationRatio = rontationRatio;
    }

    @Override
    public void applyToView(View v, RecyclerView parent) {
        float halfHeight = v.getHeight() * 0.5f;
        float parentHalfHeight = parent.getHeight() * 0.5f;
        float y = v.getY();
        float rot = parentHalfHeight - halfHeight - y;

        float scale = 1.0f - Math.abs(rot) * mScaleRatio;

        ViewCompat.setScaleX(v, scale);
        ViewCompat.setScaleY(v, scale);

        last = ((LinearLayoutManager) parent.getLayoutManager()).findLastVisibleItemPosition();
        first = ((LinearLayoutManager) parent.getLayoutManager()).findFirstVisibleItemPosition();
    }


    private void tilt(View v, float rot) {
        float degree = rot * (last - first);
        if (degree > 90) degree = 90;
        if (degree < -90) degree = -90;
        ViewCompat.setRotationX(v, degree);
    }
}

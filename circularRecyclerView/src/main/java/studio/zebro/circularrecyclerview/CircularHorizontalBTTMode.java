package studio.zebro.circularrecyclerview;

import android.view.View;

import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

public class CircularHorizontalBTTMode implements ItemViewMode {

    private int mCircleOffset = -50;
    private float mDegToRad = -1.0f / 180.0f * (float) Math.PI;
    private float mScalingRatio = 0.001f;
    private float mTranslationRatio = 0.15f;
    private float mYOffset = 100;
    private boolean mUseRotation = false;

    public CircularHorizontalBTTMode(float yOffset, boolean useRotation) {
        this.mYOffset = yOffset;
        this.mUseRotation = useRotation;
    }


    @Override
    public void applyToView(View v, RecyclerView parent) {
        float halfWidth = v.getWidth() * 0.5f;
        float parentHalfWidth = parent.getWidth() * 0.5f;
        float x = v.getX();
        float rot = parentHalfWidth - halfWidth - x;
        if (mUseRotation) {
            ViewCompat.setPivotY(v, v.getBottom());
            ViewCompat.setPivotX(v, halfWidth);
        }

        ViewCompat.setTranslationY(v, - 1 * (float) (-Math.cos(rot * mTranslationRatio * mDegToRad) + 1) * mCircleOffset + mYOffset);

        float scale = 1.0f - Math.abs(parentHalfWidth - halfWidth - x) * mScalingRatio;
//        ViewCompat.setScaleX(v, scale);
//        ViewCompat.setScaleY(v, scale);
    }
}

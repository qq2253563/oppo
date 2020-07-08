package com.student.patient;

import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

    private int mSpace = 0;

    public SpacesItemDecoration(int space) {
        mSpace = dp2px(space);
    }

    public int dp2px(float dpValue) {
        return (int) (0.5f + dpValue * Resources.getSystem().getDisplayMetrics().density);
    }


    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getAdapter() == null) {
            return;
        }
        int childLayoutPosition = parent.getChildLayoutPosition(view);
        int itemCount = parent.getAdapter().getItemCount();
        if (itemCount < 2) {
            return;
        }
        if (childLayoutPosition == 0) {
            outRect.bottom = mSpace / 2;
        } else if (childLayoutPosition == itemCount - 1) {
            outRect.top = mSpace / 2;
        } else {
            outRect.bottom = mSpace / 2;
            outRect.top = mSpace / 2;
        }
    }
}

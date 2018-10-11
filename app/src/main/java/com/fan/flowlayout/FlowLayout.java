package com.fan.flowlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by huisoucw on 2018/10/10.
 */

public class FlowLayout extends ViewGroup {
    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        for (int i = 0; i < getChildCount(); i++) {
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            int count = getChildCount();
            int parentLeft = getPaddingLeft();
            int parentTop = getPaddingTop();
            int parentRight = r - l - getPaddingRight();
            int widthUsed = parentLeft;
            int heightUsed = parentTop;
            int maxBottom = 0;
            for (int i = 0; i < count; i++) {
                View child = getChildAt(i);
                MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();
                int childTop = heightUsed;
                int childLeft = widthUsed;

                childTop += params.topMargin;
                childLeft += params.leftMargin;

                int childRight = childLeft + child.getMeasuredWidth();
                int childBottom = childTop + child.getMeasuredHeight();

                int curBottom = params.topMargin + child.getMeasuredHeight() + params.bottomMargin;
                if (curBottom > maxBottom) {
                    maxBottom = curBottom;
                }
                boolean isChangeRow = false;
                if (childRight + params.rightMargin > parentRight) {
                    isChangeRow = true;
                }
                if (isChangeRow) {
                    heightUsed += maxBottom;
                    widthUsed = parentLeft;
                    childLeft = widthUsed;
                    childTop = heightUsed;
                    childTop += params.topMargin;
                    childLeft += params.leftMargin;
                    childRight = childLeft + child.getMeasuredWidth();
                    childBottom = childTop + child.getMeasuredHeight();
                }
                child.layout(childLeft, childTop, childRight, childBottom);
                widthUsed += params.leftMargin + child.getMeasuredWidth() + params.rightMargin;
            }
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof MarginLayoutParams;
    }
}

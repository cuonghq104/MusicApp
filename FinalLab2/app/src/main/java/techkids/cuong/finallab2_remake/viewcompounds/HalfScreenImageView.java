package techkids.cuong.finallab2_remake.viewcompounds;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Cuong on 1/9/2017.
 */
public class HalfScreenImageView extends ImageView{


    public HalfScreenImageView(Context context) {
        super(context);
    }

    public HalfScreenImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HalfScreenImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(parentHeight / 2, MeasureSpec.EXACTLY));
    }
}

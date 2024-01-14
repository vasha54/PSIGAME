package cu.innovat.psigplus.ui.component;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class JustifiedTextView extends AppCompatTextView {
    public JustifiedTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public JustifiedTextView(@NonNull Context context) {
        super(context);
    }

    public JustifiedTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        TextPaint textPaint = getPaint();
        String text = getText().toString();
        Layout.Alignment alignment = Layout.Alignment.ALIGN_NORMAL;
        float spacingMultiplier = 1;
        float spacingAdition = 0;
        boolean includePadding = false;
        int width = getMeasuredWidth();

        StaticLayout layout = new StaticLayout(text,textPaint,width,alignment,spacingMultiplier,
                spacingAdition,includePadding);
        int lineCount = layout.getLineCount();
        float height = getHeight();
        for (int i = 0; i < lineCount; i++){
            height+=layout.getLineWidth(i);
        }

        float y =0;
        for (int i = 0; i < lineCount; i++){
            canvas.save();
            canvas.translate(0,y);
            layout.draw(canvas);
            canvas.restore();
            y +=layout.getLineBottom(i);
        }
    }
}

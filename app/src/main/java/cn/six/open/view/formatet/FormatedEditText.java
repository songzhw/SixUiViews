package cn.six.open.view.formatet;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by songzhw on 2015/12/19
 */

//TODO to be continued
public class FormatedEditText extends EditText {
    private int step = 4;
    private String padding = " ";

    public FormatedEditText(Context ctx) {
        this(ctx, null);
    }

    public FormatedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        addTextChangedListener(watcher);
    }

    public FormatedEditText(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs,defStyleAttr);
        addTextChangedListener(watcher);
    }

    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
//            Log.d("szw","onTextChanged() str = "+s+" ; [start,before] = ["+start+" , "+before+"] ; count = "+count);
            if (s == null) {
                return;
            }
            //判断是否是在中间输入/或者是否是一下子粘贴了多位，需要重新计算
            boolean isMiddleOrPasted = start < (s.length() - 1);
            //在末尾输入时，是否需要加入空格
            boolean isNeedSpace = s.length() > 0 && s.length() % (step+1) == 0;
//            Log.d("szw","isMiddele = "+isMiddle + " ; isNeedSpace = "+isNeedSpace);
            if (isMiddleOrPasted || isNeedSpace) {
                String newStr = s.toString();
                newStr = newStr.replace(padding, "");
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < newStr.length(); i += step) {
                    if (i > 0) {
//                        Log.d("szw","sb append a space");
                        sb.append(padding);
                    }
                    if (i + step <= newStr.length()) {
//                        Log.d("szw","sb append(2) "+newStr.substring(i, i + 4));
                        sb.append(newStr.substring(i, i + step));
                    } else {
//                        Log.d("szw","sb append(3) "+newStr.substring(i, newStr.length()));
                        sb.append(newStr.substring(i, newStr.length()));
                    }
                }
                removeTextChangedListener(watcher);
                setText(sb);
                //如果加入的字符个数大于零的话（输入或者粘贴）
                if (count > 1) {
                    setSelection(sb.length());
                } else if (start + count - before < 1) {
                    setSelection(0);
                } else if (sb.substring(start + count - before - 1, start + count - before).equals(padding)) {
                    //如果当前的光标前面的字符是空格的话，则光标自动加一
                    setSelection(start + count - before + 1);
                } else {
                    setSelection(start + count - before);
                }
                addTextChangedListener(watcher);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

}
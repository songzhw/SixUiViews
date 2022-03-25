package ca.six.views.lib.views.edit_text;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

import ca.six.views.lib.R;
import ca.six.views.lib.util.StringUtil;


/**
 * Created by songzhw on 2016-06-28
 */
public class LoginNameView extends RelativeLayout implements View.OnClickListener, View.OnTouchListener, AdapterView.OnItemClickListener, LoginHistoryAdapter.OnDeleteHistoryListener {
    private AutoCompleteTextView etName;
    private ImageView ivDeleteName, ivShowMore;
    private LoginHistoryAdapter adapterHistory;
    private LoginSuffixAdapter adapterSuffix;
    private String theRealAccount = ""; // 如果选择的是一个历史账户，此值为此账户名 ；若是自己输入的， 此值为空


    public LoginNameView(Context context) {
        super(context);
        init(context);
    }

    public LoginNameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    public void init(Context ctx){
        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View parent = inflater.inflate(R.layout.view_login_name, this);
        etName = (AutoCompleteTextView) parent.findViewById(R.id.et_login_name);
        ivDeleteName = (ImageView) parent.findViewById(R.id.iv_login_del_name);
        ivShowMore = (ImageView) parent.findViewById(R.id.iv_login_show_history_accounts);
        ivDeleteName.setOnClickListener(this);
        ivShowMore.setOnClickListener(this);

        ArrayList<String> suffix = new ArrayList<String>();
        suffix.add("@gmail.com"); suffix.add("@outlook.com"); suffix.add("@yahoo.com"); suffix.add("@163.com");
        adapterSuffix = new LoginSuffixAdapter(getContext(), suffix);

        ArrayList<String> historyAccountsList = new ArrayList<String>();
        historyAccountsList.add("boss394@gmail.com"); historyAccountsList.add("vp1287456@ali.com");
        historyAccountsList.add("developer003@mine.com");
        adapterHistory = new LoginHistoryAdapter(getContext(), historyAccountsList, this);

        etName.setThreshold(1);
        etName.setOnTouchListener(this);
        etName.addTextChangedListener(watcherName);
        etName.setDropDownBackgroundDrawable(null);
        etName.setAdapter(adapterHistory);
        etName.setOnItemClickListener(this);

    }

    private TextWatcher watcherName = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            etName.setAdapter(adapterSuffix);
        }

        @Override
        public void afterTextChanged(Editable s) {
            if(s == null){
                return;
            }

            String input = s.toString();
            if(TextUtils.isEmpty(input)){
                ivDeleteName.setVisibility(View.GONE);
            } else {
                ivDeleteName.setVisibility(View.VISIBLE);
            }

        }
    };

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            int vid = v.getId();
            //EditText AccountName
            if(vid == R.id.et_login_name){
                //1. 第一次点击时， 会出现历史账户名
                if(etName.length() == 0){
                    etName.setAdapter(adapterHistory);
                    etName.showDropDown();
                } else {
                    //2. 非第一次点击
                    // 2.1  如果已经选择过历史账户， 再点击就是清除
                    if(StringUtil.isNotEmpty(theRealAccount)){
                        theRealAccount = null;
                        etName.setText("");
                    } else{
                        //2.2 如果是自己输入的， 就完整展现
                        // do nothing
                    }
                }
            }
        }
        return false; //不consume掉这个touch事件
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // TODO here give out the opportunity to etPwd.requestFocus();
    }

    @Override
    public void onDeleteHistory(int index) {
        Toast.makeText(getContext(), "delete this account name in history", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if(v == ivDeleteName){
            theRealAccount = null;
            etName.setText("");
            ivDeleteName.setVisibility(View.GONE);
        } else if(v == ivShowMore) {
            etName.dismissDropDown();
            etName.setAdapter(adapterHistory);
            etName.showDropDown();
        }
    }
}

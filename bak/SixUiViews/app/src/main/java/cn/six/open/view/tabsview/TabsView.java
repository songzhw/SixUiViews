package cn.six.open.view.tabsview;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;




import cn.six.open.R;
import cn.six.open.util.UiUtil;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TabsView extends LinearLayout implements OnClickListener{
	/**(默认风格) 白底黑字的UI风格. 一般用于正文*/
	private final static int STYLE_WHITE_BG_BLACK_TEXT = 1; 
	/**黑底白字的UI风格. 一般用于TitleBar*/
	private final static int STYLE_BLACK_BG_WHITE_TEXT = 2; 
	private int uiStyle = STYLE_WHITE_BG_BLACK_TEXT;
	private int basic_id_offset = 150;
	
	private List<String> tabTexts;
	private int tabCount;
	private Context ctx;
	/**当前选中了哪个Tab*/
	public int currentIndex = 0;
	private OnTabItemClickListener listener;
	
	private TextView oldView;

	public TabsView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	public TabsView(Context context) {
		super(context);  
	}

	private void init(Context context, AttributeSet attrs) {
		this.ctx = context;
		TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TabsView);
		uiStyle = ta.getInteger(R.styleable.TabsView_tab_ui_style, STYLE_WHITE_BG_BLACK_TEXT);
		
		String allTitle = ta.getString(R.styleable.TabsView_tab_text);
		if (TextUtils.isEmpty(allTitle)) {
			return;
		}
		String[] titlesTemp = allTitle.split(",");
		tabTexts = Arrays.asList(titlesTemp);
		tabCount = tabTexts.size();
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		for(int i = 0 ; i < tabCount ; i++){
			TextView aTabView = (TextView) inflater.inflate(R.layout.item_tabs_view, null);
			if(i == 0 ){
				aTabView.setTextColor(getTextColorOfSelectedTab());
				oldView = aTabView;
			} else {
				aTabView.setTextColor(getTextColorOfUnSelectedTab());
			}
			if(i == 0){
				aTabView.setBackgroundResource(getBg_SelectedLeft());
			} else if(i == (tabCount - 1)){
				aTabView.setBackgroundResource(getBg_UnSelectedRight());
			} else{
				aTabView.setBackgroundResource(getBg_UnSelectedMiddle());
			}
			
			aTabView.setText(tabTexts.get(i));
			LayoutParams lp = new LayoutParams(0, LayoutParams.FILL_PARENT);
			lp.weight = 1;
			if( i != 0 ){
				int marginLeft = UiUtil.dp2px(context, 1);
				lp.leftMargin = -1 * marginLeft;
			}
			aTabView.setLayoutParams(lp);
			aTabView.setId(basic_id_offset + i);
			aTabView.setOnClickListener(this);
			this.addView(aTabView);
		}
	}

	public void setOnTabItemClickListener(OnTabItemClickListener listener) {
		this.listener = listener;
	}

	@Override
	public void onClick(View v) {
		int vid = v.getId();
		currentIndex = vid - basic_id_offset;
		
		TextView newView = (TextView) v;
		//不加这个"==就return"， 那点击同一个按钮， 按钮就成了未选中状态了
		if(newView == oldView) {
			return;
		}
		newView.setTextColor(getTextColorOfSelectedTab());
		oldView.setTextColor(getTextColorOfUnSelectedTab());
		
		if(currentIndex == 0){
			newView.setBackgroundResource(getBg_SelectedLeft());
		} else if(currentIndex == (tabCount - 1)){
			newView.setBackgroundResource(getBg_SelectedRight());
		} else{
			newView.setBackgroundResource(getBg_SelectedMiddle());
		}
		
		int oldIndex = oldView.getId()-basic_id_offset;
		if(oldIndex == 0){
			oldView.setBackgroundResource(getBg_UnSelectedLeft());
		} else if(oldIndex == (tabCount - 1)){
			oldView.setBackgroundResource(getBg_UnSelectedRight());
		} else{
			oldView.setBackgroundResource(getBg_UnSelectedMiddle());
		}
		
		
		oldView = newView;
				
		if(listener != null){
			listener.onTabItemClick(currentIndex);
		} else {
			System.err.println("ERROR : You didn't set the listener of TabsView !!!");
		}
	}
	
	private int getBg_SelectedLeft(){
		if(uiStyle == STYLE_BLACK_BG_WHITE_TEXT){
			return R.drawable.shape_left_white_whitestroke;
		}
		//默认值
		return R.drawable.shape_left_black;
	}
	
	private int getBg_UnSelectedLeft(){
		if(uiStyle == STYLE_BLACK_BG_WHITE_TEXT){
			return R.drawable.shape_left_black_whitestroke;
		}
		//默认值
		return R.drawable.shape_left_white;
	}
	
	private int getBg_SelectedMiddle(){
		if(uiStyle == STYLE_BLACK_BG_WHITE_TEXT){
			return R.drawable.shape_middle_white_whitestroke;
		}
		//默认值
		return R.drawable.shape_middle_black;
	}
	
	private int getBg_UnSelectedMiddle(){
		if(uiStyle == STYLE_BLACK_BG_WHITE_TEXT){
			return R.drawable.shape_middle_black_whitestroke;
		}
		//默认值
		return R.drawable.shape_middle_white;
	}
	
	private int getBg_SelectedRight(){
		if(uiStyle == STYLE_BLACK_BG_WHITE_TEXT){
			return R.drawable.shape_right_white_whitestroke;
		}
		//默认值
		return R.drawable.shape_right_black;
	}
	
	private int getBg_UnSelectedRight(){
		if(uiStyle == STYLE_BLACK_BG_WHITE_TEXT){
			return R.drawable.shape_right_black_whitestroke;
		}
		//默认值
		return R.drawable.shape_right_white;
	}
	
	private int getTextColorOfSelectedTab(){
		Resources res = ctx.getResources();
		if(uiStyle == STYLE_BLACK_BG_WHITE_TEXT){
			return res.getColor(R.color.tabs_view_black_text_color);
		}
		//默认值
		return res.getColor(R.color.tabs_view_white_text_color);
	}
	
	private int getTextColorOfUnSelectedTab(){
		Resources res = ctx.getResources();
		if(uiStyle == STYLE_BLACK_BG_WHITE_TEXT){
			return res.getColor(R.color.tabs_view_white_text_color);
		}
		//默认值
		return res.getColor(R.color.tabs_view_black_text_color);
	}
}

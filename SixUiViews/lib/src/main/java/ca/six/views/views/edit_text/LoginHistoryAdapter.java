package ca.six.views.views.edit_text;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ca.six.views.R;
import ca.six.views.util.StringUtil;


public class LoginHistoryAdapter extends BaseAdapter implements Filterable {
	private Context context;
	private LayoutInflater inflater;
	private HistoryFilter filter;
	private List<String> data;
	private List<String> filteredList;
	private OnDeleteHistoryListener listener;

	public LoginHistoryAdapter(Context context, List<String> data, OnDeleteHistoryListener lis) {
		super();
		this.context = context;
		this.data = data;
		this.listener = lis;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public void setData(List<String> arg){
		this.data = arg;
	}

	@Override
	public int getCount() {
		return data == null ? 0 : data.size();
	}

	@Override
	public Object getItem(int position) {
		return data == null ? position : StringUtil.getMaskName(data.get(position));
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.item_login_popup_history, null);
			holder.tv = (TextView) convertView.findViewById(R.id.tv_login_popup_history_name);
			holder.iv = (ImageView) convertView.findViewById(R.id.iv_login_popup_delete_history_name);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tv.setText(StringUtil.getMaskName( data.get(position) ) );
		holder.iv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(listener != null){
					listener.onDeleteHistory(position);
				}
			}
		});
		return convertView;
	}
	
	class ViewHolder {
		TextView tv;
		ImageView iv;
	}
	
	@Override
	public Filter getFilter() {
		return filter == null ? (new HistoryFilter()) : filter;
	}
	
	private class HistoryFilter extends Filter {

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
//			System.out.println("performFiltering("+constraint+")");
			filteredList = data;
			FilterResults result = new FilterResults();
			result.values = filteredList;
			result.count  = 1;
			return result;
		}

		@Override
		protected void publishResults(CharSequence constraint, FilterResults results) {
			filteredList = (List<String>) results.values;
//			System.out.println("publishResults("+constraint+", "+filteredList+")");
			if (results.count > 0) {
				notifyDataSetChanged();
			} else {
				notifyDataSetInvalidated();
			}
		}
		
	}
	
	public interface OnDeleteHistoryListener{
		void onDeleteHistory(int index);
	}
}

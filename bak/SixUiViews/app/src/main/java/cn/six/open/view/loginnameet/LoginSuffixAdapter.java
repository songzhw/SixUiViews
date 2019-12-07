package cn.six.open.view.loginnameet;

import java.util.ArrayList;
import java.util.List;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import cn.six.open.R;

public class LoginSuffixAdapter extends BaseAdapter implements Filterable {
	private Context context;
	private ArrayFilter filter;
	private ArrayList<String> suffixList; // 所有的Item
	private List<String> filterResult;    // 过滤后的Item
	private LayoutInflater inflater;
	private final Object mLock = new Object();

	public LoginSuffixAdapter(Context context, ArrayList<String> originalValues) {
		this.context = context;
		this.suffixList = originalValues;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return filterResult == null ? 0 : filterResult.size();
	}

	@Override
	public Object getItem(int position) {
		return filterResult.get(position);
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
			convertView = inflater.inflate(R.layout.item_popup_login_suffix, null);
			holder.tv = (TextView) convertView.findViewById(R.id.tv_item_popup_login_suffix);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tv.setText(filterResult.get(position));
		return convertView;
	}

	class ViewHolder {
		TextView tv;
	}

	public ArrayList<String> getAllItems() {
		return suffixList;
	}
	

	@Override
	public Filter getFilter() {
		if (filter == null) {
			filter = new ArrayFilter();
		}
		return filter;
	}

	private class ArrayFilter extends Filter {

		@Override
		protected FilterResults performFiltering(CharSequence input) {
			FilterResults results = new FilterResults();
			if (input == null || input.length() == 0) {
				synchronized (mLock) {
					Log.i("tag", "mOriginalValues.size=" + suffixList.size());
					ArrayList<String> list = new ArrayList<String>();
					results.values = list;
					results.count = 0;
					return results;
				}
			} else {
				String text = input.toString();
				if(text.matches("^[a-zA-Z0-9_]+$")) {
					int count = suffixList.size();
					final ArrayList<String> newValues = new ArrayList<String>(count);
	
					for (int i = 0; i < count; i++) {
						final String value = suffixList.get(i);
						newValues.add(input+value);
					}
	
					results.values = newValues;
					results.count = newValues.size();
				} else {
					results.values = new ArrayList<String>();
					results.count = 0;
				}
			}

			return results;
		}

		@Override
		protected void publishResults(CharSequence constraint, FilterResults results) {
			filterResult = (List<String>) results.values;
			if (results.count > 0) {
				notifyDataSetChanged();
			} else {
				notifyDataSetInvalidated();
			}
		}

	}

}

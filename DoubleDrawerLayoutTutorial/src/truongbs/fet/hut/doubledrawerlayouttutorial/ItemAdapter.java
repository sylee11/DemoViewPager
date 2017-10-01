package truongbs.fet.hut.doubledrawerlayouttutorial;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ItemAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	private Context mContext;
	private ArrayList<ItemNavigation> arr;

	public ItemAdapter(Context mContext, ArrayList<ItemNavigation> arr) {
		super();
		this.mContext = mContext;
		this.arr = arr;
		mInflater = LayoutInflater.from(mContext);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		int count = 0;
		if (arr != null) {
			count = arr.size();
		}
		return count;
	}

	@Override
	public Object getItem(int pos) {
		// TODO Auto-generated method stub
		return arr.get(pos);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		viewHolder holder;

		if (convertView == null) {
			holder = new viewHolder();
			convertView = mInflater
					.inflate(R.layout.layout_item, parent, false);
			holder.itemIcon = (ImageView) convertView
					.findViewById(R.id.itemIcon);
			holder.itemName = (TextView) convertView
					.findViewById(R.id.itemName);
			convertView.setTag(holder);
		} else {
			holder = (viewHolder) convertView.getTag();
		}

		final ItemNavigation itemNavigation = arr.get(position);
		
		if (itemNavigation != null) {
			holder.itemIcon.setImageResource(itemNavigation.getItemIcon());
			holder.itemName.setText(itemNavigation.getItemName());
		}

		// bat su kien khi click vao icon
		holder.itemIcon.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(mContext, "Ban da tap chon hinh anh",
						Toast.LENGTH_LONG).show();
			}
		});
		return convertView;
	}

	public class viewHolder {
		public TextView itemName;
		public ImageView itemIcon;
	}
}

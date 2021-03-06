package de.tum.in.tumcampus.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import de.tum.in.tumcampus.R;

/**
 * Adapter to show list of plans.
 */
public class PlanListAdapter extends BaseAdapter {

	public static class ViewHolder {
		public TextView detail;
		public ImageView icon;
		public TextView title;
	}

	private final Activity activity;
	private LayoutInflater inflater = null;
	private final ArrayList<PlanListEntry> planList;

    public PlanListAdapter(Activity activity, ArrayList<PlanListEntry> planList) {
		this.activity = activity;
		this.planList = planList;
		this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return planList.size();
	}

	@Override
	public Object getItem(int position) {
		return planList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;

		ViewHolder holder;
		if (convertView == null) {
			vi = this.inflater.inflate(R.layout.activity_plans_listview, parent, false);

			holder = new ViewHolder();
			holder.icon = (ImageView) vi.findViewById(R.id.list_menu_icon);
			holder.title = (TextView) vi.findViewById(R.id.list_menu_title);
			holder.detail = (TextView) vi.findViewById(R.id.list_menu_detail);
			vi.setTag(holder);
		} else {
			holder = (ViewHolder) vi.getTag();
		}

        PlanListEntry item = planList.get(position);
		holder.icon.setImageResource(item.imageId);
		holder.title.setText(activity.getResources().getText(item.titleId));
        if(item.detailId==R.string.empty_string) {
            holder.detail.setVisibility(View.GONE);
        } else {
            holder.detail.setVisibility(View.VISIBLE);
            holder.detail.setText(activity.getResources().getText(item.detailId));
        }
		return vi;
	}

    public static class PlanListEntry {
        public final int imageId;
        public final int titleId;
        public final int detailId;
        public final int imgId;

        public PlanListEntry(int thumbId, int titleId, int detailId, int imgId) {
            this.imageId = thumbId;
            this.titleId = titleId;
            this.detailId = detailId;
            this.imgId = imgId;
        }
    }
}
package com.example.mu338.stampinseoul;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

    // MoreActivity 어댑터 클래스.

public class MoreAdapter extends BaseExpandableListAdapter /*RecyclerView.Adapter<MoreAdapter.CustomViewHolder>*/ {

    private ArrayList<String> groupList;
    private ArrayList<ArrayList<String>> chileList;

    private LayoutInflater inflater = null;
    private ViewHolder viewHolder = null;

    public MoreAdapter(Context c, ArrayList<String> groupList, ArrayList<ArrayList<String>> chileList) {
        this.inflater = LayoutInflater.from(c);
        this.groupList = groupList;
        this.chileList = chileList;
    }

    // 부모 리스트
    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if( convertView == null ){

            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.more_item, parent, false);

            viewHolder.txtContent = convertView.findViewById(R.id.txtContent);
            viewHolder.imgArrow = convertView.findViewById(R.id.imgArrow);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if ( isExpanded ){
            viewHolder.imgArrow.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp);
        }else{
            viewHolder.imgArrow.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp);
        }

        viewHolder.txtContent.setText(groupList.get(groupPosition));

        return convertView;
    }


    // 자식 리스트
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return chileList.get(groupPosition).get(childPosition);
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return chileList.get(groupPosition).size();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if( convertView == null ){

            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.more_item_child, parent, false);

            viewHolder.txtContentChild = convertView.findViewById(R.id.txtContentChild);

            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.txtContentChild.setText(chileList.get(groupPosition).get(childPosition));

        return convertView;
    }



    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public class ViewHolder{

        public ImageView imgArrow;
        public TextView txtContent;
        public TextView txtContentChild;

    }

}

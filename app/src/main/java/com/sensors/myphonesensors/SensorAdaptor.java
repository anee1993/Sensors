package com.sensors.myphonesensors;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by MobPsycho100 on 16-04-2017.
 */

public class SensorAdaptor extends BaseExpandableListAdapter {

    Context context;
    List<String> sensorNames;
    private Map<String, List<Child>> listDataChild;

    public SensorAdaptor(Context context, List<String> sensorNames,
                         Map<String, List<Child>> listChildData) {
        this.context = context;
        this.sensorNames = sensorNames;
        this.listDataChild = listChildData;
    }

    @Override
    public int getGroupCount() {
        return this.sensorNames.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listDataChild.get(this.sensorNames.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.sensorNames.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.listDataChild.get(this.sensorNames.get(groupPosition))
                .get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.sensor_row, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.sensor_name);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final Child child = (Child) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.sensor_detail, null);
        }

        TextView sensor_version = (TextView) convertView
                .findViewById(R.id.sensor_version);
        TextView sensor_vendor = (TextView) convertView
                .findViewById(R.id.sensor_vendor);

        sensor_version.setText(child.getVersion());
        sensor_vendor.setText(child.getVendor());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

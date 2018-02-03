package com.sensors.myphonesensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SensorActivity extends AppCompatActivity {

    List<String> sensorNames;
    Map<String, List<Child>> listDataChild;
    ExpandableListView expListView;
    private SensorManager sensorManager;
    private List<Sensor> deviceSensors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        prepareSensorData(deviceSensors);
        SensorAdaptor adaptor = new SensorAdaptor(this, sensorNames, listDataChild);
        expListView.setAdapter(adaptor);
    }

    private void prepareSensorData(List<Sensor> deviceSensors) {
        listDataChild = new HashMap<>();
        sensorNames = new ArrayList<>();
        List<Child> additionalInfo;
        Child child;
        for (Sensor sensor : deviceSensors) {
            sensorNames.add(sensor.getName());
            child = new Child();
            child.setVendor(sensor.getVendor());
            child.setVersion(sensor.getVersion() + "");
            additionalInfo = new ArrayList<>();
            additionalInfo.add(child);
            listDataChild.put(sensor.getName(), additionalInfo);
        }
    }
}

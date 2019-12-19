package waqtesalaat.nadeem;
import android.Manifest;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class GateWayActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener {

    private GPSCoordinates mTracker;
    private PrayerTiming mPrayermodel;
    private TextView mSunset;
    private TextView mSunrise;
    private TextView mFajr;
    private TextView mZuhur;
    private TextView mAsr;
    private TextView mMagrib;
    private TextView mIsha;
    private TextView mSetArea;
    private String mLati;
    private String mLong;
    Double mLatitude=34.083656;
    Double mLongitude=74.797371;
    private LocationManager mLocationManager;
    private static final int REQUEST_CODE_PERMISSION = 1;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    NumberPicker picker;
    String[] areas ={"Anantnag","Bandipora","Baramulla","Budgam","Ganderbal","Kulgam","Kupwara","Pulwama","Shopian","Srinagar","Sopore"};
    String[] latitude  = {"33.729729","34.427094","34.202148", "34.0229547","34.216496","33.6451329","34.5092999","33.871841","33.8113259","34.083656","34.298676"};
    String[] longitude= {"75.149780","74.6674083","74.348259", "74.7218128","74.771942","75.0184944","74.2675792","74.899376","75.0169789","74.797371","74.470146"};


    public Double getmLatitude() {
        return mLatitude;
    }

    public void setmLatitude(Double mLatitude) {
        this.mLatitude = mLatitude;
    }

    public Double getmLongitude() {
        return mLongitude;
    }

    public void setmLongitude(Double mLongitude) {
        this.mLongitude = mLongitude;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gate_way);
        GPSCoordinates coordinates = new GPSCoordinates(this);
        double mLong = coordinates.getLongitude();
        double mLati = coordinates.getLatitude();
        Log.e("GWA***","\t\t\t****Longitude\t\t\t"+mLong+mLati);
        mSunset = findViewById(R.id.sunsettiming);
        mSunrise = findViewById(R.id.sunrisetiming);
        mFajr = findViewById(R.id.fajrtiming);
        mZuhur = findViewById(R.id.dhuhurtiming);
        mAsr = findViewById(R.id.asrtiming);
        mMagrib = findViewById(R.id.magribtiming);
        mIsha = findViewById(R.id.ishatiming);
        mSetArea = findViewById(R.id.longlati);
        DataTimingForPrayers prayerTiming = getTimingsList();
        mSunset.setText(prayerTiming.getmSunset());
        mSunrise.setText(prayerTiming.getmSunrise());
        mFajr.setText(prayerTiming.getmFajr());
        mZuhur.setText(prayerTiming.getmDhuhur());
        mAsr.setText(prayerTiming.getmAsr());
        mMagrib.setText(prayerTiming.getmMagrib());
        mIsha.setText(prayerTiming.getmIsha());
    }

    public DataTimingForPrayers getTimingsList() {
        mPrayermodel = new PrayerTiming();
        //Double mLatitude = 34.083656;
        //Double mLongitude = 74.797371;
        Calendar mCalendar = Calendar.getInstance();
        Date mCurrentDate = new Date();
        mCalendar.setTime(mCurrentDate);
        TimeZone zone = mCalendar.getTimeZone();
        int offset = zone.getRawOffset();
        if(zone.inDaylightTime(new Date())){
            offset = offset + zone.getDSTSavings();
        }
        int offsetHrs = offset / 1000 / 60 / 60;
        int offsetMins = (offset / 1000 / 60 % 60);
        double offsetMinValue = offsetMins/(double)60;
        double val = offsetHrs+offsetMinValue;//
        Log.e("GWA","\t\t\tOffset ZoneID\t\t\t"+val);
        mPrayermodel.setTimeFormat(mPrayermodel.Time12);
        mPrayermodel.setCalcMethod(mPrayermodel.Makkah);
        mPrayermodel.setAsrJuristic(mPrayermodel.Hanafi);
        mPrayermodel.setAdjustHighLats(mPrayermodel.AngleBased);
        int[] offsets = { 0, 0, 0, 0, 0, 0, 0}; // {Fajr,Sunrise,Dhuhr,Asr,Sunset,Maghrib,Isha}

        mPrayermodel.tune(offsets);
            ArrayList prayerTimes = mPrayermodel.getPrayerTimes(mCalendar, getmLatitude(),
                    getmLongitude(), val);
        DataTimingForPrayers timings = new DataTimingForPrayers();
            timings.setmFajr((String) prayerTimes.get(0));
            timings.setmSunrise((String)prayerTimes.get(1));
            timings.setmDhuhur((String)prayerTimes.get(2));
            timings.setmAsr((String)prayerTimes.get(3));
            timings.setmSunset((String)prayerTimes.get(4));
            timings.setmMagrib((String)prayerTimes.get(5));
            timings.setmIsha((String)prayerTimes.get(6));
         return timings;
    }

    @Override
    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
        setmLatitude(Double.valueOf(latitude[numberPicker.getValue()]));
        setmLongitude(Double.valueOf(longitude[numberPicker.getValue()]));

        Log.e("LongiLati","\tLatitude\t"+getmLatitude());
        Log.e("LongiLati","\tLongitude\t"+getmLongitude());
        Toast.makeText(this,
                "selected number " + numberPicker.getValue(), Toast.LENGTH_SHORT).show();
    }

    public void showNumberPicker(View view){
        AreaPickerDialog newFragment = new AreaPickerDialog();
        newFragment.setValueChangeListener(this);
        newFragment.show(getSupportFragmentManager(), "time picker");
    }
}

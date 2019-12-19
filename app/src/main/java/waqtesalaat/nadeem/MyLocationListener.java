//package waqtesalaat.nadeem;
//
//import android.location.Location;
//import android.util.Log;
//import android.view.View;
//import android.widget.Toast;
//
//import com.google.android.gms.location.LocationListener;
//
//import static android.content.ContentValues.TAG;
//
///**
// * Created by Nadeem Bhat on 18-12-2018.
// * Copy Right (c) 05:04 PM STC ,Rangreth
// * nnbhat@stc.in
// */
//private class MyLocationListener implements LocationListener {
//
//    @Override
//    public void onLocationChanged(Location loc) {
////        editLocation.setText("");
//        pb.setVisibility(View.INVISIBLE);
//        Toast.makeText(
//                getBaseContext(),
//                "Location changed: Lat: " + loc.getLatitude() + " Lng: "
//                        + loc.getLongitude(), Toast.LENGTH_SHORT).show();
//        String longitude = "Longitude: " + loc.getLongitude();
//        Log.v(TAG, longitude);
//        String latitude = "Latitude: " + loc.getLatitude();
//        Log.v(TAG, latitude);
//
//        /*------- To get city name from coordinates -------- */
//        String cityName = null;
//        Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
//        List<Address> addresses;
//        try {
//            addresses = gcd.getFromLocation(loc.getLatitude(),
//                    loc.getLongitude(), 1);
//            if (addresses.size() > 0) {
//                System.out.println(addresses.get(0).getLocality());
//                cityName = addresses.get(0).getLocality();
//            }
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//        String s = longitude + "\n" + latitude + "\n\nMy Current City is: "
//                + cityName;
//        editLocation.setText(s);
//    }
//
//    @Override
//    public void onProviderDisabled(String provider) {}
//
//    @Override
//    public void onProviderEnabled(String provider) {}
//
//    @Override
//    public void onStatusChanged(String provider, int status, Bundle extras) {}
//}

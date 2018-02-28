package geolocation.harisevak.com.geolocation;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.audiofx.BassBoost;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

/**
 * Created by hp on 28-02-2018.
 */

public class GPStracker implements LocationListener {
    Context context;
    public GPStracker(Context c){
        context=c;
    }

    public Location getLocation(){
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            Toast.makeText(context,"Permission NOT Granted", Toast.LENGTH_LONG).show();
            return null;
        }
        LocationManager lm= (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        boolean isGpsEnabled= lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if(isGpsEnabled){
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,this);
            Location l= lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return l;
        }else{
            Toast.makeText(context,"Please Enable GPS",Toast.LENGTH_LONG).show();
            return null;
        }
    }
    @Override
    public void onLocationChanged(Location location) {


    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

}

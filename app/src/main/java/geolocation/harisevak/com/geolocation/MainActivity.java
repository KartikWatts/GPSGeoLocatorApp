package geolocation.harisevak.com.geolocation;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnGetLoc;
    private TextView txt;
    public static int i=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt= (TextView)findViewById(R.id.txt);
        btnGetLoc = (Button) findViewById(R.id.btnGetLoc);
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},123);

        btnGetLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GPStracker g= new GPStracker(getApplicationContext());
                Location l =g.getLocation();

                if(l!=null){
                    double lat= l.getLatitude();
                    double lon=l.getLongitude();
                    Toast.makeText(getApplicationContext(),"LAT: "+ lat+ "\n LON: "+ lon,Toast.LENGTH_LONG).show();
                    txt.append("["+i+"]"+"\nLAT: "+ lat+ "\n LON: "+ lon+"\n__________________________\n");
                    i++;
                }
            }
        });
    }
}

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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    DatabaseReference myRef1;

    private Button btnGetLoc;
    private TextView txt;
    private Button btnStop;
    public static int i=1;
    public static int status=1;
    public Location l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt= (TextView)findViewById(R.id.txt);
        btnGetLoc = (Button) findViewById(R.id.btnGetLoc);
        btnStop= (Button) findViewById(R.id.btnStop);

        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},123);

        btnGetLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status=1;
                GPStracker g= new GPStracker(getApplicationContext());
                    while(status!=0){
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        l =g.getLocation();

                        double lat= l.getLatitude();
                    double lon=l.getLongitude();
                    Toast.makeText(getApplicationContext(),"LAT: "+ lat+ "\n LON: "+ lon,Toast.LENGTH_LONG).show();
                    txt.append("["+i+"]"+"\nLAT: "+ lat+ "\n LON: "+ lon+"\n__________________________\n");
                    database= FirebaseDatabase.getInstance();
                    myRef= database.getReference("GPS/location"+i+"/latitude");
                    myRef.setValue(lat);
                    myRef1= database.getReference("GPS/location"+i+"/longitude");
                    myRef1.setValue(lon);
                        i++;

                        btnStop.setOnClickListener(new View.OnClickListener(){

                            @Override
                            public void onClick(View view) {
                                status=0;
                            }
                        });

                }
            }
        });

    }
}

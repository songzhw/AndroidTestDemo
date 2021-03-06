package cn.six.espresso.location;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import cn.six.aut.R;


@SuppressWarnings("MissingPermission")
public class AndroidLocationDemo extends Activity implements GoogleApiClient.ConnectionCallbacks, LocationListener,
        GoogleApiClient.OnConnectionFailedListener {
    private FusedLocationProviderApi locationClient;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;

    private TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_btn);
        tv = (TextView) findViewById(R.id.tv_simple);

        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        googleApiClient.connect();

        locationClient = LocationServices.FusedLocationApi;

        locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(5000)
                .setFastestInterval(1000)
                .setSmallestDisplacement(2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        googleApiClient.disconnect();
    }

    public void onClickSimpleButton(View v) {
    }

    public void onClickSimpleButton2(View v) {
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (googleApiClient.isConnected()) {
            locationClient.requestLocationUpdates(googleApiClient, locationRequest, this);

            Location location = locationClient.getLastLocation(googleApiClient);
            if (location == null) {
                return;
            }
            onLocationChanged(location);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        System.out.println("szw google api client : suspended");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        System.out.println("szw google play connect failed");
    }

    @Override
    public void onLocationChanged(Location location) {
        String lastLocation = "latitude  = " + location.getLatitude() + " ; longitude = " + location.getLongitude();
        tv.setText(lastLocation);
        System.out.println("szw onLocationChanged() : " + lastLocation);
    }
}


package br.com.rbarrelo.clima.helpers;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import br.com.rbarrelo.clima.util.Commom;

/**
 * Created by rafaelbarrelo on 10/17/15.
 */
public class GoogleApiHelper implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener  {

    private GoogleApiClient googleApiClient;
    private boolean conectado = false;

    public GoogleApiClient getInstanceOfApiClient(Context context){
        if (googleApiClient == null) {
            iniciaConexaoGoogleApi(context);
        }

        return this.googleApiClient;
    }

    public boolean isConectado() {
        return conectado;
    }

    private synchronized void iniciaConexaoGoogleApi(Context context){
        googleApiClient = new GoogleApiClient.Builder(context)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();

        googleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.i(Commom.TAG, "onConnected: " + bundle);
        conectado = true;
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(Commom.TAG, "onConnectionSuspended: "  + i);
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(Commom.TAG, "onConnectionFailed: " + connectionResult);
    }
}

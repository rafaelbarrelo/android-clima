package br.com.rbarrelo.clima.helpers;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import br.com.rbarrelo.clima.services.RecuperadorDeCidadeIntentService;
import br.com.rbarrelo.clima.util.Commom;

/**
 * Created by rafaelbarrelo on 10/17/15.
 */
public class GoogleApiHelper implements GoogleApiClient.ConnectionCallbacks,
                                        GoogleApiClient.OnConnectionFailedListener, OnClickListener{

    private GoogleApiClient googleApiClient;
    private boolean conectado = false;
    private Context context;

    public GoogleApiHelper(Context context){
        this.context = context;
        if (googleApiClient == null) {
            iniciaConexaoGoogleApi(context);
        }
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
        CarregaLocalizacaoAtual();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(Commom.TAG, "onConnectionSuspended: "  + i);
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(Commom.TAG, "onConnectionFailed: " + connectionResult);
    }

    @Override
    public void onClick(View view) {
        CarregaLocalizacaoAtual();
    }

    private void CarregaLocalizacaoAtual(){
        if (this.isConectado()) {
            Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
            if (location != null) {
                Log.i(Commom.TAG, "Latitude: " + location.getLatitude());
                Log.i(Commom.TAG, "Longitude: " + location.getLongitude());

                Intent it = new Intent(context, RecuperadorDeCidadeIntentService.class);
                it.putExtra(RecuperadorDeCidadeIntentService.LOCATION, location);
                context.startService(it);
            }
            else{
                Log.e(Commom.TAG, "CarregaLocalizacaoAtual - Location NULL");
            }
        }else{
            Log.e(Commom.TAG, "CarregaLocalizacaoAtual - Não conectado");
        }
    }
}

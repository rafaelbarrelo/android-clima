package br.com.rbarrelo.clima.services;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import br.com.rbarrelo.clima.eventbus.MessageEvent;
import br.com.rbarrelo.clima.modelos.Cidade;
import br.com.rbarrelo.clima.util.Commom;
import de.greenrobot.event.EventBus;

/**
 * Created by rafaelbarrelo on 10/17/15.
 */
public class RecuperadorDeCidadeIntentService extends IntentService{

    public static final String LOCATION = "location";

    public RecuperadorDeCidadeIntentService() {
        super("recuperador_thread");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            Location location = intent.getParcelableExtra(RecuperadorDeCidadeIntentService.LOCATION);
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());

            List<Address> list = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if(list != null && list.size() > 0 && list.get(0) != null) {
                String nome = list.get(0).getLocality();
                String estado = list.get(0).getAdminArea();

                if (nome != null){
                    Cidade cidade = new Cidade(nome, estado);
                    MessageEvent messageEvent = new MessageEvent(cidade);
                    EventBus.getDefault().post(messageEvent);
                }
                else{
                    Log.e(Commom.TAG, "Não recuperou nome da cidade: "
                            + location.getLatitude() + "," + location.getLongitude());
                }
            }
            else{
                Log.e(Commom.TAG, "Não recuperou lista: "
                        + location.getLatitude() + "," + location.getLongitude());
            }
        }
        catch (IOException e) {
            Log.e(Commom.TAG, "OException: " + e.getMessage());
        }
        catch (IllegalArgumentException e){
            Log.e(Commom.TAG, "IllegalArgumentException:" + e.getMessage());
        }
    }
}

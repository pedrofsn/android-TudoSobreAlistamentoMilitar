package alistamento.militar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.avast.android.dialogs.iface.ISimpleDialogListener;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import alistamento.militar.R;
import alistamento.militar.fragment.FragmentPerguntas;
import alistamento.militar.utils.GPlay;


public class MainActivity extends ActionBarActivity implements
        ISimpleDialogListener {

    private InterstitialAd interstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new FragmentPerguntas())
                    .commit();
        }

        toolbar.setTitleTextColor(getResources().getColor(R.color.answer));

        configIntersticial();
    }

    private void configIntersticial() {
        interstitial = new InterstitialAd(this);
        interstitial.setAdUnitId(getString(R.string.ad_unit_id));

        AdRequest adRequest = new AdRequest.Builder()
                .setGender(AdRequest.GENDER_MALE)
                .addTestDevice("emulador")
                .build();

        interstitial.loadAd(adRequest);
        interstitial.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                finish();
            }
        });
    }

    private boolean showInterstitial() {
        if (interstitial != null && interstitial.isLoaded()) {
            interstitial.show();
            return true;
        }

        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.enviarDuvida:
                Intent itSend = new Intent(android.content.Intent.ACTION_SEND);
                itSend.setType("plain/text");
                itSend.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"faleconosco@ccomsex.eb.mil.br"});
                itSend.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.enviar_duvida) + " - " + getString(R.string.app_name));
                startActivity(itSend);
                break;
            case R.id.avaliarAppNaGooglePlay:
                GPlay.avaliarAppNaGooglePlay(this);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (!showInterstitial()) {
            super.onBackPressed();
        }
    }

    @Override
    public void onNegativeButtonClicked(int i) {
        Log.e("teste", "onNegativeButtonClicked");
    }

    @Override
    public void onNeutralButtonClicked(int i) {
        Log.e("teste", "onNeutralButtonClicked");

    }

    @Override
    public void onPositiveButtonClicked(int i) {
        Log.e("teste", "onPositiveButtonClicked");

    }
}

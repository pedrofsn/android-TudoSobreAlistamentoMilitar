package alistamento.militar.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by pedro on 13/04/15.
 */
public class GPlay {

    public static void avaliarAppNaGooglePlay(Context context) {
        try {
            // Tenta abrir pelo app da Google Play
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + context.getPackageName())));
        } catch (android.content.ActivityNotFoundException anfe) {
            // Se não tiver a Google Play (ex.: Cyanogen Mod), abre pela URL
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
        }
    }
}

package alistamento.militar.dialog;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.view.View;

import com.avast.android.dialogs.core.BaseDialogFragment;
import com.avast.android.dialogs.fragment.SimpleDialogFragment;

import alistamento.militar.R;
import alistamento.militar.adapter.AdapterDuvida;
import alistamento.militar.model.Duvida;

/**
 * Created by pedro on 09/04/15.
 */
public class SimpleDialog extends SimpleDialogFragment {

    public static String TAG = "SimpleDialog";

    private static Duvida duvida;

    public static void show(FragmentActivity activity, Duvida duvida) {
        duvida = duvida;
        new SimpleDialog().show(activity.getSupportFragmentManager(), TAG);
    }

    @Override
    public BaseDialogFragment.Builder build(BaseDialogFragment.Builder builder) {
        // Por ser static, só acessa via static. Se não entendeu, cheque o fonte da biblioteca.
        builder.setTitle(duvida.getPergunta());
        builder.setMessage(Html.fromHtml(duvida.getResposta()));

        builder.setNeutralButton(getString(R.string.compartilhar), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, duvida.getPergunta());
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, duvida.getResposta());
                startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.compartilhar)));
            }
        });

        builder.setNegativeButton(getString(R.string.fechar), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        return builder;
    }
}
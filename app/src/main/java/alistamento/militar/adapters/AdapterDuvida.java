package alistamento.militar.adapters;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.avast.android.dialogs.fragment.SimpleDialogFragment;

import alistamento.militar.R;
import alistamento.militar.models.Duvida;

/**
 * Created by pedrofsn on 11/01/2015.
 */
public class AdapterDuvida extends RecyclerView.Adapter<AdapterDuvida.ViewHolder> {

    private FragmentManager fragmentManager;
    private Duvida[] listDuvidas;
    private Context context;

    public AdapterDuvida(Context context, FragmentManager fragmentManager, Duvida[] result) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.listDuvidas = result;
    }

    @Override
    public AdapterDuvida.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {

        ViewHolder holder = new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_duvida, parent, false));
        holder.textView.setTag(holder);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Duvida duvida = listDuvidas[position];

        holder.textView.setText(duvida.getPergunta());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDialogFragment.createBuilder(context, fragmentManager)
                        .setTitle(duvida.getPergunta())
                        .setMessage(Html.fromHtml(duvida.getResposta()))
                        .setNegativeButtonText(context.getString(R.string.fechar))
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDuvidas.length;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public ViewHolder(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.textView);
        }
    }
}

package alistamento.militar;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by pedrofsn on 11/01/2015.
 */
public class AdapterDuvida extends RecyclerView.Adapter<AdapterDuvida.ViewHolder> {

    private Duvida[] listDuvidas;

    public AdapterDuvida(Duvida[] list) {
        this.listDuvidas = list;
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
        holder.textView.setText(listDuvidas[position].getPergunta());
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

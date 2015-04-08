package alistamento.militar.tasks;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import alistamento.militar.models.Duvida;
import alistamento.militar.domain.Searchable;


/**
 * Created by pedro.sousa on 24/12/2014.
 */
public class AsyncTaskLoad extends AsyncTask<String, Void, Duvida[]> {

    private final Context context;
    private final Searchable callback;

    public AsyncTaskLoad(Context context, Searchable callback) {
        this.context = context;
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        callback.onLoading(true);
    }

    @Override
    protected Duvida[] doInBackground(String... params) {
        return new Gson().fromJson(getStringJsonFromAssets("duvidas.json"), Duvida[].class);
    }

    @Override
    protected void onPostExecute(Duvida[] result) {
        super.onPostExecute(result);
        callback.onLoaded(result);
    }

    private String getStringJsonFromAssets(String arquivo) {
        try {
            InputStream is = context.getAssets().open(arquivo);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            return new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private String[] lerArquivos() {
        ArrayList<String> arrayListArquivos = new ArrayList<>();
        AssetManager assetManager = context.getAssets();
        try {
            String[] arrayFileList = assetManager.list("");

            for (String nomeDoPais : arrayFileList) {
                if (nomeDoPais.endsWith(".json")) {
                    arrayListArquivos.add(nomeDoPais.replace(".json", "").trim());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return arrayListArquivos.toArray(new String[arrayListArquivos.size()]);
    }
}

package com.architecturecomponents.data.remote.task;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpGetRequest extends AsyncTask<String, Void, String> {
    private static final String REQUEST_METHOD = "GET";
    private static final int READ_TIMEOUT = 20000;
    private static final int CONNECTION_TIMEOUT = 000;

    public interface Listeners {
        void onPreExecute();
        void onPostExecute(String success);
    }


    private final WeakReference<Listeners> callback;


    public HttpGetRequest(Listeners callback) {
        this.callback = new WeakReference<>(callback);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.callback.get().onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String stringUrl = params[0];
        String result;
        String inputLine;
        try {
            // Crée un objet URL contenant notre URL
            URL myUrl = new URL(stringUrl);
            // Créer une connexion
            HttpURLConnection connection = (HttpURLConnection)
                    myUrl.openConnection();
            // Définit les méthodes et les délais
            connection.setRequestMethod(REQUEST_METHOD);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);

            //Connectez-vous à notre URL
            connection.connect();
            //Create a new InputStreamReader
            InputStreamReader streamReader = new
                    InputStreamReader(connection.getInputStream());
            //Créer un nouveau InputStreamReader
            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();
            //Vérifiez si la ligne que nous lisons n'est pas nulle
            while ((inputLine = reader.readLine()) != null) {
                stringBuilder.append(inputLine);
            }
            //Fermez notre lecteur InputStream et Buffered
            reader.close();
            streamReader.close();
            //Définir notre résultat égal à notre stringBuilder
            result = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            result = null;
        }
        return result;
    }

    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        this.callback.get().onPostExecute(result);
    }
}


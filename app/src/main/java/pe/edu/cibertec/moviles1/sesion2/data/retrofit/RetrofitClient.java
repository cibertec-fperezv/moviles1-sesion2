package pe.edu.cibertec.moviles1.sesion2.data.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RetrofitClient {

    private static Retrofit RETROFIT = null;

    public static Retrofit getInstance() {
        if (RETROFIT == null) {
            OkHttpClient client = new OkHttpClient.Builder().build();

            RETROFIT = new Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }

        return RETROFIT;
    }

}

package com.angogasapps.retrofitlesson;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EchoClient {
    private static Retrofit retrofit;
    private static OkHttpClient httpClient = new OkHttpClient();

    public static Retrofit getRetrofit() {
        synchronized (EchoClient.class) {
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl("http://httpbin.org")
                        .client(httpClient)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return retrofit;
        }
    }
}

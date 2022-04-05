package com.angogasapps.retrofitlesson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.angogasapps.retrofitlesson.databinding.ActivityMainBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Retrofit retrofit;
    private EchoAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        retrofit = EchoClient.getRetrofit();
        api = retrofit.create(EchoAPI.class);
        binding.button.setOnClickListener(v -> {
            doRequest();
        });
    }

    private void doRequest() {
        Call<IpClass> call = api.getEcho();
        call.enqueue(new Callback<IpClass>() {
            @Override
            public void onResponse(Call<IpClass> call, Response<IpClass> response) {
                runOnUiThread(() -> {
                    IpClass body = response.body();
                    System.out.println(body.origin);
                    binding.textView.setText(body.origin);
                });
            }

            @Override
            public void onFailure(Call<IpClass> call, Throwable t) {
                runOnUiThread(() -> {
                    Toast.makeText(MainActivity.this, "Произошла ошибка", Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                });
            }
        });
    }
}
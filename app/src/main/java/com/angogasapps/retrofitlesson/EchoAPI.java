package com.angogasapps.retrofitlesson;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EchoAPI {
    @GET("/ip")
    Call<IpClass> getEcho();
}

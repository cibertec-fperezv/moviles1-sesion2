package pe.edu.cibertec.moviles1.sesion2.data.retrofit;

import java.util.List;

import pe.edu.cibertec.moviles1.sesion2.models.Country;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CountriesApi {

    @GET("/v3.1/all")
    Call<List<Country>> getCountries();

}

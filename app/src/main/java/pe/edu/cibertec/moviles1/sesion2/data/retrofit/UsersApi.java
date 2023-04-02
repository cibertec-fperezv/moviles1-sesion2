package pe.edu.cibertec.moviles1.sesion2.data.retrofit;

import java.util.List;

import pe.edu.cibertec.moviles1.sesion2.models.User;
import retrofit2.Call;
import retrofit2.http.GET;

public interface UsersApi {

    @GET("/users")
    Call<List<User>> getUsers();

}

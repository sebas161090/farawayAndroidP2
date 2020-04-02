package pe.edu.upc.faraway.api.service;

import pe.edu.upc.faraway.bean.request.LoginReq;
import pe.edu.upc.faraway.bean.response.LoginRes;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {
    @POST("login")
    Call<LoginRes> login(@Body LoginReq req);
}

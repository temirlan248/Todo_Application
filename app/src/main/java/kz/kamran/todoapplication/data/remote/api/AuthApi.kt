package kz.kamran.todoapplication.data.remote.api

import kz.kamran.todoapplication.data.remote.dto.LoginRequestDto
import kz.kamran.todoapplication.data.remote.dto.LoginResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("account/login")
    suspend fun login(
        @Body loginRequestDto: LoginRequestDto
    ): Response<LoginResponseDto>
}
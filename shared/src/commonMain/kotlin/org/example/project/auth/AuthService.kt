package org.example.project.auth

interface AuthService{
    suspend fun login(email:String,password:String) : AuthResult
    suspend fun register(email: String,password: String) : AuthResult
}

data class AuthResult(
    val success:Boolean,
    val errorMessage : String?=null
)
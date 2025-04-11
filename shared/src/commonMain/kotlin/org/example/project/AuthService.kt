package org.example.project

interface AuthService{
    suspend fun login(email:String,password:String) : Boolean
    suspend fun logout()
}

expect class AuthServiceFactory {
    fun create(): AuthService
}
package org.example.project.auth

expect class AuthServiceFactory {
    fun createAuthService() : AuthService
}
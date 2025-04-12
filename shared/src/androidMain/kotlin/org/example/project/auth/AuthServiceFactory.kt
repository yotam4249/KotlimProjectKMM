package org.example.project.auth

actual class AuthServiceFactory {
    actual fun createAuthService() : AuthService = FirebaseAuthServiceAndroid()
}
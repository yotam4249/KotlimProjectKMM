package org.example.project.auth

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await


class FirebaseAuthServiceAndroid : AuthService {
    private val auth = Firebase.auth

    override suspend fun login(email: String, password: String) : AuthResult {
        return try{
            Log.d("login","Email:   $email")
            Log.d("login","password:    $password")
            auth.signInWithEmailAndPassword(email.trim(),password.trim()).await()
            AuthResult(true)
        }catch(e : Exception) {
            AuthResult(false,"Login failed")
        }
    }

    override suspend fun register(email: String, password: String)  : AuthResult{
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            AuthResult(true)
        } catch (e: Exception) {
            AuthResult(false, "Registration failed")
        }
    }
}
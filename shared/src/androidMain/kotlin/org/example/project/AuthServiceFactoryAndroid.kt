package org.example.project

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

actual class AuthServiceFactory{
    actual fun create() : AuthService = AndroidAuthService()
}

class AndroidAuthService : AuthService {
    private val auth = FirebaseAuth.getInstance()

    override suspend fun login(email: String, password: String): Boolean {
        return try{
            auth.signInWithEmailAndPassword(email,password).await()
            true
        }catch (e:Exception)
        {
            false
        }
    }

    override suspend fun logout() {
        auth.signOut()
    }
}
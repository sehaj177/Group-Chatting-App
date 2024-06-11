package data

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import data.Result.Error
import data.Result.Success
import kotlinx.coroutines.tasks.await

class UserRepository(
    private val auth:FirebaseAuth,
    private val firestore: FirebaseFirestore
) {

    suspend fun signUp(
        email: String,
        password: String,
        firstName: String,
        lastName: String
    ): Result<Boolean> =
        try {
            Log.d("user2","hello reached")
            auth.createUserWithEmailAndPassword(email,password).await()
            val user = User(firstName,lastName,email)
            saveUserToFirebase(user)
            Success(true)
        } catch (e: Exception) {
            Error(e)
        }

    private suspend fun saveUserToFirebase(user: User){
        firestore.collection("users").document(user.email).set(user).await()
    }
    suspend fun login(
        email: String,
        password: String
    ):Result<Boolean> =
    try{
        auth.signInWithEmailAndPassword(email,password).await()
        Success(true)
    }catch(e:Exception){
        Error(e)
    }

    suspend fun getCurrentUser(): Result<User> = try {
        val uid = auth.currentUser?.email
        if (uid != null) {
            val userDocument = firestore.collection("users").document(uid).get().await()
            val user = userDocument.toObject(User::class.java)
            if (user != null) {
                Result.Success(user)
            } else {
                Result.Error(Exception("User data not found"))
            }
        } else {
            Result.Error(Exception("User not authenticated"))
        }
    } catch (e: Exception) {
        Result.Error(e)
    }
}
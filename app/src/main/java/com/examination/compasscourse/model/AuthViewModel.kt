package com.examination.compasscourse.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

// Sealed class to represent authentication states
sealed class AuthState {
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    data class Error(val message: String) : AuthState()
}

class AuthViewModel: ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    init {
        checkAuthStatus()
    }

    fun checkAuthStatus() {
        val currentUser = auth.currentUser
        if (currentUser == null) {
            _authState.value = AuthState.Unauthenticated
        } else {
            // Check if the email matches the admin email stored in Firestore
            checkIfAdmin(currentUser.email)
        }
    }

    private fun checkIfAdmin(userEmail: String?) {
        // Retrieve admin email from Firestore
        firestore.collection("admin").document("config").get()
            .addOnSuccessListener { document ->
                val adminEmail = document.getString("adminEmail")
                if (adminEmail == userEmail) {
                    _authState.value = AuthState.Authenticated  // Admin user
                } else {
                    _authState.value = AuthState.Unauthenticated  // Regular user
                }
            }
            .addOnFailureListener {
                _authState.value = AuthState.Error("Error retrieving admin email")
            }
    }

    fun login(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Email or Password can't be empty")
            return
        }
    }
        fun clearError(){
            _authState.value = AuthState.Unauthenticated
        }
        // Add your Firebase login logic here
}

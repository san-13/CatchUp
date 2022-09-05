package com.sv.catchup.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.sv.catchup.R
import com.sv.catchup.data.token
import com.sv.catchup.ui.home.HomeActivity
import com.sv.catchup.ui.login_signup.loginandsignup

const val TAG = "oauth-test"
class OAuthActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    val RC_SIGN_IN: Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            //findNavController().navigate(R.id.action_login_to_home2)
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        val intent = googleSignInClient.signInIntent
        startActivityForResult(intent, RC_SIGN_IN)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    //findNavController().navigate(R.id.action_login_to_home2)
                    val acct: GoogleSignInAccount? =GoogleSignIn.getLastSignedInAccount(this)
                    if(acct!=null) {
                        val tokenFile: SharedPreferences = getSharedPreferences("tokenfile", Context.MODE_PRIVATE)
                        tokenFile.edit().putString("name", acct.displayName.toString()).apply()
                        tokenFile.edit().putString("token",acct.idToken.toString()).apply()
                        tokenFile.edit().putString("image",acct.photoUrl.toString()).apply()
                    }
                    val intent=Intent(this,HomeActivity::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    val toast = Toast.makeText(this,"Authentication Failed: {task.exception?.message}",Toast.LENGTH_SHORT)
                    toast.show()
                    startActivity(Intent(this,loginandsignup::class.java))
                }
            }
    }
}
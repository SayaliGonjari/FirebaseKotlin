package com.project.firebaseemailpassword

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.btn_login
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {


    private lateinit var mAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        btn_login.setOnClickListener(){
            loginUser()
        }

        new_user.setOnClickListener(){
            startActivity(Intent(this,MainActivity::class.java))
        }

    }

    private fun loginUser() {

        if (login_email.text.toString().isEmpty()) {
            email.error = "Please enter email"
            email.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(login_email.text.toString().trim()).matches()) {
            //[a-zA-Z0-9\+\.\_\%\-\+]{1,256}\@[a-zA-Z0-9][a-zA-Z0-9\-]{0,64}(\.[a-zA-Z0-9][a-zA-Z0-9\-]{0,25})+
            // sayali@gmail.com
            email.error = "Please enter valid email id"
            email.requestFocus()
            return
        }
        if (login_pwd.text.toString().isEmpty()) {
            password.error = "Please enter"
            password.requestFocus()
            return
        }

        mAuth.signInWithEmailAndPassword(login_email.text.toString().trim(),login_pwd.text.toString()).addOnCompleteListener {
            task ->
            if(task.isSuccessful){
                Toast.makeText(this,"Login successful",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"Login Unsuccessful",Toast.LENGTH_SHORT).show()
            }
        }

    }
}
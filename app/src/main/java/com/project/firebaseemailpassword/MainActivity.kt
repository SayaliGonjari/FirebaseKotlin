package com.project.firebaseemailpassword

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()


        btn_signup.setOnClickListener(){
            signUpUser()
        }

        btn_login.setOnClickListener(){
            startActivity( Intent(this,LoginActivity::class.java));
        }

    }

    private fun signUpUser() {



        if (email.text.toString().isEmpty()) {
            email.error = "Please enter email"
            email.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString().trim()).matches()) {
            //[a-zA-Z0-9\+\.\_\%\-\+]{1,256}\@[a-zA-Z0-9][a-zA-Z0-9\-]{0,64}(\.[a-zA-Z0-9][a-zA-Z0-9\-]{0,25})+
            // sayali@gmail.com
            email.error = "Please enter valid email id"
            email.requestFocus()
            return
        }
        if (password.text.toString().isEmpty()) {
            password.error = "Please enter"
            password.requestFocus()
            return
        }


        mAuth.createUserWithEmailAndPassword(email.text.toString().trim(),password.text.toString()).addOnCompleteListener {
            task->
            if(task.isSuccessful){
                Toast.makeText(this,"Successful",Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(this,"Unsuccessful",Toast.LENGTH_SHORT).show()
            }
        }

    }
}


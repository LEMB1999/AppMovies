package maniacorp.c17130804.appmovies

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Login : AppCompatActivity(){

    lateinit var boton_login : Button
    lateinit var boton_signup :Button
    lateinit var email : TextInputEditText
    lateinit var password : TextInputEditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

       boton_login = findViewById(R.id.buttonLogin)
       boton_signup = findViewById(R.id.buttonSignUp)

        email = findViewById(R.id.inputCorreo)
        password = findViewById(R.id.inputPassword)



        boton_signup.setOnClickListener {

            var intent = Intent(this,SignUp::class.java)
            startActivity(intent)

        }

        boton_login.setOnClickListener {

            var email_texto = email.text.toString()
            var password_texto = password.text.toString()


            if(email_texto.trim().isEmpty() || password_texto.trim().isEmpty()){
                Toast.makeText(this,"Por favor ingresar la información Solicitada", Toast.LENGTH_LONG).show()
            }else{

                FirebaseAuth.getInstance().signInWithEmailAndPassword(email_texto, password_texto)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            var intent = Intent(this,Inicio::class.java)
                            startActivity(intent)

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(baseContext, "Correo o Contraseña invalida",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
            } // cierre del else

        }

    }

}
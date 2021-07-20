package maniacorp.c17130804.appmovies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUp : AppCompatActivity() {

    lateinit var correo : TextInputEditText
    lateinit var password : TextInputEditText
    lateinit var boton_registro : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up)

        correo = findViewById(R.id.inputCorreo)
        password = findViewById(R.id.inputPassword)

        //estilos  a los layout

        boton_registro = findViewById(R.id.buttonRegistro)


        boton_registro.setOnClickListener {

            var email_texto = correo.text.toString()
            var password_texto = password.text.toString()

            if(email_texto.trim().isEmpty() || password_texto.trim().isEmpty()){
                Toast.makeText(this,"Por favor ingresar la información Solicitada",Toast.LENGTH_LONG).show()
            }else{

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email_texto, password_texto)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            var intent = Intent(this,Inicio::class.java)
                            startActivity(intent)

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(baseContext, "Error al Registrar la Cuenta ",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
            }//cierre del else
        }
    }
}
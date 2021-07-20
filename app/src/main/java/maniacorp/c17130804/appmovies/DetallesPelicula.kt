package maniacorp.c17130804.appmovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetallesPelicula : AppCompatActivity() {

    lateinit var titulo: TextView
    lateinit var descripcion: TextView
    lateinit var fecha: TextView
    lateinit var imagen: ImageView
    private var urlImagenes= "https://image.tmdb.org/t/p/w500"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detalles_pelicula)

        val titulo_Extra = intent.getStringExtra("titulo")
        val fecha_Extra = intent.getStringExtra("fecha")
        val imagen_Extra = intent.getStringExtra("imagen")
        val descripcion_Extra = intent.getStringExtra("descripcion")

        titulo = findViewById(R.id.titulo)
        fecha = findViewById(R.id.fecha)
        descripcion = findViewById(R.id.descripcion)
        imagen = findViewById(R.id.imagen)

        titulo.text = titulo_Extra
        fecha.text = fecha_Extra
        descripcion.text = descripcion_Extra

        Glide.with(this).load(urlImagenes+imagen_Extra).into(imagen)
    }

}
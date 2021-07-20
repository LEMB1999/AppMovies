package maniacorp.c17130804.appmovies


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.GridView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class Inicio : AppCompatActivity() {

    private var url = "https://api.themoviedb.org/3/movie/popular?api_key=4b8e33bd179c8ec35ab5032f86151fa8"
    lateinit var  arregloPeliculas:MutableList<pelicula>
    lateinit var  gridView :GridView
    lateinit var  adaptador:AdaptadorPelicula

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inicio)
        arregloPeliculas = mutableListOf();
        gridView = findViewById(R.id.contenedor)
        adaptador = AdaptadorPelicula(arregloPeliculas,this@Inicio)
        gridView.adapter = adaptador

        gridView.setOnItemClickListener { _, _, position, _->
            var pelicula = arregloPeliculas[position]
            var intent = Intent(this, DetallesPelicula::class.java).apply {
                putExtra("titulo", pelicula.titulo)
                putExtra("descripcion", pelicula.descripcion)
                putExtra("imagen", pelicula.imagen)
                putExtra("fecha", pelicula.year)
            }
            startActivity(intent)
        }
        obtenerInfo()
    }

    fun obtenerInfo(){

        // Instanciar requestQueue
        val queue = Volley.newRequestQueue(this)
        // respuesta de la solicitud
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url,null,
            {
                // Obtener los resultados de la consulta
               var arreglo = it.getJSONArray("results")
               //solo va a mostrar las 9 peliculas mas importantes al inicio
               for (i in 0..8 ){
                   var data = arreglo.getJSONObject(i)
                   var titulo = data.getString("title")
                   var descripcion = data.getString("overview")
                   var imagen = data.getString("poster_path")
                   var year = data.getString("release_date")
                   arregloPeliculas.add(
                       pelicula(titulo, imagen,descripcion,year)
                   )
               }
                adaptador.notifyDataSetChanged()
            },
            {
                Log.i("error", it.toString())
            })
        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)
    }


}
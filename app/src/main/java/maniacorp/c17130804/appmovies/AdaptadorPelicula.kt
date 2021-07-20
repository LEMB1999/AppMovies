package maniacorp.c17130804.appmovies

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class AdaptadorPelicula(var arregloPelicula: MutableList<pelicula>,var contexto:Context): BaseAdapter()
{

    private var urlImagenes= "https://image.tmdb.org/t/p/w500"
    var view:View? = null

    override fun getCount(): Int {
        return arregloPelicula.size
    }

    override fun getItem(position: Int): Any {
        return arregloPelicula[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {


        if(convertView == null){
            var layout = LayoutInflater.from(contexto)
            view = layout.inflate(R.layout.item_pelicula,null)
        }

        var tituloView = view?.findViewById<TextView>(R.id.titulo)
        var imagenView = view?.findViewById<ImageView>(R.id.imagen)
        var yearView = view?.findViewById<TextView>(R.id.year)

        var pelicula:pelicula = getItem(position) as pelicula

        tituloView?.text = pelicula.titulo
        yearView?.text = pelicula.year


        Glide.with(view!!).load(urlImagenes+pelicula.imagen).into(imagenView!!);

        return view!!
    }

}
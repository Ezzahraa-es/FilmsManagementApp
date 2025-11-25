package com.example.filmsmanagement.adapter


import com.example.filmsmanagement.classes.Movie
import android.content.Context
import com.example.filmsmanagement.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
/**
 * Adapter personnalisé pour afficher une liste de films dans un ListView.
 *
 * @param context Le contexte de l'activité ou fragment.
 * @param movies La liste des objets Movie à afficher.
 */
class MovieAdapter(context: Context, movies: ArrayList<Movie>) :
    ArrayAdapter<Movie>(context, 0, movies) {

    /**
     * Crée ou réutilise une vue pour afficher un élément Movie dans la liste.
     *
     * @param position Position de l'élément dans la liste.
     * @param convertView Vue recyclée, si disponible.
     * @param parent Le parent utilisé pour l'inflation.
     * @return La vue affichant un film.
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val movie = getItem(position)
        var view = convertView

        if (view == null) {
            view = LayoutInflater.from(context)
                .inflate(R.layout.item_movie, parent, false)
        }

        val tvTitle = view!!.findViewById<TextView>(R.id.tvItemTitle)
        val tvGenre = view.findViewById<TextView>(R.id.tvItemGenre)

        tvTitle.text = movie?.title
        tvGenre.text = movie?.genre

        return view
    }
}

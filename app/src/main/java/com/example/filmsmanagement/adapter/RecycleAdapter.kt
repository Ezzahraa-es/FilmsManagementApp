package com.example.filmsmanagement.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.filmsmanagement.classes.Movie
import com.example.filmsmanagement.R

/**
 * Adapter RecyclerView très simple pour afficher une liste de films.
 *
 * @param list La liste de films à afficher.
 */
class RecycleAdapter(private val list: ArrayList<Movie>) :
    RecyclerView.Adapter<RecycleAdapter.ViewHolder>() {

    /**
     * ViewHolder représente une seule ligne (item) dans la liste.
     * Il contient simplement deux TextView : titre et genre.
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.txtTitle)
        val genre: TextView = view.findViewById(R.id.txtGenre)
    }

    /**
     * Crée la vue pour un item du RecyclerView (item_movie_recycle.xml).
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie_recycle, parent, false)
        return ViewHolder(view)
    }

    /**
     * Remplit un item avec les données du film (titre + genre).
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = list[position]
        holder.title.text = movie.title
        holder.genre.text = movie.genre
    }

    /**
     * Retourne combien d’éléments il y a dans la liste.
     */
    override fun getItemCount(): Int {
        return list.size
    }
}

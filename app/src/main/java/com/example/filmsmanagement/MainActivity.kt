package com.example.filmsmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import com.example.filmsmanagement.adapter.MovieAdapter
import com.example.filmsmanagement.classes.Movie
import android.widget.*

/**
 * Activity principale de l'application de gestion des films.
 *
 * Affiche une liste de films, permet d'ajouter de nouveaux films,
 * de voir les détails d'un film, et d'accéder aux films favoris.
 */
class MainActivity : AppCompatActivity() {

    /** Bouton pour accéder aux films favoris */
    private lateinit var btnFavorites: Button
    /** ListView pour afficher la liste des films */
    private lateinit var listView: ListView
    /** Bouton pour ajouter un nouveau film */
    private lateinit var btnAddFilm: Button

    /** Liste des films stockés dans l'application */
    private val moviesList = ArrayList<Movie>()
    /** Adaptateur pour gérer l'affichage des films dans la ListView */
    private lateinit var adapter: MovieAdapter

    /**
     * Méthode appelée lors de la création de l'Activity.
     * Elle initialise les vues, configure les boutons et la ListView.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listMovies)
        btnAddFilm = findViewById(R.id.btnAddFilm)
        btnFavorites = findViewById(R.id.btnFavorites)

        adapter = MovieAdapter(this, moviesList)
        listView.adapter = adapter

        btnAddFilm.setOnClickListener {
            val i = Intent(this, FormulaireActivity::class.java)
            startActivityForResult(i, 100)
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            val movie = moviesList[position]

            val i = Intent(this, DetaitsActivity::class.java)
            i.putExtra("movie", movie)
            i.putExtra("position", position)
            startActivityForResult(i, 200)
        }

        btnFavorites.setOnClickListener {
            val favoriteMovies = ArrayList(moviesList.filter { it.favorite })

            if (favoriteMovies.isNotEmpty()) {
                val intent = Intent(this, FavoritesActivity::class.java)
                intent.putExtra("favorites", favoriteMovies as java.io.Serializable)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Aucun film favori", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * Méthode appelée après le retour d'une Activity lancée avec startActivityForResult.
     * Elle traite les résultats pour ajouter un film ou supprimer un film.
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && resultCode == RESULT_OK) {

            val title = data?.getStringExtra("title") ?: ""
            val genre = data?.getStringExtra("genre") ?: ""
            val stars = data?.getIntExtra("stars", 1) ?: 1
            val watched = data?.getBooleanExtra("watched", false) ?: false
            val favorite = data?.getBooleanExtra("favorite", false) ?: false

            moviesList.add(Movie(title, genre, stars, watched, favorite))
            adapter.notifyDataSetChanged()
        }

        if (requestCode == 200 && resultCode == RESULT_OK) {
            val pos = data?.getIntExtra("delete_position", -1)
            if (pos != null && pos >= 0) {
                moviesList.removeAt(pos)
                adapter.notifyDataSetChanged()
            }
        }
    }
}

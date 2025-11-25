package com.example.filmsmanagement

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmsmanagement.classes.Movie
import com.example.filmsmanagement.adapter.RecycleAdapter

/**
 * Activity qui affiche la liste des films favoris.
 *
 * Cette page reçoit une liste de films envoyée depuis une autre Activity
 * grâce à un Intent, puis l'affiche dans un RecyclerView.
 */
class FavoritesActivity : AppCompatActivity() {

    /** Le RecyclerView qui affichera la liste des films */
    private lateinit var recyclerView: RecyclerView
    /** Adaptateur pour gérer l'affichage des films */
    private lateinit var adapter: RecycleAdapter

    /**
     * Méthode appelée lors de la création de l'Activity.
     * Elle configure le RecyclerView et récupère la liste des films favoris depuis l'Intent.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        recyclerView = findViewById(R.id.recyclerFavorites)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val favorites = intent.getSerializableExtra("favorites") as? ArrayList<Movie> ?: arrayListOf()

        adapter = RecycleAdapter(favorites)
        recyclerView.adapter = adapter
    }
}

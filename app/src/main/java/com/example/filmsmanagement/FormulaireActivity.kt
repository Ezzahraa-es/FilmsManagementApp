package com.example.filmsmanagement

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView

/**
 * Activity pour ajouter un nouveau film via un formulaire.
 *
 * Permet à l'utilisateur de saisir le titre du film, sélectionner un genre,
 * indiquer s'il a été regardé, s'il est favori, et donner une note avec des étoiles.
 * Les données sont renvoyées à l'Activity précédente via un Intent.
 */
class FormulaireActivity : AppCompatActivity() {

    /** Champ de texte pour saisir le titre du film */
    private lateinit var etMovieTitle: EditText
    /** Spinner pour sélectionner le genre du film */
    private lateinit var spGenre: Spinner
    /** Switch pour indiquer si le film a été regardé */
    private lateinit var switchWatched: Switch
    /** Case à cocher pour marquer le film comme favori */
    private lateinit var checkFavorite: CheckBox
    /** Bouton pour ajouter le film */
    private lateinit var btnAdd: Button

    /** Liste des ImageView représentant les étoiles pour la note */
    private lateinit var stars: List<ImageView>
    /** Note actuelle du film (de 0 à 5) */
    private var rating = 0

    /**
     * Méthode appelée lors de la création de l'Activity.
     * Elle initialise les vues, configure les étoiles, le spinner et le bouton.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulaire)

        etMovieTitle = findViewById(R.id.etMovieTitle)
        spGenre = findViewById(R.id.spGenre)
        switchWatched = findViewById(R.id.switchWatched)
        checkFavorite = findViewById(R.id.checkFavorite)
        btnAdd = findViewById(R.id.btnAddMovie)

        stars = listOf(
            findViewById(R.id.star1),
            findViewById(R.id.star2),
            findViewById(R.id.star3),
            findViewById(R.id.star4),
            findViewById(R.id.star5)
        )

        updateStars(0)

        for ((index, star) in stars.withIndex()) {
            star.setOnClickListener {
                rating = index + 1
                updateStars(rating)
            }
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.movie_genres,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spGenre.adapter = adapter
        }

        btnAdd.setOnClickListener {
            val title = etMovieTitle.text.toString().trim()
            if (title.isEmpty()) {
                etMovieTitle.error = "Enter movie title"
                return@setOnClickListener
            }

            val genre = spGenre.selectedItem.toString()
            val watched = switchWatched.isChecked
            val favorite = checkFavorite.isChecked

            val intent = Intent()
            intent.putExtra("title", title)
            intent.putExtra("genre", genre)
            intent.putExtra("stars", rating)
            intent.putExtra("watched", watched)
            intent.putExtra("favorite", favorite)

            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    /**
     * Met à jour l'affichage des étoiles en fonction de la note donnée.
     *
     * @param rating La note actuelle (de 0 à 5), où chaque étoile allumée représente une unité.
     */
    private fun updateStars(rating: Int) {
        for ((index, star) in stars.withIndex()) {
            if (index < rating) {
                star.setImageResource(android.R.drawable.btn_star_big_on)
            } else {
                star.setImageResource(android.R.drawable.btn_star_big_off)
            }
        }
    }
}

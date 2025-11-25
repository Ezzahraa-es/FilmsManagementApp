package com.example.filmsmanagement

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.*
import com.example.filmsmanagement.classes.Movie
import android.content.Intent
/**
 * Cette activité affiche les détails d'un film sélectionné
 * et permet de le supprimer.
 */
class DetaitsActivity : AppCompatActivity() {

    /**
     * Méthode appelée lors de la création de l'activité.
     * Elle récupère le film envoyé, affiche ses infos
     * et renvoie la position à supprimer si l'utilisateur clique sur "Delete".
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detaits)

        val tvTitle = findViewById<TextView>(R.id.tvDetailTitle)
        val tvGenre = findViewById<TextView>(R.id.tvDetailGenre)
        val starsLayout = findViewById<LinearLayout>(R.id.starsLayout)
        val tvWatched = findViewById<TextView>(R.id.tvWatchedState)
        val btnDelete = findViewById<Button>(R.id.btnDelete)

        //recupere movie man mainactivity
        val movie = intent.getSerializableExtra("movie") as Movie
        val position = intent.getIntExtra("position", -1)

        tvTitle.text = movie.title
        tvGenre.text = "Genre: ${movie.genre}"

        starsLayout.removeAllViews()
        for (i in 1..5) {
            val star = ImageView(this)
            star.setImageResource(
                if (i <= movie.stars)
                    android.R.drawable.btn_star_big_on
                else
                    android.R.drawable.btn_star_big_off
            )
            star.layoutParams =
                LinearLayout.LayoutParams(90, 90).apply { marginEnd = 8 }
            starsLayout.addView(star)
        }

        tvWatched.text =
            if (movie.watched) "Film already watched"
            else "Film not watched yet"

        btnDelete.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("delete_position", position)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}

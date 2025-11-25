package com.example.filmsmanagement.classes

/**
 * Classe représentant un film.
 *
 * @property title    Le titre du film
 * @property genre    Le genre du film (Action, Drama, etc.)
 * @property stars    Le nombre d'étoiles (1 à 5)
 * @property watched  True si le film est déjà regardé
 * @property favorite True si le film est marqué comme favori
 */
data class Movie(
    val title: String,
    val genre: String,
    val stars: Int,
    val watched: Boolean,
    val favorite: Boolean
) : java.io.Serializable

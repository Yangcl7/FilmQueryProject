package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private final String USER = "student";
	private final String PASSWORD = "student";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;

		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = "select id, title, description, release_year, language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features FROM film where film.id= ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(1);
				String title = rs.getString(2);
				String description = rs.getString(3);
				int releaseYear = rs.getInt(4);
				int languageId = rs.getInt(5);
				int rentalDuration = rs.getInt(6);
				double rentalRate = rs.getDouble(7);
				int length = rs.getInt(8);
				double replacementCost = rs.getDouble(9);
				String rating = rs.getString(10);
				String specialFeatures = rs.getString(11);
				String language = findLanguageByFilmId(id);
				List<Actor> actors = findActorsByFilmId(id) ;
				String category = "CHANGE THIS LATER";
				

				film = new Film(id, title, description, releaseYear, languageId, rentalDuration, rentalRate, length,
						replacementCost, rating, specialFeatures, language, actors, category);

			}
			conn.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;

		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = "SELECT * from actor where id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				actor = new Actor(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"));

			}
			conn.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {

		List<Actor> actorList = new ArrayList<>();

		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = "SELECT a.id, a.first_name, a.last_name FROM actor a JOIN film_actor fa ON a.id = fa.actor_id JOIN film f ON f.id = fa.film_id WHERE f.id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				actorList.add(new Actor(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name")));
			}

			conn.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actorList;
	}

	@Override
	public String findLanguageByFilmId(int filmId) {
		String language = "";

		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = "SELECT l.name FROM film f JOIN language l ON f.language_id = l.id WHERE f.id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				language = rs.getString(1);
			}
			conn.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return language;
	}

	@Override
	public List<Film> findFilmByKeyword(String keyword) {
		List<Film> films = new ArrayList<Film>();
		
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = "SELECT id, title, description, release_year, language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features FROM film WHERE title LIKE ? OR description LIKE ? ";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+ keyword +"%");
			stmt.setString(2, "%"+ keyword +"%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String title = rs.getString(2);
				String description = rs.getString(3);
				int releaseYear = rs.getInt(4);
				int languageId = rs.getInt(5);
				int rentalDuration = rs.getInt(6);
				double rentalRate = rs.getDouble(7);
				int length = rs.getInt(8);
				double replacementCost = rs.getDouble(9);
				String rating = rs.getString(10);
				String specialFeatures = rs.getString(11);
				String language = findLanguageByFilmId(id);
				List<Actor> actors = findActorsByFilmId(id) ;
				String category = "CHANGE THIS LATER";
				

				Film film = new Film(id, title, description, releaseYear, languageId, rentalDuration, rentalRate, length, replacementCost, rating, specialFeatures, language, actors, category);
				films.add(film);

			}

			conn.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return films;
	}

}

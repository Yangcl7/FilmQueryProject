package com.skilldistillery.filmquery.database;

import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public interface DatabaseAccessor {
  public Film findFilmById(int filmId);
  public Actor findActorById(int actorId);
  
//  public List<Film> findFilmByKeyword(String filmKeyword) throws SQLException;
  public List<Actor> findActorsByFilmId(int filmId);
  public String findLanguageByFilmId(int filmId);
  public List<Film> findFilmByKeyword(String keyword);
  
}

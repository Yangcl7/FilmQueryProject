package com.skilldistillery.filmquery.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();
	DatabaseAccessorObject dbo = new DatabaseAccessorObject();
	Scanner input = new Scanner(System.in);
	boolean proceed = false;

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
//    app.test();
//		do {
		app.launch();

//		} while (app.anotherSearch());
//		System.out.println("Thanks for using YaallyWood film studio interface");
//		System.out.println("Have a nice day");
	}

//	private void test() {
//		Film film = db.findFilmById(500);
//		System.out.println(film);
//	}

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

	}

	private void startUserInterface(Scanner input) {

		int select;
		do {
			proceed = true;
			System.out.println("***********************************");
			System.out.println("**            WELCOME            **");
			System.out.println("**     BollyWood Film studio     **");
			System.out.println("**            INTERFACE          **");
			System.out.println("**        would you like to      **");
			System.out.println("**                               **");
			System.out.println("** 1: Find a film by ID?         **");
			System.out.println("** 2: Find an Actor by ID?       **");
			System.out.println("** 3: Find Actors by Film ID?    **");
			System.out.println("** 4: Find Film by keyword?      **");
			System.out.println("**                               **");
			System.out.println("** 0: EXIT                       **");
			System.out.println("**                               **");
			System.out.println("***********************************");
			System.out.println("**Please enter an option:      ");
			select = input.nextInt();

			switch (select) {
			case 1:
				findFilmById();
				proceed = false;
				break;
			case 2:
				findFilmByKeyword();
				proceed = false;
				break;
			case 0:
				System.out.println("Thanks for using BollyWood film studio interface \n GoodBye ");
				System.exit(0);
			default:
				System.out.println("\nThat is not a valid selection, please try again");
				proceed = false;
			}
		} while (proceed == false);

	}

//	public boolean anotherSearch() {
//		Scanner input2 = new Scanner(System.in);
//		String anotherSearch;
//		System.out.println();
//		System.out.println("Would you like another film search?");
//		System.out.println("YES or NO");
//		System.out.println();
//		anotherSearch= input2.next();
//
//		if (anotherSearch.equalsIgnoreCase("y") || anotherSearch.equalsIgnoreCase("yes")) {
//			return true;
//		} else {
//			return false;
//		}
//	}

	private void findFilmById() {
		int select;
		System.out.print("Please enter a film ID: ");
		int filmId = input.nextInt();
		Film film = db.findFilmById(filmId);

		if (film != null) {
			System.out.println(
					"\n\n" + film.getTitle() + "\nYear: " + film.getReleaseYear() + "\nRating: " + film.getRating()
							+ "\nLanguage: " + dbo.findLanguageByFilmId(film.getLanguageId()) + "\nDescription: "
							+ film.getDescription() + "\nActors" + dbo.findActorsByFilmId(film.getFilmId()));

			System.out.println("\nWhat would you like to do next");

			do {
				proceed = true;
				System.out.println("1: Return to main Menu");
				System.out.println("2: Film Details");
				System.out.print("Selection: ");
				select = input.nextInt();

				switch (select) {
				case 1:
					break;
				case 2:
					System.out.println(film);
					break;
				default:
					System.out.println("\nThat is not a valid selection, please try again");
					proceed = false;
				}
			} while (proceed == false);

		} else {
			System.out.println("\nThat is not a valid Film ID");
		}
	}

	private void findFilmByKeyword() {
		String keyword;
		List<Film> films = new ArrayList<>();
		System.out.print("\nPlease enter the keyword you would like to search by: ");
		keyword = input.next();
		films = db.findFilmByKeyword(keyword);
		if (films != null) {
			for (Film film : films) {
				System.out.println(
						"\n\n" + film.getTitle() + "\nYear: " + film.getReleaseYear() + "\nRating: " + film.getRating()
								+ "\nLanguage: " + dbo.findLanguageByFilmId(film.getLanguageId()) + "\nDescription: "
								+ film.getDescription() + "\nActors" + dbo.findActorsByFilmId(film.getFilmId()));
			}
		} else {
			System.out.println("\nNo films were found with that keyword");
		}
	}
}

//		int kb = input.nextInt();
//		int choice;
//		switch (kb) {
//		case 1:
//			System.out.println("Find Film by ID, Enter ID: ");
//			choice = input.nextInt();
//			Film filmDisplay = db.findFilmById(choice);
//			System.out.println(filmDisplay);
//			break;
//		case 2:
//			System.out.println("Find Actor by ID");
//			choice = input.nextInt();
//			Actor actorDisplay = db.findActorById(choice);
//			System.out.println(actorDisplay);
//			break;
//		case 3:
//			System.out.println("Find Actors by Film ID, Enter Film ID: ");
//			choice = input.nextInt();
//			List<Actor> actors = db.findActorsByFilmId(choice);
//			for (Actor actor : actors) {
//				System.out.println(actor);
//			}
//			break;
//		case 4:
//			System.out.println("Please enter a keyword: ");
//			System.out.print("->>;");
//			input.nextLine();
//			String keyWord = input.nextLine();
//			List<Film> films = db.findFilmByKeyword(keyWord);
//			for (Film film : films) {
//			System.out.println(film);
//			break;
//			}
//		case 0:
//			System.out.println("Thanks for using YaallyWood film studio interface");
//			System.out.println("Have a nice day");
//			break;
//		default:
//			break;
//		}
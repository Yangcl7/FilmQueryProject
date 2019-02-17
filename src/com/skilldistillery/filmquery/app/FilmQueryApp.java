package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
//    app.test();
		do {
			app.launch();
		
		} while (app.anotherSearch());
		System.out.println("Thanks for using YaallyWood film studio interface");
		System.out.println("Have a nice day");
	}

//	private void test() {
//		Film film = db.findFilmById(500);
//		System.out.println(film);
//	}

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		int kb = input.nextInt();
		int choice;
		switch (kb) {
		case 1:
			System.out.println("Find Film by ID, Enter ID: ");
			choice = input.nextInt();
			Film filmDisplay = db.findFilmById(choice);
			System.out.println(filmDisplay);
			break;
		case 2:
			System.out.println("Find Actor by ID");
			choice = input.nextInt();
			Actor actorDisplay = db.findActorById(choice);
			System.out.println(actorDisplay);
			break;
		case 3:
			System.out.println("FInd Actors by Film ID, Enter Film ID: ");
			choice = input.nextInt();
			List<Actor> actors = db.findActorsByFilmId(choice);
			for (Actor actor : actors) {
				System.out.println(actor);
			}
			break;
		case 4:
			System.out.println("Please enter a keyword: ");
			input.nextLine();
			String keyWord = input.nextLine();
			List<Film> films = db.findFilmByKeyword(keyWord);
			for (Film film : films) {
				System.out.println(film);
				break;
			}
		case 0:
			System.out.println("Thanks for using YaallyWood film studio interface");
			System.out.println("Have a nice day");
			break;
		default:
			System.out.println("EXIT");
			break;
		}

		input.close();
	}

	private void startUserInterface(Scanner input) {
		System.out.println("***********************************");
		System.out.println("**            WELCOME            **");
		System.out.println("**    YoollyWood Film studio     **");
		System.out.println("**            INTERFACE          **");
		System.out.println("**        would you like to      **");
		System.out.println("**                               **");
		System.out.println("** 1: Find a film by ID?         **");
		System.out.println("** 2: Find an Actor by ID?       **");
		System.out.println("** 3: Find Actors by Film ID?    **");
		System.out.println("** 4: Find Film by keyword?      **");
		System.out.println("**                               **");
		System.out.println("** 0: Exit the app.              **");
		System.out.println("***********************************");
	}

	private boolean anotherSearch() {
		Scanner input2 = new Scanner(System.in);
		System.out.println("Would you like another film search?");
		System.out.println("YES or NO");
		String as = input2.next();

		if (as.equalsIgnoreCase("y") || as.equalsIgnoreCase("yes")) {
			return true;
		}else {
			return false;
		}

	}
}

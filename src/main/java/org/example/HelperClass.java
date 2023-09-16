package org.example;

import java.util.Scanner;

import static org.example.Main.*;

public class HelperClass {

	public static User authorization() {

		while (true) {

			System.out.print("Введите логин пользователя: ");
			Scanner scanner = new Scanner(System.in);
			String name = "";
			try {
				name = scanner.nextLine();
			} catch (Exception e) {
				System.err.println("Допущена ошибка");
			}

			for (int i = 0; i < userList.size(); i++) {

				if (userList.get(i).name.equals(name)) {

					return userList.get(i);

				}

			}

			System.out.println("Пользователь не найден");

		}

	}


	public static StringBuffer readAccess(int elem) {

		StringBuffer str = new StringBuffer();
		str.append(Integer.toBinaryString(elem));
		if(str.length() < 3){
			for(int i = str.length(); i  < 3; i++){
				str.insert(0, 0);
			}

		}
		return str;
	}

	public static void binToNat(int[][] matrix, StringBuffer str, int indexUser, int indexObj){

		int elem = Integer.parseInt(str.toString(), 2);

		matrix[indexUser][indexObj] = elem;

	}




	public static User readAnswer(User actualUser, int userIndex, int[][] matrix){

		Scanner scanner = new Scanner(System.in);
		System.out.println("Выберите объект: ");
		int objIndex = scanner.nextInt();

		if(objIndex == 4){
			actualUser = null;
			return actualUser;
		}

		StringBuffer str = readAccess(matrix[userIndex][objIndex]);

		System.out.println("Доступные действия: ");
		if(str.charAt(0) == '1'){
			System.out.println("0) Чтение");
		}
		if(str.charAt(1) == '1'){
			System.out.println("1) Запись");
		}
		if(str.charAt(2) == '1'){
			System.out.println("2) Передача прав");
		}

		scanner = new Scanner(System.in);
		System.out.print("Выберите действие: ");
		int result = scanner.nextInt();
		switch (result){
			case 0:
				if(str.charAt(0) == '1') {
					readObj(objIndex);
				} else {
					System.out.println("У вас нет таких прав");
				}
				break;
			case 1:
				if(str.charAt(1) == '1') {
					writeObj(objIndex);
				} else {
					System.out.println("У вас нет таких прав");
				}
				break;
			case 2:
				if(str.charAt(2) == '1') {
					transAccess(str, objIndex, matrix, actualUser);
				} else {
					System.out.println("У вас нет таких прав");
				}
				break;
		}
		return actualUser;
	}

	public static void readObj(int objIndex){
		for(int i : objList.get(objIndex).file){
			System.out.print(i + " ");
		}
		System.out.println();
	}

	public static void writeObj(int objIndex){

		Scanner scanner = new Scanner(System.in);
		System.out.print("Введите новый элемент: ");
		objList.get(objIndex).file.add(scanner.nextInt());

	}




	public static void transAccess(StringBuffer firstAccess, int indexObj, int[][] matrix, User actualUser){

		User secondUser = authorization();
		StringBuffer secondAccess = readAccess(matrix[secondUser.index][indexObj]);

		System.out.print("Выберите какое право передать: ");

		boolean check = false;
		System.out.println();
		if(firstAccess.charAt(0) == '1' && secondAccess.charAt(0) == '0'){
			System.out.println("0) Чтение");
			check = true;
		}
		if(firstAccess.charAt(1) == '1' && secondAccess.charAt(1) == '0'){
			System.out.println("1) Запись");
			check = true;
		}
		if(!check){
			System.out.println("Нет доступных действий");
		}

		Scanner scanner = new Scanner(System.in);


		switch (scanner.nextInt()){
			case 0:
				secondAccess.replace(0, 1,"1");
				break;
			case 1:
				secondAccess.replace(1, 2, "1");
				break;
		}

		binToNat(matrix, secondAccess, secondUser.index, indexObj);

	}




	public static void printAccess(int index, int[][] matrix) {

		int[] mas = matrix[index];

		for (int i = 0; i < mas.length; i++) {

			System.out.print(i + " объект: ");

			switch (mas[i]) {

				case (0):
					System.out.print("Полный запрет");
					break;
				case (1):
					System.out.print("Полный запрет");
					break;
				case (2):
					System.out.print("Запись");
					break;
				case (3):
					System.out.print("Запись, передача");
					break;
				case (4):
					System.out.print("Чтение");
					break;
				case (5):
					System.out.print("Чтение, передача");
					break;
				case (6):
					System.out.print("Чтение, запись");
					break;
				case (7):
					System.out.print("Полный доступ");
					break;
			}
			System.out.println();
		}
		System.out.println("4) Выйти из аккаунта");
	}

}

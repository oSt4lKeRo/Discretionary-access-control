package org.example;


import java.util.ArrayList;

import static org.example.HelperClass.*;

public class Main{


	static class User{

		String name;
		int index;

		public User(String name, int index){
			this.name = name;
			this.index = index;
		}

		public User(){}

	}

	static class Obj{

		ArrayList<Integer> file = new ArrayList<>();
		int index;

		public Obj(int index){
			for(int i = 0; i < 5; i++){
				file.add(i);
			}
			this.index = index;
		}


	}

	public static ArrayList<User> userList = new ArrayList<>();
	public static ArrayList<Obj> objList = new ArrayList<>();

	public static void main(String[] args) {

		//////////////////////////////////////////////////////Подготовка
		int[][] matrix = {{1, 3, 5}, {2, 3, 7}, {6, 3, 5}};

		User dima = new User("dima", 0);
		User bob = new User("bob", 1);
		User oleg = new User("oleg", 2);
		userList.add(dima);
		userList.add(bob);
		userList.add(oleg);
		Obj obj = new Obj(0);
		Obj obj1 = new Obj(1);
		Obj obj2 = new Obj(2);
		objList.add(obj);
		objList.add(obj1);
		objList.add(obj2);


		//////////////////////////////////////////////////////////////Начало алгоритма
		int status = 0;
		while(true) {

			User activeUser = new User();

			if (status == 0) {
				activeUser = authorization();
				status = 1;
				System.out.println("Вы зашли за пользователя: " + activeUser.name);
			}

			if(status == 1) {
				printAccess(activeUser.index, matrix);
				activeUser = readAnswer(activeUser, activeUser.index, matrix);
				if(activeUser == null){
					status = 0;
				}

			}
		}

//		StringBuffer binaryElem = readAccess(mas[i]);

	}
}
package vttp.batch5.sdf.task02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {

	static char player = 'X', opponent = 'O';

	public static void main(String[] args) throws Exception {

		if (args.length == 0) {
			System.out.println("No board config file is provided");
			return;
		} else if (args.length > 1) {
			System.out.println("Too many arguments");
			return;
		}
		String boardName = args[0];
		File file = new File(boardName);
		if (!file.exists()) {
			System.out.println("File doesn't exist in directory");
		} else {

			FileReader reader = new FileReader(file);
			BufferedReader br = new BufferedReader(reader);
			char[][] board = new char[3][3]; //board size
			ArrayList<String> legalMoves = new ArrayList<>(); //to store legal positions and utility
			String line = "";
			for (int i = 0; i < 3; i++) {
				line = br.readLine();
				for (int j = 0; j < 3; j++) {
					board[i][j] = line.charAt(j);
				}
			}
			int point = 0;
			String lineToAdd = "";
			for (int x = 0; x < 3; x++) {
				for (int y = 0; y < 3; y++) {
					if (board[x][y] == '.') {
						board[x][y] = player;
						point = checkWin(board);
						if (point == 1) {
							lineToAdd = "y = " + y + ", x = " + x + ", ulility = " + point;
							if (!legalMoves.contains(lineToAdd)){ //prevents duplicates
								legalMoves.add(lineToAdd);
							}
						} else {
							boolean cpuWin = false; 
							for (int cx = 0; cx < 3; cx++) {
								for (int cy = 0; cy < 3; cy++) {
									if (board[cx][cy] == '.') {
										board[cx][cy] = opponent;
										point = checkWin(board);
										if (point == -1) {
											lineToAdd = "y = " + y + ", x = " + x + ", ulility = " + point;
											if (!legalMoves.contains(lineToAdd)){
												legalMoves.add(lineToAdd);
											}
											cpuWin = true;
										}
										board[cx][cy] = '.';
									}
								}
							}
							if (cpuWin == false) { //tie game
								lineToAdd = "y = " + y + ", x = " + x + ", ulility = " + 0;
								if (!legalMoves.contains(lineToAdd)){
									legalMoves.add(lineToAdd);
								}
							}
						}
						board[x][y] = '.';
					}
				}
			}
			br.close();
			System.out.println("Processing: " + boardName); // show txt file
			printBoard(board); //output board
			for (String item : legalMoves) { //output all legal moves
				System.out.println(item);
			}
		}

	}

	public static void printBoard(char[][] board) {
		System.out.println();
		System.out.println("Board: ");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("-----------------------------");
	}

	public static int checkWin(char[][] board) {
		{
			// Checking for Rows for X or O victory.
			for (int row = 0; row < 3; row++) {
				if (board[row][0] == board[row][1] &&
						board[row][1] == board[row][2]) {
					if (board[row][0] == player) {
						return 1;
					} else if (board[row][0] == opponent) {
						return -1;
					}
				}
			}
			// Checking for Columns for X or O victory.
			for (int col = 0; col < 3; col++) {
				if (board[0][col] == board[1][col] &&
						board[1][col] == board[2][col]) {
					if (board[0][col] == player) {
						return 1;
					} else if (board[0][col] == opponent) {
						return -1;
					}
				}
			}
			// Checking for Diagonals for X or O victory.
			if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
				if (board[0][0] == player) {
					return 1;
				} else if (board[0][0] == opponent) {
					return -1;
				}
			}

			if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
				if (board[0][2] == player) {
					return 1;
				} else if (board[0][2] == opponent) {
					return -1;
				}
			}
			// Else if none of them have won then return 0
			return 0;
		}

	}
}

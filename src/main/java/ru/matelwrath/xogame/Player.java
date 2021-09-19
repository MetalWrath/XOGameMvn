package main.java.ru.matelwrath.xogame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Player {
    private String name;
    private int score;
    private SignXO signXO;
//Constructors

    public Player() {
        this.score = 0;
    }
// Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public void setSignXO(SignXO signXO) {
        this.signXO = signXO;
    }
    //Methods
    public String doMoveStr(){
        String playerMove = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            while(true){
                playerMove = br.readLine();
                if ((playerMove.equalsIgnoreCase("A1")) || (playerMove.equalsIgnoreCase("A2")) ||
                        (playerMove.equalsIgnoreCase("A3")) || (playerMove.equalsIgnoreCase("B1")) ||
                        (playerMove.equalsIgnoreCase("B2")) || (playerMove.equalsIgnoreCase("B3")) ||
                        (playerMove.equalsIgnoreCase("C1")) || (playerMove.equalsIgnoreCase("C2")) ||
                        (playerMove.equalsIgnoreCase("C3"))){
                    break;
                }else {
                    System.out.println("Введите свой ход в формате буква-цифра(слитно) в английской раскладке." +
                            " Например \"А2\"");
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return playerMove;

    }
    public void move(GameFieldObj[][] gameFieldObjs) {
        System.out.println("Ход игрока " + getName());
        String move;
        while(true){
            move = doMoveStr();
        int tempX = 99;
        int tempY = 99;
        char[] tempCharArray = move.toCharArray();
            switch (tempCharArray[0]) {
                case 'a' -> tempX = 1;
                case 'b' -> tempX = 2;
                case 'c' -> tempX = 3;
                default -> System.out.println("Что-то пошло не так. Звони маме!");
            }

            switch (tempCharArray[1]) {
                case '1' -> tempY = 1;
                case '2' -> tempY = 2;
                case '3' -> tempY = 3;
                default -> System.out.println("Что-то пошло не так. Звони маме2!");
            }
        if (gameFieldObjs[tempX][tempY].isUsed) {
            System.out.println("В эту клетку уже ходили, веберите другую клетку!");
            continue;
        }


            if (signXO.equals(SignXO.X)) {
                gameFieldObjs[tempX][tempY].value = " " + SignXO.X + " ";
                gameFieldObjs[tempX][tempY].isUsed = true;
                gameFieldObjs[tempX][tempY].signX = true;


            } else {
                gameFieldObjs[tempX][tempY].value = " " + SignXO.O + " ";
                gameFieldObjs[tempX][tempY].isUsed = true;
                gameFieldObjs[tempX][tempY].signO = true;
            }
            break;
        }
    }
}

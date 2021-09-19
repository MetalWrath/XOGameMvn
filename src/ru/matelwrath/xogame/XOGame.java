package ru.matelwrath.xogame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class XOGame {
    static int size = 4;
    GameFieldObj[][] gameField = new GameFieldObj[size][size];
    int step = 0;
    Player p1, p2;

    Map<String, Integer> scoreTable = new HashMap<>();

    public void startGame(){
        p1 = new Player();
        p2 = new Player();
        initField();
        initPlayer(p1,p2);
        int finRes, finRes2;
        for (int i = 0; i < 3; i++){
            startPlayerMove();
            initField();
        }
        try {
             finRes = scoreTable.get(p1.getName()) + p1.getScore();
             scoreTable.put(p1.getName(), finRes);
        }catch (NullPointerException e){
            scoreTable.put(p1.getName(), p1.getScore());
        }
        try{
            finRes2 = scoreTable.get(p2.getName()) + p2.getScore();
            scoreTable.put(p2.getName(), finRes2);
        }catch (NullPointerException e){
            scoreTable.put(p2.getName(), p2.getScore());
        }



        System.out.println(p1.getName() + ": " + p1.getScore());
        System.out.println(p2.getName() + ": " + p2.getScore());

        mainMenu();


    }

    protected void initField() {
        System.out.println("Приступаем к инициализации поля.");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                gameField[i][j] = new GameFieldObj();
            }
        }
        gameField[0][0].value = "   ";
        gameField[0][1].value = " 1 ";
        gameField[0][2].value = " 2 ";
        gameField[0][3].value = " 3 ";
        gameField[1][0].value = " A ";
        gameField[2][0].value = " B ";
        gameField[3][0].value = " C ";
        System.out.println("Поле готово!");
    } // OK

    protected void showGameField() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(gameField[i][j].value);
            }
            System.out.println();
        }
    } // OK

    protected boolean isWin() {
        if ((gameField[1][1].signO) && (gameField[1][2].signO) && (gameField[1][3].signO)) {
            return true;
        }
        if ((gameField[1][1].signX) && (gameField[1][2].signX) && (gameField[1][3].signX)) {
            return true;
        }

        if ((gameField[2][1].signO) && (gameField[2][2].signO) && (gameField[2][3].signO)) {
            return true;
        }
        if ((gameField[2][1].signX) && (gameField[2][2].signX) && (gameField[2][3].signX)) {
            return true;
        }


        if ((gameField[3][1].signO) && (gameField[3][2].signO) && (gameField[3][3].signO)) {
            return true;
        }
        if ((gameField[3][1].signX) && (gameField[3][2].signX) && (gameField[3][3].signX)) {
            return true;
        }


        if ((gameField[1][1].signO) && (gameField[2][1].signO) && (gameField[3][1].signO)) {
            return true;
        }
        if ((gameField[1][1].signX) && (gameField[2][1].signX) && (gameField[3][1].signX)) {
            return true;
        }


        if ((gameField[1][2].signO) && (gameField[2][2].signO) && (gameField[3][2].signO)) {
            return true;
        }
        if ((gameField[1][2].signX) && (gameField[2][2].signX) && (gameField[3][2].signX)) {
            return true;
        }

        if ((gameField[1][3].signO) && (gameField[2][3].signO) && (gameField[3][3].signO)) {
            return true;
        }
        if ((gameField[1][3].signX) && (gameField[2][3].signX) && (gameField[3][3].signX)) {
            return true;
        }


        //--------
        if ((gameField[1][1].signO) && (gameField[2][2].signO) && ((gameField[3][3]).signO)) {
            return true;
        }
        if ((gameField[1][1].signX) && (gameField[2][2].signX) && ((gameField[3][3]).signX)) {
            return true;
        }


        if ((gameField[3][1].signO) && (gameField[2][2].signO) && (gameField[1][3].signO)) {
            return true;
        }

        return (gameField[3][1].signX) && (gameField[2][2].signX) && (gameField[1][3].signX);
    } // OK

    protected void initPlayer(Player player1, Player player2){
        System.out.println("Начинается инициализация игроков!");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите имя первого игрока:");
        String name = "";
        try {
            name = br.readLine();
        }catch (IOException e){
            e.printStackTrace();
        }
        player1.setName(name);
        player1.setSignXO(SignXO.X);
        System.out.print("Введите имя второго игрока:");
        try {
            name = br.readLine();
        }catch (IOException e){
            e.printStackTrace();
        }
        player2.setName(name);
        player2.setSignXO(SignXO.O);
        System.out.println("Инициализация закончена успешно! Игроки созданы!");
    }

    protected boolean checkWin(Player pl){
        if (isWin()){
            System.out.println(pl.getName() + " победил! Ты просто космос!");
            pl.setScore(pl.getScore() + 1);
            return true;
        }else if(step>=9){
            System.out.println("Ничья!");
            return true;
        }
        return false;
    }


    protected void startPlayerMove(){

        showGameField();

        while (true){
            p1.move(gameField);
            step++;
            showGameField();
            if(checkWin(p1)){
                step = 0;
                break;
            }

            p2.move(gameField);
            step++;
            showGameField();
            if (checkWin(p2)){
                step = 0;
                break;
            }

        }
    }

    protected void mainMenu(){
        int changer;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Добро пожаловать в игру крестики-нолики.");
        System.out.println("Веберите нужный пункт меню: ");
        System.out.println("1 - Игра.");
        System.out.println("2 - Результаты.");
        System.out.println("3 - Выход.");
        System.out.println();

        while(true){
            try{
                changer = Integer.parseInt(br.readLine());
            }catch (Exception e){
                System.out.println("Введен недопустимый символ.");
                continue;
            }
            switch (changer) {
                case 1 -> startGame();
                case 2 -> showScores();
                case 3 -> XOGameMain.exit();
                default -> System.out.println("Такого пункта нет в меню!");
            }
        }
    }

    protected void showScores(){
        System.out.println(scoreTable);
        mainMenu();
    }

}

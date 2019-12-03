import java.util.*;
class TicTacToe {
    public static void main(String[] args) {
        String playera = "X", playerb = "O";
        Scanner scam = new Scanner(System.in);
        //Added Comment for Lab 11
        int woot;
        String cha = "Y";
        while(cha.equals("Y") || cha.equals("y")) {
            woot = 0 ;
            String[] board = new String[]{"0","1","2","3","4","5","6","7","8","9"};
            System.out.println("Player 1 is: " + playera + ". Player two is: " + playerb + ".");
            System.out.print(board[1]);
            System.out.print(board[2]);
            System.out.println(board[3]);
            System.out.print(board[4]);
            System.out.print(board[5]);
            System.out.println(board[6]);
            System.out.print(board[7]);
            System.out.print(board[8]);
            System.out.println(board[9]);
            if (board[1] == board[2] == board[3])
            while (woot <= 3) {
                woot++;
                System.out.println("Player 1, Choose a space to go!");
                cha = scam.nextLine();
                board[Integer.parseInt(cha)] = playera;
                System.out.print(board[1] + " ");
                System.out.print(board[2] + " ");
                System.out.println(board[3]);
                System.out.print(board[4] + " ");
                System.out.print(board[5] + " ");
                System.out.println(board[6]);
                System.out.print(board[7] + " ");
                System.out.print(board[8] + " ");
                System.out.println(board[9]);
                System.out.println("Player 2, Choose a space to go!");
                cha = scam.nextLine();
                board[Integer.parseInt(cha)] = playerb;
                System.out.print(board[1] + " ");
                System.out.print(board[2] + " ");
                System.out.println(board[3]);
                System.out.print(board[4] + " ");
                System.out.print(board[5] + " ");
                System.out.println(board[6]);
                System.out.print(board[7] + " ");
                System.out.print(board[8] + " ");
                System.out.println(board[9]);
            }
            System.out.println("Play Again? (Y/N)");
            cha = scam.nextLine();
        }
        System.out.println("Thanks for Playing!");


    }
}
//Added Comment for Lab 11
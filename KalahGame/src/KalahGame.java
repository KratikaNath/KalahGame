import java.util.Random;
import java.util.Scanner;
/*//////////////////////////////////////////////////////////////////
 * NAME: Kratika Gupta
 * DATE: 7/30/2016
 * -----------------------------------------------------------------------------------------
 * Kalah Game:
 * 
 * Objective: The objective of the game is to collect as many playing pieces as possible
 * before one of the players clears their side of all the playing pieces. 
 * 
 * RULES:
 * 1) Start by placing six stones in each small cup.
 * 2) Your mancala is the big basin 6 and 13 according to our program.
 * 3) Choose which player is going to go first.
 * 4) Going counter-clockwise, the beginning player takes all six stones in one cup on their 
 * 	  side and places one stone each in any four adjacent cups.
 * 5) Players can put stones in their own Mancala, but not in their opponent's Mancala.
 * 6) Take turns picking up stones in any cup and placing them,
 * 7) If your last stone falls into your Mancala, take another turn.
 * 8) If the last stone you drop is in an empty cup on your side, capture that piece along with 
 * 	  any pieces in the hole directly opposite.
 * 9) When one player's six cups are completely empty, the game ends. The player who still has stones 
 * 	  left in their cups captures those stones and puts them in their Mancala. 
 *    Players compare the number of stones in their Mancala. The player with the most stones wins.
 *    
 *        12 11 10 9 8 7
 *     13                6        
 *        0  1  2  3 4 5 
 *    Pit 6 and 13 are Mancala's
 *    
 *    ------------------------------------------------------------------------------------
 *    Name							Data type					Description
 *    player1						boolean						To check the turns of both the players
 *    arr							int							contains the value of each pit
 *    pitA							int							Player 1 chosen pit
 *    pitB							int							Player 2 chosen pit
 *    Player						object						to take user inputs
 *    k,i,j							int							to run the loops
 *    len							int 						contains the value of the chosen pit
 *    lastPit						int							contains the pit number of the last stone
 *    player1Total					int							stores the total number of stones player 1 has.
 *    player2Total					int							stores the total number of stones player 2 has.
 *    
 */
public class KalahGame {

	public static void main(String[] args) {
		boolean player1 = true;
		Scanner Player = new Scanner(System.in);
		int arr[] = {6,6,6,6,6,6,0,6,6,6,6,6,6,0};
		System.out.println("Who wants to play first? Write either TRUE or FALSE. TRUE = Player 1 and FALSE = Player 2");
		player1 = Player.nextBoolean(); 
		System.out.println("the value of pits are: ");
		System.out.print("Pits:   ");
		for(int k = 0; k<=13;k++){
			System.out.print(" "+k+" ");
		}
		System.out.println();
		System.out.print("Values: ");
		for(int j=0;j<=13;j++){
			System.out.print(" "+arr[j]+" ");
		}
		System.out.println();
		start(Player,arr,player1);

	}

	private static void start(Scanner Player, int[] arr, boolean player1) { //Method for start the game 
		int pitA;
		int pitB;
		if(player1 == true){
			System.out.println("Player-1: Choose the pit between 0 and 5");
			pitA = Player.nextInt();
			while(pitA <0 || pitA> 5 || arr[pitA]==0){
				System.out.println("Please enter the value between 0 and 5 and from non-empty pit");
				pitA = Player.nextInt();
			}
			System.out.println();
			System.out.println("The Pit chosen by Player-1 is: "+ pitA);
			System.out.println();
			nextMove(Player, player1,pitA,arr);
		}
		else{
			System.out.println("Player-2: Choose the pit between 7 and 12");
			pitB = Player.nextInt();
			while(pitB < 7 || pitB > 12 || arr[pitB]==0){
				System.out.println("Please enter the value between 7 and 13 and not an empty pit");
				pitB = Player.nextInt();
			}
			System.out.println();
			System.out.println("The Pit chosen by Player-2 is: "+ pitB);
			System.out.println();
			nextMove(Player, player1,pitB,arr);
		}
	}

	private static void nextMove(Scanner Player, boolean player1, int pit,int[] arr) { //Method for moves of player 1 & player 2
		int len;
		len = arr[pit];
		arr[pit]=0;
		int lastPit =0;
		for(int i = pit+1; len>0; len --){
			if(player1 == true && i==13){
				i++;
			}
			if(player1 == false && i==6){
				i++;
			}
			if(i==14){
				i=0;
			}
			arr[i]++;
			i++;
			
			if(len == 1){
				lastPit = i-1;
			}
		}
		nextPlayer(Player, lastPit, player1, arr);
		
	}

	private static void nextPlayer(Scanner Player, int lastPit, boolean player1, int[] arr) { // Method to decide the next player
		
	if(player1 == true ){
		if(lastPit == 6){
			player1 = true;
		}
		else if((lastPit >= 0 && lastPit <= 5)&&(arr[lastPit]==1)){
			arr[6] += arr[12-lastPit];
			arr[6] += arr[lastPit];//now
			arr[lastPit] = 0;//now
			arr[12-lastPit] = 0;
			player1 = false;
		}
		else{
			player1 = false;
		}
	}
	else{
		if(lastPit == 13){
			player1 = false;
		}
		else if((lastPit >= 7 && lastPit <= 12)&&(arr[lastPit]==1)){
			arr[13] += arr[12-lastPit];
			arr[13] += arr[lastPit];//now
			arr[lastPit] = 0;//now
			arr[12-lastPit] = 0;
			player1 = true;
		}
		else{
			player1 =true;
		}
	}
	System.out.println("the value of pits are: ");
	System.out.print("Pits:  ");
	for(int k = 0; k<=13;k++){
		System.out.print(" "+k+" ");
	}
	System.out.println();
	System.out.print("Values:");
	for(int j=0;j<=13;j++){
		System.out.print(" "+arr[j]+" ");
	}
	System.out.println();
	System.out.println();
	finish(Player, player1, arr);
	}

	private static void finish(Scanner Player, boolean player1, int[] arr) {// Method for deciding end of the game
		int totalPit=0;
		if(player1==true){
			
			for(int i = 0; i<6; i++){
				totalPit += arr[i];
			}
			if(totalPit == 0){
				System.out.println("Game Over!!");
				wins(arr);
			}
			else{
				start(Player,arr,player1);
			}
		}else{
			for(int i = 7; i<13; i++){
				totalPit += arr[i];
			}
			if(totalPit == 0){
				System.out.println("Game Over!!");
				wins(arr);
			}else{
				start(Player,arr,player1);
			}
		}
		
		
	}

	private static void wins(int[] arr) {// Method to decide the winner of the game.
		int player1Total=0;
		int player2Total=0;
		for(int k=0; k<=6;k++){
			player1Total += arr[k];
		}
		for(int k = 7;k <=13;k++){
			player2Total += arr[k];
		}
		if(player1Total > player2Total){
			System.out.println("Player-1 wins the game with score of: "+ player1Total);
		}else if(player1Total == player2Total){
			System.out.println("The game is tied!");
		}
		else{
			System.out.println("Player-2 wins the game with the score of: "+ player2Total);
		}
		
	}

}

package trivia;

import java.util.ArrayList;
import java.util.LinkedList;

// REFACTOR ME
public class Game implements IGame {
   ArrayList<Player> players = new ArrayList();
   LinkedList popQuestions = new LinkedList();
   LinkedList scienceQuestions = new LinkedList();
   LinkedList sportsQuestions = new LinkedList();
   LinkedList rockQuestions = new LinkedList();

   int currentPlayer = 0;
   boolean isGettingOutOfPenaltyBox;

   public Game() {
      for (int i = 0; i < 50; i++) {
         popQuestions.addLast("Pop Question " + i);
         scienceQuestions.addLast(("Science Question " + i));
         sportsQuestions.addLast(("Sports Question " + i));
         rockQuestions.addLast(createRockQuestion(i));
      }
   }

   public String createRockQuestion(int index) {
      return "Rock Question " + index;
   }

   public boolean isPlayable() {
      return (howManyPlayers() >= 2);
   }

   public boolean add(String playerName) {
      Player p = new Player(playerName,1,0);
      players.add(p);

      System.out.println(playerName + " was added");
      System.out.println("They are player number " + players.size());
      return true;
   }

   public int howManyPlayers() {
      return players.size();
   }

   public void roll(int roll) {
      System.out.println(players.get(currentPlayer).name + " is the current player");
      Player player = players.get(currentPlayer);
      System.out.println("They have rolled a " + roll);

      if (player.inPenalty) {
         if (roll % 2 != 0) {
            isGettingOutOfPenaltyBox = true;
            System.out.println(players.get(currentPlayer).name + " is getting out of the penalty box");
         } else {
            System.out.println(players.get(currentPlayer).name + " is not getting out of the penalty box");
            isGettingOutOfPenaltyBox = false;
         }
      
      } 
      if(!player.inPenalty || isGettingOutOfPenaltyBox) {
         player.place = player.place + roll;
         if (player.place > 12) player.place -=12;

         System.out.println(players.get(currentPlayer).name
                            + "'s new location is "
                            + player.place);
         System.out.println("The category is " + currentCategory());
         askQuestion();
      }

   }

   private void askQuestion() {
      if (currentCategory() == "Pop")
         System.out.println(popQuestions.removeFirst());
      if (currentCategory() == "Science")
         System.out.println(scienceQuestions.removeFirst());
      if (currentCategory() == "Sports")
         System.out.println(sportsQuestions.removeFirst());
      if (currentCategory() == "Rock")
         System.out.println(rockQuestions.removeFirst());
   }


   private String currentCategory() {
      Player player = players.get(currentPlayer);
      if (player.place - 1 == 0) return "Pop";
      if (player.place - 1 == 4) return "Pop";
      if (player.place - 1 == 8) return "Pop";
      if (player.place - 1 == 1) return "Science";
      if (player.place - 1 == 5) return "Science";
      if (player.place - 1 == 9) return "Science";
      if (player.place - 1 == 2) return "Sports";
      if (player.place - 1 == 6) return "Sports";
      if (player.place - 1 == 10) return "Sports";
      return "Rock";
   }

   public boolean handleCorrectAnswer() {
      Player player = players.get(currentPlayer);
      if (player.inPenalty) {
         if (isGettingOutOfPenaltyBox) {
            System.out.println("Answer was correct!!!!");
            player.purse++;
            System.out.println(player.name
                               + " now has "
                               + player.purse
                               + " Gold Coins.");

            boolean winner = didPlayerWin();
            currentPlayer++;
            if (currentPlayer == players.size()) currentPlayer = 0;

            return winner;
         } else {
            currentPlayer++;
            if (currentPlayer == players.size()) currentPlayer = 0;
            return true;
         }


      } else {

         System.out.println("Answer was corrent!!!!");
         player.purse++;
         System.out.println(player.name
                            + " now has "
                            + player.purse
                            + " Gold Coins.");

         boolean winner = didPlayerWin();
         currentPlayer++;
         if (currentPlayer == players.size()) currentPlayer = 0;

         return winner;
      }
   }

   public boolean wrongAnswer() {
      System.out.println("Question was incorrectly answered");
      System.out.println(players.get(currentPlayer).name + " was sent to the penalty box");
      Player player = players.get(currentPlayer);
      player.inPenalty = true;

      currentPlayer++;
      if (currentPlayer == players.size()) currentPlayer = 0;
      return true;
   }


   private boolean didPlayerWin() {
      return !(players.get(currentPlayer).purse == 6);
   }
}



import java.util.LinkedList;
import java.util.Scanner;


class Map<K extends Comparable<K>, E>{
    public K key;
    public E value;

    public Map(K key, E value){
        this.key = key;
        this.value = value;
    }
}

class SLLNode<E> {
    public E info;
    public SLLNode<E> next;
    public SLLNode(E info, SLLNode<E> next){
        this.info = info;
        this.next = next;
    }
}

class SLLHT<K extends Comparable<K>, E>{
    private SLLNode<Map<K,E>>[] htable;
    public SLLHT(int n){
        htable = new SLLNode[n];
        for(int i=0;i<n;i++){
            htable[i] = null; 
        }
    }
    //hesh funkcija koja sekogas bi trebalo da e private
    private int hash(K key){
        return (Integer)key % htable.length;
    }

    public SLLNode<Map<K,E>> find(K look){
        int h = hash(look);
        
        for(SLLNode<Map<K,E>> node = htable[h]; node!= null; node = node.next){
            if(look.equals(node.info.key)){
                return node;
            }
        }
        return null;
    } 

    public void insert(K key, E value){
        Map<K,E> entry = new Map(key, value);

        int h = hash(key);

        for(SLLNode<Map<K,E>> node = htable[h]; node!= null; node = node.next){
            if(key.equals(node.info.key)){
                node.info = entry;
                return;
            }
        }

        htable[h] = new SLLNode<Map<K, E>>(entry, htable[h]);
    }

    public void delete(K key){
        int h = hash(key);

        for(SLLNode<Map<K,E>> pred = null, node = htable[h];node != null; pred = node, node = node.next){
            if(key.equals(node.info.key)){
                if(pred == null){
                    htable[h] = node.next;
                }else{
                    pred.next = node.next;
                }
                return;
            }
        }

    }
}

class ChessPlayer {
    public String name, surname, country;
    public Integer rank, points;

    ChessPlayer(String name,String surname,String country,Integer rank,Integer points){
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.rank = rank;
        this.points = points;
    }

    @Override
    public String toString(){
        return "Plater" + this.name + " " + this.surname + " is from " + this.country + " and is ranked no." + this.rank+ " in the world with"+ this.points+ " points";
    }
}

class ChessGame {
    public ChessPlayer player_1, player_2;
    public String datetime;
    public Integer gameID;

    ChessGame(ChessPlayer player_1,ChessPlayer player_2, String datetime, Integer gameID){
        this.player_1 = player_1;
        this.player_2 = player_2;
        this.datetime = datetime;
        this.gameID = gameID;
    }

    @Override
    public  String toString(){
        return "Game "+gameID+" is played by " + player_1.toString() + " and " + player_2.toString()+ " on "+ this.datetime;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        ChessPlayer[] chessPlayers = new ChessPlayer[5];
        chessPlayers[0] = new ChessPlayer("Magnus", "Carlsen", "Norway", 1, 2890);
        chessPlayers[1] = new ChessPlayer("Ian", "Nepomniachtci", "Russia", 2, 2823);
        chessPlayers[2] = new ChessPlayer("Liren", "Ding", "China", 3, 2813);
        chessPlayers[3] = new ChessPlayer("Alireza", "Firouzja", "France", 4, 2803);
        chessPlayers[4] = new ChessPlayer("Huikaru", "nakamura", "USA", 5, 2799);

        ChessGame[] chessGames = new ChessGame[10];
        chessGames[0] = new ChessGame(chessPlayers[0], chessPlayers[1], "22/065/2023 14:00", 1);
        chessGames[1] = new ChessGame(chessPlayers[2], chessPlayers[3], "22/065/2023 18:00", 2);
        chessGames[2] = new ChessGame(chessPlayers[1], chessPlayers[3], "23/065/2023 14:00", 1);
        chessGames[3] = new ChessGame(chessPlayers[0], chessPlayers[3], "23/065/2023 18:00", 2);
        chessGames[4] = new ChessGame(chessPlayers[1], chessPlayers[3], "24/065/2023 14:00", 1);
        chessGames[5] = new ChessGame(chessPlayers[2], chessPlayers[3], "24/065/2023 18:00", 2);
        chessGames[6] = new ChessGame(chessPlayers[1], chessPlayers[2], "25/065/2023 14:00", 1);
        chessGames[7] = new ChessGame(chessPlayers[3], chessPlayers[4], "25/065/2023 18:00", 2);
        chessGames[8] = new ChessGame(chessPlayers[0], chessPlayers[4], "26/065/2023 14:00", 1);
        chessGames[9] = new ChessGame(chessPlayers[2], chessPlayers[3], "26/065/2023 18:00", 2);

        SLLHT<Integer, ChessPlayer> chessPlayersHased = new SLLHT<Integer, ChessPlayer>(5);

        chessPlayersHased.insert(chessPlayers[0].rank, chessPlayers[0]);
        chessPlayersHased.insert(chessPlayers[1].rank, chessPlayers[1]);
        chessPlayersHased.insert(chessPlayers[2].rank, chessPlayers[2]);
        chessPlayersHased.insert(chessPlayers[3].rank, chessPlayers[3]);
        chessPlayersHased.insert(chessPlayers[4].rank, chessPlayers[4]);

        SLLHT<Integer, ChessGame> chessGamesHased = new SLLHT<Integer, ChessGame>(5);
        
        chessGamesHased.insert(f(chessGames[0].gameID,chessGames[0].datetime), chessGames[0]);
        chessGamesHased.insert(f(chessGames[1].gameID,chessGames[1].datetime), chessGames[1]); 
        chessGamesHased.insert(f(chessGames[2].gameID,chessGames[2].datetime), chessGames[2]);
        chessGamesHased.insert(f(chessGames[3].gameID,chessGames[3].datetime), chessGames[3]);
        chessGamesHased.insert(f(chessGames[4].gameID,chessGames[4].datetime), chessGames[4]);
        chessGamesHased.insert(f(chessGames[5].gameID,chessGames[5].datetime), chessGames[5]);
        chessGamesHased.insert(f(chessGames[6].gameID,chessGames[6].datetime), chessGames[6]);
        chessGamesHased.insert(f(chessGames[7].gameID,chessGames[7].datetime), chessGames[7]);
        chessGamesHased.insert(f(chessGames[8].gameID,chessGames[8].datetime), chessGames[8]);
        chessGamesHased.insert(f(chessGames[9].gameID,chessGames[9].datetime), chessGames[9]);

        Scanner input = new Scanner(System.in);
        String datetime = input.next();
        Integer player = input.nextInt();

        if(datetime.compareTo("22/05/2023")>0 && datetime.compareTo("26/05/2023")<0){
            SLLNode<Map<Integer, ChessPlayer>> returned_player = chessPlayersHased.find(player);
            if(returned_player!=null){
                System.out.println("Both are valid");
                LinkedList returned_games = findALL(chessGamesHased,datetime,player);

                for (int i = 0; i<returned_games.size();i++) {
                    System.out.println(returned_games.get(i));
                }
            }else{
                System.out.println("Player not valid");
            }
        }else{
            System.out.println("Date not valid");
        }
    }

    private static LinkedList findALL(SLLHT<Integer,ChessGame> chessGamesHashed, String datetime, Integer player){
        LinkedList<ChessGame> allGames = new LinkedList<ChessGame>();
        for(int i=0;i<20;i++){
            int look = f(i+1,datetime);

            SLLNode<Map<Integer, ChessGame>> node = chessGamesHashed.find(look);

            if(node !=null){
                if(node.info.value.player_1.rank == player.intValue() || node.info.value.player_2.rank == player.intValue()){
                    allGames.add(node.info.value);
                }
            }
        }
        return allGames;
    }

    private static int f(Integer gamID, String datetime){
        int day = Integer.parseInt(datetime.split(" ")[0].split("/")[0])-22;

        return 20*day+gamID;
        
    }
}

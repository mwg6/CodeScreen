package MaxGoodman;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MaxGoodman {
    static char[][] grid;
    static Set<String> uS;
    static Set<Character> vowelSet;
    static int mR;
    static int mC;
    static int wordLength;


    public MaxGoodman(char[][] grid, int wordLength){
        this.grid=grid;
        this.wordLength = wordLength;

        //Assume grid is consistent space, as confirmed by Kevin
        this.mR=grid.length;
        this.mC=grid[0].length;

        //O(1) look up, best we can do
        this.uS = new HashSet<>();
        vowelSet = new HashSet<>();
        vowelSet.add('A');
        vowelSet.add('E');
        vowelSet.add('I');
        vowelSet.add('O');
        vowelSet.add('U');
        vowelSet.add('Y');


    }

    private static void solveGrid(int row, int col, int vowels, String s){
        //If 3+ vowels exits before counting as a word
        if(vowels>2){
            return;
        }
        if(s.length()==wordLength){
            uS.add(s);
            return;
        }
        else{
            //skip overflow and blank spaces
            if(row<0||row>=mC||col<0||col>=mC||grid[row][col]==' '){
                return;
            }
            else{
                //track vowels visited in path
                if(vowelSet.contains(grid[row][col])){
                    vowels++;
                }
                makeMoves(row, col, vowels, s+grid[row][col]);
            }

        }
    }

    private static void makeMoves(int row, int col, int vowels, String s){
        int[] rowMoves = {-1,-1,1,1,-2,-2,2,2};
        int[] colMoves = {-2,2,2,-2,1,-1,1,-1};

        for(int i = 0; i<rowMoves.length; i++){
            solveGrid(row+rowMoves[i],col+colMoves[i], vowels, s);

        }
    }

    public static long SolveMatrix(){
        for(int i = 0; i<mR; i++){
            for(int j = 0; j<mC; j++){
                solveGrid(i,j,0,"");
                //ans+=solveDP(i,j,wordLength);

            }
        }

        return uS.size();
        //return ans;

    }
    /*
     Attempt to solve dynamically, with the understanding that each word can be constructed from the value at the
     cell of grid plus the value of all cells it can reach with legal moves to depth d. Instead of going down the tree
     store in some data structure a list of values already known to have been generated from that cell. When accessing
     those cells from legal moves just append the precalculated values. Ran out of time for completion.

    private static List<List<String>> move(int row, int col, int depth){
        List<List<String>> hold = new ArrayList<>();
        int[] rowMoves = {-1,-1,1,1,-2,-2,2,2};
        int[] colMoves = {-2,2,2,-2,1,-1,1,-1};

        //return nothing for overflow or spaces
        if(row<0||row>=mC||col<0||col>=mC||grid[row][col]==' '){
            return hold;
        }

        //if looking for word length one, return value at grid
        if(depth==1){
            List<String> tmp = new ArrayList<>();
            tmp.add(""+grid[row][col]);
            hold.add(tmp);
        }
        //otherwise, return a list of all legal values that a move from grid[i][j] would return
        else {
            for (int i = 0; i < rowMoves.length; i++) {
                for (List<String> list : move(row + rowMoves[i], col + colMoves[i], depth - 1)) {
                    list.replaceAll(s -> "" + grid[row][col] + s);
                    hold.add(list);
                }

            }
        }
        return hold;

    }

    private static long solveDP(int row, int col, int depth){
        List<List<String>> uniques = move(row, col, depth);
        long ans = 0L;
        for(List<String> l : uniques){
            for(String s:l){
                if(!s.contains(" ") && !hasThreeUpVowels(s) ){
                    ans++;
                }
            }
        }
        return ans;
    }

    private static boolean hasThreeUpVowels(String s){
        int count = 0;
        //faster than string replace all vowels and compare size
        for(char c: s.toCharArray()){
            if(vowelSet.contains(c)){
                count++;
            }
            if(count>2){
                return true;
            }
        }
        return false;
    }


    */
}

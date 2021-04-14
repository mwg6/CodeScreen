import MaxGoodman.MaxGoodman;

public class main {

    public static void main(String args[]){

        char[][] a = new char[5][];
        a[0] = new char[]{'A', 'B', 'C', ' ', 'E'};
        a[1] = new char[]{' ', 'G', 'H', 'I', 'J'};
        a[2] = new char[]{'K', 'L', 'M', 'N', 'O'};
        a[3] = new char[]{'P', 'Q', 'R', 'S', 'T'};
        a[4] = new char[]{'U', 'V', ' ', ' ', 'Y'};

        long startTime = System.nanoTime();

        MaxGoodman mg = new MaxGoodman(a,8);

        System.out.println("Found " + mg.SolveMatrix() + " unique words");
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Solve took:" + (double)duration/1_000_000_000.0 + "s");
    }
}

package lexoperm;


public class PermutationsLex_thread_queue3 {

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    public static boolean hasNext(int[] a) {
        int N = a.length;
        // find rightmost element a[k] that is smaller than element to its right
        int k; 
        for (k = N-2; k >= 0; k--)
            if (a[k] < a[k+1]) break;
        if (k == -1) return false;
        // find rightmost element a[j] that is larger than a[k]
        int j = N-1;
        while (a[k] > a[j])
            j--;
        swap(a, j, k);
        for (int r = N-1, s = k+1; r > s; r--, s++)
            swap(a, r, s);
        return true;
    }

    static class perm implements Runnable {

        public perm(){

        }

        private Thread tperm;

        public void start (){
           System.out.println("Starting permutations " );
           if (tperm == null) {
              tperm = new Thread (this);
              tperm.start ();
           }
        }

        public void run() {
            // initialize permutation
            int side_limit = 6;
            int[] a  = new int[side_limit];
            for (int i = 0; i < side_limit; i++)
                a[i] = i;
            while (hasNext(a)) {
                //for (int i = 0; i < side_limit; i++) {
                //    System.out.print(a[i] + ", ");
                //}
                //System.out.println();
                PolygonDrawing myp = new PolygonDrawing();
                myp.setupPolygon(a);
                myp.setVisible(true);
                myp.dispose();
            } //end while
        }//end method Run
    }//end class Perm


}//end PermutationsLex_thread_queue2.java





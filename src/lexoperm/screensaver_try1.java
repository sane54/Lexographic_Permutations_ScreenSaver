/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexoperm;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import javax.swing.JFrame;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Owner
 */
public class screensaver_try1 extends JFrame {
    
        static int sides = 6;
        static int radius = 160;
        static int startingX = 200;
        static int startingY = 200;
        static int vert_p_limit = 4;
        static int horiz_p_limit = 4;
        static Double [] xarray = new Double [sides];
        static Double [] yarray = new Double [sides];
        static Integer [] permvector = new Integer [sides];
        



	public screensaver_try1() {
            super("The Best Lack All Conviction");
            setSize(500, 500);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            looper();
	}

        public void paint(Graphics g) {
            super.paint(g);
            drawLines(g);
	}
        
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
        
        final void looper() {
            System.out.println("I am looper");
            int side_limit = sides;
            int[] a  = new int[side_limit];
            for (int i = 0; i < side_limit; i++)
                a[i] = i;
            while (hasNext(a)) {
                for (int i = 0; i < side_limit; i++) {
                    System.out.print(a[i] + ", ");
                }
                System.out.println();
                
                setupPolygon(a);
                //removeAll();
                repaint();
                revalidate();
                setVisible(true);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(screensaver_try1.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            } //end while
        }
        
        void setupPolygon(int [] a) {
            System.out.println("I am setupPolygon");
            Double theta = 2*Math.PI/sides;
            Double [] angles = new Double[sides];
            Double arc = 0.00;
            for ( int i = 0; i < sides; i++) {
                angles[i] = arc;
                arc = theta + arc;
            }
            for ( int i = 0; i < sides; i++) {
                permvector[i] = a[i];
            }
            for ( int i = 0; i < sides; i++) {
                Double x = (Math.cos(angles[i])*radius) + startingX;
                Double y = (Math.sin(angles[i])*radius) + startingY;
                xarray[i] = x;
                yarray[i] = y;
            }  
        }


        
	public static void drawLines(Graphics g) {
            Graphics2D g2d = (Graphics2D)g;
            System.out.println("I entered drawLines");
            for (int point = 0; point < sides; point++) {
		int mysub = permvector[point];
		int nextsub;
		if (point + 1 < sides) {
			nextsub = permvector[point + 1];
		}
		else {
			nextsub = permvector[0];
			}
		Double xco = xarray[mysub];
		Double yco = yarray[mysub];
		Double nxco = xarray[nextsub];
		Double nyco = yarray[nextsub];
		System.out.println("sub = " + mysub + " nextsub " + nextsub);
		System.out.println("xco = " + xco + " yco = " + yco + " nxco = " + nxco + " nyco = " + nyco);
		g2d.draw(new Line2D.Double(xco, yco, nxco, nyco));
                //Shape s = new Line2D.Double(xco, yco, nxco, nyco);
                //g1.drawLine(360, 200, 280, 340);
            //g2d.draw3DRect(3600, 2000, 280, 340, false);
            }
	}
}


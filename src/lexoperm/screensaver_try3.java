/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexoperm;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import javax.swing.JFrame;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Owner
 */
public class screensaver_try3 extends JFrame implements ActionListener {
    
        static int sides = 10;
        static int radius = 80;
        static int initX = 0;
        static int initY = 0;
        static int vpl = 10;
        static int hpl = 20;
        polygon_arrays [] master_array = new polygon_arrays [vpl * hpl];
        
        public class polygon_arrays {
            int startingX;
            int startingY;
            Double [] xarray = new Double [sides];
            Double [] yarray = new Double [sides];
            Integer [] permvector = new Integer [sides];     
            
            public polygon_arrays(int starting_x, int starting_y) {
                startingX = starting_x;
                startingY = starting_y;
            }
        }


	public screensaver_try3() {
            super("The Best Lack All Conviction");
            setSize(800, 500);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setUndecorated(true);
            setLocationRelativeTo(null);
            setExtendedState(MAXIMIZED_BOTH);
            addKeyListener(new KeyHandler());
            addMouseListener(new MouseHandler());
            looper();
	}

        @Override
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
                
                setupPolygons(a);
                //removeAll();
                repaint();
                revalidate();
                setVisible(true);
                try {
                    TimeUnit.MILLISECONDS.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(screensaver_try3.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            } //end while
        }
        
        void setupPolygons(int [] a) {
            System.out.println("I am setupPolygon");
            Double theta = 2*Math.PI/sides;
            Double [] angles = new Double[sides];
            Double arc = 0.00;
            int startingX = initX;
            int startingY = initY;
            int master_array_counter = 0;
            for (int x_count = 0; x_count < hpl; x_count++) {
                for (int y_count = 0; y_count < vpl; y_count++){
                    polygon_arrays pa = new polygon_arrays(startingX, startingY);
                    for ( int i = 0; i < sides; i++) {
                        angles[i] = arc;
                        arc = theta + arc;
                    }
                    for ( int i = 0; i < sides; i++) {
                        pa.permvector[i] = a[i];
                    }
                    for ( int i = 0; i < sides; i++) {
                        Double x = (Math.cos(angles[i])*radius) + startingX;
                        Double y = (Math.sin(angles[i])*radius) + startingY;
                        pa.xarray[i] = x;
                        pa.yarray[i] = y;
                    }
                    master_array[master_array_counter] = pa;
                    master_array_counter++;
                    startingY = startingY + 2*radius - 7;
                    //startingY = startingY + 2*radius;
                }
                startingY = initY;
                startingX = startingX + 2*radius;
            }    
        }


        
	public void drawLines(Graphics g) {
            Graphics2D g2d = (Graphics2D)g;
            System.out.println("I entered drawLines");
            for (int i = 0; i < vpl * hpl; i++){
                polygon_arrays pa = master_array[i];
                for (int point = 0; point < sides; point++) {
                    for (int p = 0; p < pa.permvector.length; p++) {
                        System.out.print(p);
                    }
                    System.out.println();
                    int mysub = pa.permvector[point];
                    int nextsub; 
                    if (point + 1 < sides) {
                            nextsub = pa.permvector[point + 1];
                    }
                    else {
                            nextsub = pa.permvector[0];
                            }
                    Double xco = pa.xarray[mysub];
                    Double yco = pa.yarray[mysub];
                    Double nxco = pa.xarray[nextsub];
                    Double nyco = pa.yarray[nextsub];
                    //System.out.println("sub = " + mysub + " nextsub " + nextsub);
                    //System.out.println("xco = " + xco + " yco = " + yco + " nxco = " + nxco + " nyco = " + nyco);
                    g2d.draw(new Line2D.Double(xco, yco, nxco, nyco));
                }
            }

	}
     @Override   
     public void actionPerformed( ActionEvent actionEvent ){
        repaint();
        }
     
    class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            // Terminate the program.
            //int keyCode= e.getKeyCode();
            System.exit(0);
//            if(keyCode== KeyEvent.VK_E){
//             System.exit(0);
//            }
        }
    }    
    class MouseHandler extends MouseAdapter {
        @Override
        public void mouseMoved(MouseEvent e) {
            System.exit(0);
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            System.exit(0);
        }
    }
    
}


package lexoperm;

import java.awt.GraphicsEnvironment;

public class lexoplayer2 {

    public static void main(String[] args) {
        //perm P = new perm();
	//P.start();
        //screensaver_try2 myss = new screensaver_try2();
        if (args.length > 0) {
            if ("/s".equals(args[0])) {
                screensaver_try3 myss = new screensaver_try3();
                myss.dispose(); 
            }
            else if ("/c".equals(args[0])) {
                System.out.println("Nothing to configure");
            }
            else if ("/p".equals(args[0])) {
                screensaver_try3 myss = new screensaver_try3();
                myss.dispose(); 
            }
        }
        else {
            screensaver_try3 myss = new screensaver_try3();
            myss.dispose();    
        }

    }

}

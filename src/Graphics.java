import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Rectangle;
import java.awt.Color;


public class Graphics extends JPanel
implements ActionListener {
        private Timer t = new Timer(100, this);
        public String state;

        private Snake s;
        private Food f;
        private Game game;

  public Graphics(Game g){
            t.start();
            state = "START";

            game = g;
            s= g.getPlayer();
            f= g.getFood();

            this.addKeyListener(g);
            this.setFocusable(true);
            this.setFocusTraversalKeysEnabled(false);
        }
        public void paintComponent(java.awt.Graphics g){
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;

            g2d.setColor(Color.BLACK);
            g2d.fillRect(0,0, Game.width * Game.dimension + 5, Game.height * Game.dimension + 5 );

            if(state == "START"){
                g2d.setColor(Color.WHITE);
                g2d.drawString("Press any key", Game.width/2 * Game.dimension - 40, Game.height/2 * Game.dimension - 20);
            }
            else if(state == "RUNNING"){
                g2d.setColor(Color.RED);
                g2d.fillRect(f.getX() * Game.dimension, f.getY() * Game.dimension, Game.dimension, Game.dimension );

                g2d.setColor(Color.GREEN);
                for( Rectangle r : s.getBody()){
                    g2d.fill(r);
                }
            }
            else {
                if ((s.getBody().size() - 3) <=30) {
                    g2d.setColor(Color.WHITE);
                    g2d.drawString("Your score is: " + (s.getBody().size() - 3) + ". You're kinda bad", Game.width / 2 * Game.dimension - 40, Game.height / 2 * Game.dimension - 20);
                }
                else if ((s.getBody().size() - 3) >=30 && (s.getBody().size() - 3) <=50) {
                    g2d.setColor(Color.WHITE);
                    g2d.drawString("Your score is: " + (s.getBody().size() - 3) + ". You're decent", Game.width / 2 * Game.dimension - 40, Game.height / 2 * Game.dimension - 20);
                }
                else if ((s.getBody().size() - 3) >=50 && (s.getBody().size() - 3) <=100) {
                    g2d.setColor(Color.WHITE);
                    g2d.drawString("Your score is: " + (s.getBody().size() - 3) + ". Sheeeesh you're nice with it", Game.width / 2 * Game.dimension - 40, Game.height / 2 * Game.dimension - 20);
                }
                else {
                    g2d.setColor(Color.WHITE);
                    g2d.drawString("Your score is: " + (s.getBody().size() - 3) + ". Cracked out of you're mind", Game.width / 2 * Game.dimension - 40, Game.height / 2 * Game.dimension - 20);
                }
            }



        }

        @Override
        public void actionPerformed(ActionEvent e){
            repaint();

            game.update();
        }
}

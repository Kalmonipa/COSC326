/* Oliver Westenra, Etude 6  */

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Iterative extends JFrame {
    private final JPanel panel;
    public final int x = 243; 
    public static int count; 
   
    private static ArrayList<Integer> width = new ArrayList<Integer>();
    private static ArrayList<Integer> height = new ArrayList<Integer>();
    private static ArrayList<Integer> lengths = new ArrayList<Integer>();
 
    public static void main(String[] args) {
        Iterative frame = new Iterative();
        try {
            count = Integer.parseInt(args[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        
        frame.setVisible(true);
    }
	
	/* Constructor for the window */
    public Iterative() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        panel = new JPanel();
        getContentPane().setBackground(Color.green);
        getContentPane().add(panel);

    }
	
	/* Paints the squares in the window */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.gray);
        int w = (getWidth() / 2) - (x / 2);
        int h = (getHeight() / 2) - (x / 2);
        int numSquares = count;
		
        g.fillRect(w, h, x, x);
       
        while (numSquares > 0) {
           if (width.isEmpty()) { 
                drawSquares(g, w, h, x);
                update(w, h, x);
            } 
			else {
                for (int i = 0; i < (Math.pow(9,(count-1))); i++) {
                    if(update(width.get(i), height.get(i), lengths.get(i))){
                      drawSquares(g, width.get(i), height.get(i), lengths.get(i));
                    }
                }
            }
            numSquares--; 
        }
        width.clear();
        height.clear();
        lengths.clear();
    }
	
	
	/*
	* Adds the coordinates for the surrounding squares 
	*/
    private boolean update(int x, int y, int size) {
      width.add(x + (size / 3));
      height.add(y - (2 * size / 3));
      lengths.add(size/3);
      
      width.add(x - (2 * size / 3));
      height.add(y - (2 * size / 3));
      lengths.add(size/3);
      
      width.add(x + (4 * size / 3));
      height.add(y - (2 * size / 3));
      lengths.add(size/3);
	  
      width.add(x - (2 * size / 3));
      height.add(y + (size / 3));
      lengths.add(size/3);
      
      width.add(x + size + (size / 3));
      height.add(y + (size / 3));
      lengths.add(size/3);
      
      width.add(x + (size / 3));
      height.add(y + (4 * size / 3));
      lengths.add(size/3);
      
      width.add(x - (2 * size / 3));
      height.add(y + (4 * size / 3));
      lengths.add(size/3);
      
      width.add(x + size + (size / 3));
      height.add(y + (4 * size / 3));
      lengths.add(size/3);
      
      int d =lengths.get(lengths.size()-1);
      if( d < 243  /  ((int)Math.pow(3,count))){
        return false;
      }
	return true;
    }

	/* Draws each of the squares at the coordinates */
    private void drawSquares(Graphics g, int w, int h, int size) {
        g.fillRect(w, h, size, size);
        g.fillRect(w + (size / 3), h - (2 * size / 3), size / 3, size / 3);
        g.fillRect(w - (2 * size / 3), h - (2 * size / 3), size / 3, size / 3);
        g.fillRect(w + (4 * size / 3), h - (2 * size / 3), size / 3, size / 3);
        g.fillRect(w - (2 * size / 3), h + (size / 3), size / 3, size / 3);
        g.fillRect(w + size + (size / 3), h + (size / 3), size / 3, size / 3);
        g.fillRect(w + (size / 3), h + (4 * size / 3), size / 3, size / 3);
        g.fillRect(w - (2 * size / 3), h + (4 * size / 3), size / 3, size / 3);
        g.fillRect(w + size + (size / 3),h + (4 * size / 3), size / 3, size / 3);
    }
}

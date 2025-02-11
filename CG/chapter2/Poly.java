package chapter2;
public class Poly extends JApplet { 

    // called when applet is started 
    public void init() { 
        // set the size of applet to 200, 200 
        setSize(200, 200); 
        show(); 
    } 

    // invoked when applet is started 
    public void start() { 
    } 

    // invoked when applet is closed 
    public void stop() { 
    } 

    public void paint(Graphics g) { 
        // x coordinates of vertices 
        int x[] = { 10, 30, 40, 50, 110, 140 }; 

        // y coordinates of vertices 
        int y[] = { 140, 110, 50, 40, 30, 10 }; 

        // number of vertices 
        int numberofpoints = 6; 

        // set the color of line drawn to blue 
        g.setColor(Color.blue); 

        // draw the polygon using drawPolygon function 
        g.drawPolygon(x, y, numberofpoints); 
    } 

    public static void main(String[] args) {
        JFrame frame = new JFrame("Polygon Drawing");
        Poly applet = new Poly();
        frame.add(applet);
        frame.setSize(200, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        applet.init();
        applet.start();
    }
}
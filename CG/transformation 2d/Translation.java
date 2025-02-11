import java.util.Scanner;
import java.awt.*;
class Translation extends Frame{
   int x1,y1,x2,y2,tx,ty;
   int px1,px2,py1,py2;
   public Translation()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter x1");
        x1=sc.nextInt();
        System.out.println("Enter y1");
        y1=sc.nextInt();
        System.out.println("Enter x2");
        x2=sc.nextInt();
        System.out.println("Enter y2");
        y2=sc.nextInt();
        System.out.println("Enter tx");
        tx=sc.nextInt();
        System.out.println("Enter ty");
        ty=sc.nextInt();
        
       this. setTitle("Line Translation");
       this.setLayout(null);
       this.setBounds(100, 100,800 , 800);
       this.setVisible(true);
    
       px1=x1+tx;
       px2=x2+tx;
       py1=y1+ty;
       py2=y2+ty;
    }
       public void paint(Graphics g)
       {
         g.drawLine(x1,y1,x2,y2);
g.drawString("before translation",x1,y1);
         g.drawLine(px1,py1,px2,py2);
g.drawString("after  translation",px1,py1);
       }
    public static void main(String[] args)
    {
        new Translation();
    }
}
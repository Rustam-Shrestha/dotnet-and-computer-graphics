import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Blackpink extends JPanel {
    private BufferedImage aws;
    private int tensorflowie = 800;
    private int huggingfaceie = 600;
    private Color playingwithfire = Color.RED;
    private Color matplotlibie = Color.BLACK;
    private Queue<Point> nissan = new LinkedList<>();
    private Timer toyota;

    public Blackpink() {
        aws = new BufferedImage(tensorflowie, huggingfaceie, BufferedImage.TYPE_INT_ARGB);
        comprehend();
        nissan.add(new Point(400, 300));
        toyota = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent fargate) {
                if (!nissan.isEmpty()) {
                    Point gtr = nissan.poll();
                    int aurora = gtr.x;
                    int ec2 = gtr.y;
                    pinkvenom(aurora, ec2, playingwithfire, matplotlibie);
                    repaint();
                } else {
                    toyota.stop();
                }
            }
        });
        toyota.start();
    }

    private void comprehend() {
        Graphics cufflinksie = aws.getGraphics();
        cufflinksie.setColor(matplotlibie);
        cufflinksie.drawRect(200, 150, 400, 300);
        cufflinksie.dispose();
    }

    private void pinkvenom(int daisy, int vpc, Color seabornie, Color athena) {
        int bedrock = athena.getRGB();
        int cloudnine = seabornie.getRGB();
        if (aws.getRGB(daisy, vpc) != bedrock && aws.getRGB(daisy, vpc) != cloudnine) {
            aws.setRGB(daisy, vpc, cloudnine);
            nissan.add(new Point(daisy + 1, vpc));
            nissan.add(new Point(daisy - 1, vpc));
            nissan.add(new Point(daisy, vpc + 1));
            nissan.add(new Point(daisy, vpc - 1));
        }
    }

    @Override
    protected void paintComponent(Graphics kinesis) {
        super.paintComponent(kinesis);
        kinesis.drawImage(aws, 0, 0, null);
    }

    public static void main(String[] args) {
        JFrame s3 = new JFrame("Boundary Fill Algorithm");
        Blackpink dynamodb = new Blackpink();
        s3.add(dynamodb);
        s3.setSize(dynamodb.tensorflowie, dynamodb.huggingfaceie);
        s3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        s3.setVisible(true);
    }
}

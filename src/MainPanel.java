import javax.swing.*; 
import java.awt.*;

public class MainPanel extends JPanel{
    int x = 500;
    int y = 500;
    int offset = 28;
    int yoffset = y -offset;
    
    Double x1 = (0.33)*(x);
    int Ix1 = x1.intValue();
    Double x2 = (0.67)*(x);
    int Ix2 = x2.intValue();

    Double y1 = (0.33)*yoffset;
    int Iy1 = y1.intValue();
    Double y2 = (0.67)*yoffset;
    int Iy2 = y2.intValue();
    
    MainPanel(){
        this.setSize(500,yoffset);
    }
    
    public void paint(Graphics g ){
        Graphics2D g2d = (Graphics2D) g;

        g2d.setPaint(Color.red);
        g2d.setStroke(new BasicStroke(8));
        g2d.drawLine(0,offset,x,offset); //Division line between title and main board

        g2d.drawLine(Ix1,offset,Ix1,yoffset); //Vertical L1
        g2d.drawLine(Ix2,offset,Ix2,yoffset); //Vertical L2

        g2d.drawLine(0,Iy1,x,Iy1); //Horizontal L1
        g2d.drawLine(0,Iy2,x,Iy2); //Horizontal L2

        g2d.drawLine(0,offset,0,y); //Left Border
        g2d.drawLine(0,yoffset-3,x,yoffset-3); //Bottom Border
        g2d.drawLine(x-3,offset,x-3,yoffset); //Right Border
        App state = new App();
        if (state.winstate== true){
            g2d.setPaint(Color.green);
            g2d.setStroke(new BasicStroke(8));
            g2d.fillRect(Ix1, Iy1+75, Ix2 - Ix1, 50);
        }

    }

 

}


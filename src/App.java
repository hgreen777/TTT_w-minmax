import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App implements ActionListener {
    public static int x = 519;
    public static int yoffset = 513;
    public static int aiToggle = 1; 
    //1 = Ai is X, 2 = Ai is O;

    static boolean winstate = false;

    public static void main(String[] args) throws Exception {
        mainMenu menu = new mainMenu();
        menu.setTitle("Select GameMode");
        menu.setSize(250,300);
        menu.setLayout(null);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setVisible(true);





    }

    public static void twoPlayerStartup() {
        onegameFrame frame = new onegameFrame();
        frame.setSize(x,yoffset);

        JButton reset = new JButton("Reset");
        reset.setBounds(100,0,80, 25);
        reset.addActionListener(e -> {
            frame.dispose();
            winstate = false;
            twoPlayerStartup();
        });

        JButton back = new JButton("Back");
        back.setBounds(0,0,80, 25);
        back.addActionListener(e -> {
            frame.dispose();
            winstate = false;
            try {
                main(null);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                System.out.println("Error");
            }
        });

        frame.add(reset);frame.add(back);
        frame.setVisible(true);
    }

    public static void onePlayerStartup(){
        onegameFrame frame = new onegameFrame();
        frame.setSize(x,yoffset);

        JButton reset = new JButton("Reset");
        reset.setBounds(100,0,80, 25);
        reset.addActionListener(e -> {
            frame.dispose();
            winstate = false;
            onePlayerStartup();
        });

        JButton back = new JButton("Back");
        back.setBounds(0,0,80, 25);
        back.addActionListener(e -> {
            frame.dispose();
            winstate = false;
            try {
                main(null);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                System.out.println("Error");
            }
        });

        frame.add(reset);frame.add(back);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }

    
}
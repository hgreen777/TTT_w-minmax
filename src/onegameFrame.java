import javax.swing.*;
import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class onegameFrame extends JFrame implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
    
    //Importing Game Board
    MainPanel panel;
    App state = new App();

    //Info Label Decalration
    JLabel lblturnDisplay;
    JLabel lblResult;

    JButton oo; JLabel R00; //First row 
    JButton o1; JLabel R01;
    JButton o2; JLabel R02;

    JButton Io; JLabel R10;//Second row 
    JButton I1; JLabel R11;
    JButton I2; JLabel R12;
    
    JButton two0;JLabel R20;//Third row
    JButton two1;JLabel R21;
    JButton two2;JLabel R22;
    
    //For dimensions and locations for buttons etc
    int x = 500;
    int y = 500;
    int offset = 28;
    int yoffset = y -offset;

    Double xbtn00 = (0.5*((0.33)*x))-50.0; //Horizontal positiong for first column
    int ixbtn00 = xbtn00.intValue();

    Double xbtn01 = (0.5*x)-50.0;  //Horizontal positioning for second comuln
    int ixbtn01 = xbtn01.intValue();

    Double xbtn02 = ((0.833)*x)-50.0;  //Horizontal positioning for third comuln
    int ixbtn02 = xbtn02.intValue();
    
    Double ybtn00 = (0.5*((1/3)*yoffset))+50.0; //Vertical position for first row
    int iybtn00 = ybtn00.intValue();

    Double ybtn10 = (0.5*yoffset)-50.0;  //Vertical positioning for second row
    int iybtn10 = ybtn10.intValue();

    Double ybtn20 = ((0.833)*yoffset)-50.0; //Vertical positioning for third row
    int iybtn20 = ybtn20.intValue();

    int counter = 0;
    String turn = "X";

    //Array for minimax algorithm 
    String[][] gameBoard = new String[3][3];  
    public void fillArray(){
        for (int i = 0; i < 3; i++){
            for (int j = 0; i < 3; i++){
                gameBoard[i][j] = "";
            }
        } 
    }  
    /*
     * {0,0,0}
     * {0,0,0}
     * {0,0,0}
     */


    onegameFrame(){
        fillArray();
        String title = "One Player TicTacToe";

        //Setting general attributes about JFrame
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        ImageIcon image = new ImageIcon("3286676.png");
        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(new Color(153,204,255));

        JLabel Menutitle = new JLabel();// Label title
        Menutitle.setText("TicTacToe");
        Menutitle.setFont(new Font("Ariel",Font.BOLD,20));
        Menutitle.setBounds(200, 0, 500, 100);
        Menutitle.setVerticalAlignment(JLabel.TOP);
        this.add(Menutitle);

        //Displaying Turn
        lblturnDisplay = new JLabel();
        lblturnDisplay.setText(turn + " Turn");
        lblturnDisplay.setFont(new Font("Ariel",Font.BOLD,20));
        lblturnDisplay.setVerticalAlignment(JLabel.TOP);
        lblturnDisplay.setBounds(300, 0, 200, 100);
        lblturnDisplay.setHorizontalAlignment(JLabel.RIGHT);
        this.add(lblturnDisplay);

        //For displaying Final Game Result
        lblResult = new JLabel();
        lblResult.setFont(new Font("Ariel",Font.BOLD,50));
        lblResult.setVerticalAlignment(JLabel.CENTER);
        lblResult.setBounds(0, 0, 500, 500);
        lblResult.setHorizontalAlignment(JLabel.CENTER); 
        this.add(lblResult);

        //Drawing board
        panel = new MainPanel();

        this.add(panel);
        this.pack();
        components();
    }

    public void components(){
        //Declaration and initialisation of all buttons
        oo = new JButton("");
        oo.setBounds(ixbtn00, iybtn00, 100, 100);
        
        o1 = new JButton("");
        o1.setBounds(ixbtn01, iybtn00, 100, 100);

        o2 = new JButton("");
        o2.setBounds(ixbtn02, iybtn00, 100, 100);

        Io = new JButton("");
        Io.setBounds(ixbtn00, iybtn10, 100, 100);

        I1 = new JButton("");
        I1.setBounds(ixbtn01, iybtn10, 100, 100);

        I2 = new JButton("");
        I2.setBounds(ixbtn02, iybtn10, 100, 100);

        two0 = new JButton("");
        two0.setBounds(ixbtn00, iybtn20, 100, 100);

        two1 = new JButton("");
        two1.setBounds(ixbtn01, iybtn20, 100, 100);

        two2 = new JButton("");
        two2.setBounds(ixbtn02, iybtn20, 100, 100);

        //Adding Action listener to buttons
        oo.addActionListener(e -> {turnChanger(oo, R00); gameBoard[0][0] = R00.getText();testArray();}); 
        o1.addActionListener(e -> {turnChanger(o1, R01); gameBoard[0][1] = R01.getText();testArray();}); 
        o2.addActionListener(e -> {turnChanger(o2, R02); gameBoard[0][2] = R02.getText();}); 
        
        Io.addActionListener(e -> {turnChanger(Io, R10); gameBoard[1][0] = R10.getText();}); 
        I1.addActionListener(e -> {turnChanger(I1, R11); gameBoard[1][1] = R11.getText();}); 
        I2.addActionListener(e -> {turnChanger(I2, R12); gameBoard[1][2] = R12.getText();});
        
        two0.addActionListener(e -> {turnChanger(two0, R20); gameBoard[2][0] = R20.getText();});
        two1.addActionListener(e -> {turnChanger(two1, R21); gameBoard[2][1] = R21.getText();});
        two2.addActionListener(e -> {turnChanger(two0, R22); gameBoard[2][2] = R22.getText();});
        
        //Adding Buttons to Frame
        this.add(oo);this.add(o1);this.add(o2); //Adding First row of buttons
        this.add(Io);this.add(I1);this.add(I2);
        this.add(two0);this.add(two1);this.add(two2);       
        
        //All Result Label Declaration byt starting hidden

        R00 = new JLabel("");
        R00.setFont(new Font("Ariel",Font.BOLD,150));
        R00.setBounds(ixbtn00, iybtn00, 500, 100);
        this.add(R00);
        R00.setVisible(false);

        R01 = new JLabel("");
        R01.setFont(new Font("Ariel",Font.BOLD,150));
        R01.setBounds(ixbtn01, iybtn00, 500, 100);
        this.add(R01);
        R01.setVisible(false);

        R02 = new JLabel("");
        R02.setFont(new Font("Ariel",Font.BOLD,150));
        R02.setBounds(ixbtn02, iybtn00, 500, 100);
        this.add(R02);
        R02.setVisible(false);

        R10 = new JLabel("");
        R10.setFont(new Font("Ariel",Font.BOLD,150));
        R10.setBounds(ixbtn00, iybtn10, 500, 100);
        this.add(R10);
        R10.setVisible(false);

        R11 = new JLabel("");
        R11.setFont(new Font("Ariel",Font.BOLD,150));
        R11.setBounds(ixbtn01, iybtn10, 500, 100);
        this.add(R11);
        R11.setVisible(false);

        R12 = new JLabel("");
        R12.setFont(new Font("Ariel",Font.BOLD,150));
        R12.setBounds(ixbtn02, iybtn10, 500, 100);
        this.add(R12);
        R12.setVisible(false);

        R12 = new JLabel("");
        R12.setFont(new Font("Ariel",Font.BOLD,150));
        R12.setBounds(ixbtn02, iybtn10, 500, 100);
        this.add(R12);
        R12.setVisible(false);

        R20 = new JLabel("");
        R20.setFont(new Font("Ariel",Font.BOLD,150));
        R20.setBounds(ixbtn00, iybtn20, 500, 100);
        this.add(R20);
        R20.setVisible(false);

        R21 = new JLabel("");
        R21.setFont(new Font("Ariel",Font.BOLD,150));
        R21.setBounds(ixbtn01, iybtn20, 500, 100);
        this.add(R21);
        R21.setVisible(false);
        
        R22 = new JLabel("");
        R22.setFont(new Font("Ariel",Font.BOLD,150));
        R22.setBounds(ixbtn02, iybtn20, 500, 100);
        this.add(R22);
        R22.setVisible(false);
        

    }
    //Sets the label under button to result and sets the turn label to next go, than runs AI minimax algorithm
    public void turnChanger(JButton x, JLabel y){
        if((counter % 2) == 0){
            y.setText("X");
            turn = "O";
            lblturnDisplay.setText(turn + " Turn");
            
            //Prepping for AI Algorithm
            btnVisible(false);
            minimax(); // After X go has been done and set, Algorithm gets to work

            //vv Code below potentially not needed
        }else{
            y.setText("O");
            turn = "X";
            lblturnDisplay.setText(turn + " Turn");
        }

        x.setVisible(false);
        y.setVisible(true);
        winchecker();
        counter += 1;

    }
 
    //Checks who has won the fight 
    public void winchecker(){
        if (  ((R00.getText() == "X") && (R01.getText() == "X") && (R02.getText() == "X"))  ||   ((R10.getText() == "X") && (R11.getText() == "X") && (R12.getText() == "X"))  ||   ((R20.getText() == "X") && (R21.getText() == "X") && (R22.getText() == "X"))  ||   ((R00.getText() == "X") && (R10.getText() == "X") && (R20.getText() == "X"))  ||   ((R01.getText() == "X") && (R11.getText() == "X") && (R21.getText() == "X"))  ||   ((R02.getText() == "X") && (R12.getText() == "X") && (R22.getText() == "X"))  ||   ((R00.getText() == "X") && (R11.getText() == "X") && (R22.getText() == "X"))  ||   ((R02.getText() == "X") && (R11.getText() == "X") && (R20.getText() == "X"))  ){
            oo.setVisible(false);o1.setVisible(false);o2.setVisible(false);
            Io.setVisible(false);I1.setVisible(false);I2.setVisible(false);
            two0.setVisible(false);two1.setVisible(false);two2.setVisible(false);
            state.winstate = true;
            panel.repaint();
            lblResult.setText("X Wins");
            

        }else if (  ((R00.getText() == "O") && (R01.getText() == "O") && (R02.getText() == "O"))  ||   ((R10.getText() == "O") && (R11.getText() == "O") && (R12.getText() == "O"))  ||   ((R20.getText() == "O") && (R21.getText() == "O") && (R22.getText() == "O"))  ||   ((R00.getText() == "O") && (R10.getText() == "O") && (R20.getText() == "O"))  ||   ((R01.getText() == "O") && (R11.getText() == "O") && (R21.getText() == "O"))  ||   ((R02.getText() == "O") && (R12.getText() == "O") && (R22.getText() == "O"))  ||   ((R00.getText() == "O") && (R11.getText() == "O") && (R22.getText() == "O"))  ||   ((R02.getText() == "O") && (R11.getText() == "O") && (R20.getText() == "O"))  ){
            oo.setVisible(false);o1.setVisible(false);o2.setVisible(false);
            Io.setVisible(false);I1.setVisible(false);I2.setVisible(false);
            two0.setVisible(false);two1.setVisible(false);two2.setVisible(false);
            
            lblResult.setText("O Wins");
            lblResult.setVisible(true);
            state.winstate = true;
            panel.repaint();
        }
    }

    public void minimax(){

    }

    public void btnVisible(Boolean flip){
        oo.setVisible(flip);o1.setVisible(flip);o2.setVisible(flip);
        Io.setVisible(flip);I1.setVisible(flip);I2.setVisible(flip);
        two0.setVisible(flip);two1.setVisible(flip);two2.setVisible(flip);
    }

    public void testArray(){
        for (int i = 0; i < 3; i++){
            for (int j = 0; i < 3; i++){
                System.out.println(gameBoard[i][j]);
            }
        } 
    }
}

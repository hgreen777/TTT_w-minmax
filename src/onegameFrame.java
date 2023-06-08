import javax.swing.*;
import javax.swing.text.Position;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import java.awt.Font;
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

    //Variables needed for game playy
    int counter = 0;
    String turn;

    String human;
    String ai;

    //Array for minimax algorithm 
    String[][] gameBoard = new String[3][3];  
    public void fillArray(){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                gameBoard[i][j] = "";
            }
        } 
    }  
    /* ^^ Above method makes array empty like shown
     * {0,0,0}
     * {0,0,0}
     * {0,0,0}
     */


    onegameFrame(){
        //When Building board sets the first go to ai (and sets the array gameboard to empty)
        if(App.aiToggle == 1){
            ai = "X";
            human = "O";
            turn = ai;
        }else{
            ai = "O";
            human = "X";
            turn = human;
        }
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

        //Setting title of board
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

        //Drawing board lines
        panel = new MainPanel();

        //adding board to frame
        this.add(panel);
        this.pack();

        //Adding all components like buttons and result labels
        components();

        //Starts with ai go is set to AI as X in settings menu
        if (App.aiToggle ==1){
            AImove();
        }
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
        oo.addActionListener(e -> {gameBoard[0][0] = human;boardUpdate();counter +=1;AImove();}); 
        o1.addActionListener(e -> {gameBoard[0][1] = human;boardUpdate();counter +=1;AImove();}); 
        o2.addActionListener(e -> {gameBoard[0][2] = human;boardUpdate();counter +=1;AImove();}); 
        
        Io.addActionListener(e -> {gameBoard[1][0] = human;boardUpdate();counter +=1;AImove();}); 
        I1.addActionListener(e -> {gameBoard[1][1] = human;boardUpdate();counter +=1;AImove();}); 
        I2.addActionListener(e -> {gameBoard[1][2] = human;boardUpdate();counter +=1;AImove();});
        
        two0.addActionListener(e -> {gameBoard[2][0] = human;boardUpdate();counter +=1;AImove();});
        two1.addActionListener(e -> {gameBoard[2][1] = human;boardUpdate();counter +=1;AImove();});
        two2.addActionListener(e -> {gameBoard[2][2] = human;boardUpdate();counter +=1;AImove();});
        
        //Adding Buttons to Frame
        this.add(oo);this.add(o1);this.add(o2); //Adding First row of buttons
        this.add(Io);this.add(I1);this.add(I2);
        this.add(two0);this.add(two1);this.add(two2);       
        
        //All Result Label Declaration and adding to gameboard

        R00 = new JLabel("");
        R00.setFont(new Font("Ariel",Font.BOLD,150));
        R00.setBounds(ixbtn00, iybtn00, 500, 100);
        this.add(R00);
        R00.setVisible(true);

        R01 = new JLabel("");
        R01.setFont(new Font("Ariel",Font.BOLD,150));
        R01.setBounds(ixbtn01, iybtn00, 500, 100);
        this.add(R01);
        R01.setVisible(true);

        R02 = new JLabel("");
        R02.setFont(new Font("Ariel",Font.BOLD,150));
        R02.setBounds(ixbtn02, iybtn00, 500, 100);
        this.add(R02);
        R02.setVisible(true);

        R10 = new JLabel("");
        R10.setFont(new Font("Ariel",Font.BOLD,150));
        R10.setBounds(ixbtn00, iybtn10, 500, 100);
        this.add(R10);
        R10.setVisible(true);

        R11 = new JLabel("");
        R11.setFont(new Font("Ariel",Font.BOLD,150));
        R11.setBounds(ixbtn01, iybtn10, 500, 100);
        this.add(R11);
        R11.setVisible(true);

        R12 = new JLabel("");
        R12.setFont(new Font("Ariel",Font.BOLD,150));
        R12.setBounds(ixbtn02, iybtn10, 500, 100);
        this.add(R12);
        R12.setVisible(true);

        R12 = new JLabel("");
        R12.setFont(new Font("Ariel",Font.BOLD,150));
        R12.setBounds(ixbtn02, iybtn10, 500, 100);
        this.add(R12);
        R12.setVisible(true);

        R20 = new JLabel("");
        R20.setFont(new Font("Ariel",Font.BOLD,150));
        R20.setBounds(ixbtn00, iybtn20, 500, 100);
        this.add(R20);
        R20.setVisible(true);

        R21 = new JLabel("");
        R21.setFont(new Font("Ariel",Font.BOLD,150));
        R21.setBounds(ixbtn01, iybtn20, 500, 100);
        this.add(R21);
        R21.setVisible(true);
        
        R22 = new JLabel("");
        R22.setFont(new Font("Ariel",Font.BOLD,150));
        R22.setBounds(ixbtn02, iybtn20, 500, 100);
        this.add(R22);
        R22.setVisible(true);
    }

    //Sets all buttons to either visible or not
    public void btnVisible(Boolean flip){
        oo.setVisible(flip);o1.setVisible(flip);o2.setVisible(flip);
        Io.setVisible(flip);I1.setVisible(flip);I2.setVisible(flip);
        two0.setVisible(flip);two1.setVisible(flip);two2.setVisible(flip);
    }

    //Prints current State of gameboard used for testing
    public void testArray(){
        System.out.print("{");
        for (int i = 0; i <= 2; i++){
            for (int j = 0; j <= 2; j++){
                System.out.print(gameBoard[i][j] + ", ");
            }
            System.out.println("");
        } 
        System.out.println("}");
    }

    //Reference point for updating game board
    //See two player game for turnchanger code to ensure update board works correctly
 
    //Checks who has won the fight 
    public void winchecker(){ 
        if (  ((R00.getText() == "X") && (R01.getText() == "X") && (R02.getText() == "X"))  ||   ((R10.getText() == "X") && (R11.getText() == "X") && (R12.getText() == "X"))  ||   ((R20.getText() == "X") && (R21.getText() == "X") && (R22.getText() == "X"))  ||   ((R00.getText() == "X") && (R10.getText() == "X") && (R20.getText() == "X"))  ||   ((R01.getText() == "X") && (R11.getText() == "X") && (R21.getText() == "X"))  ||   ((R02.getText() == "X") && (R12.getText() == "X") && (R22.getText() == "X"))  ||   ((R00.getText() == "X") && (R11.getText() == "X") && (R22.getText() == "X"))  ||   ((R02.getText() == "X") && (R11.getText() == "X") && (R20.getText() == "X"))  ){
            btnVisible(false);
            state.winstate = true;
            panel.repaint();
            lblResult.setText("X Wins");
            

        }else if (  ((R00.getText() == "O") && (R01.getText() == "O") && (R02.getText() == "O"))  ||   ((R10.getText() == "O") && (R11.getText() == "O") && (R12.getText() == "O"))  ||   ((R20.getText() == "O") && (R21.getText() == "O") && (R22.getText() == "O"))  ||   ((R00.getText() == "O") && (R10.getText() == "O") && (R20.getText() == "O"))  ||   ((R01.getText() == "O") && (R11.getText() == "O") && (R21.getText() == "O"))  ||   ((R02.getText() == "O") && (R12.getText() == "O") && (R22.getText() == "O"))  ||   ((R00.getText() == "O") && (R11.getText() == "O") && (R22.getText() == "O"))  ||   ((R02.getText() == "O") && (R11.getText() == "O") && (R20.getText() == "O"))  ){
            btnVisible(false);
            
            lblResult.setText("O Wins");
            lblResult.setVisible(true);
            state.winstate = true;
            panel.repaint();
        }else if ( (R00.getText() != "") && (R01.getText() != "") && (R02.getText() != "") && (R10.getText() != "") && (R11.getText() != "") && (R12.getText() != "") && (R20.getText() != "") && (R21.getText() != "") && (R22.getText() != "")){
            btnVisible(false);
            
            lblResult.setText("Draw");
            lblResult.setVisible(true);
            state.winstate = true;
            panel.repaint();
        }
    }

    // Checks if there is any winner in the array of the gameboard ---- needed for the minimax algorithm as testing whether someone will win off a go without changing the actual board
    public int boardWinChecker(){
        if (  ((gameBoard[0][0] == "X") && (gameBoard[0][1] == "X") && (gameBoard[0][2] == "X"))  ||   ((gameBoard[1][0] == "X") && (gameBoard[1][1] == "X") && (gameBoard[1][2] == "X"))  ||   ((gameBoard[2][0] == "X") && (gameBoard[2][1] == "X") && (gameBoard[2][2] == "X"))  ||   ((gameBoard[0][0] == "X") && (gameBoard[1][0] == "X") && (gameBoard[2][0] == "X"))  ||   ((gameBoard[0][1] == "X") && (gameBoard[1][1] == "X") && (gameBoard[2][1] == "X"))  ||   ((gameBoard[0][2] == "X") && (gameBoard[1][2] == "X") && (gameBoard[2][2] == "X"))  ||   ((gameBoard[0][0] == "X") && (gameBoard[1][1] == "X") && (gameBoard[2][2] == "X"))  ||   ((gameBoard[0][2] == "X") && (gameBoard[1][1] == "X") && (gameBoard[2][0] == "X"))  ){
            return 1;
        }else if ( ((gameBoard[0][0] == "O") && (gameBoard[0][1] == "O") && (gameBoard[0][2] == "O"))  ||   ((gameBoard[1][0] == "O") && (gameBoard[1][1] == "O") && (gameBoard[1][2] == "O"))  ||   ((gameBoard[2][0] == "O") && (gameBoard[2][1] == "O") && (gameBoard[2][2] == "O"))  ||   ((gameBoard[0][0] == "O") && (gameBoard[1][0] == "O") && (gameBoard[2][0] == "O"))  ||   ((gameBoard[0][1] == "O") && (gameBoard[1][1] == "O") && (gameBoard[2][1] == "O"))  ||   ((gameBoard[0][2] == "O") && (gameBoard[1][2] == "O") && (gameBoard[2][2] == "O"))  ||   ((gameBoard[0][0] == "O") && (gameBoard[1][1] == "O") && (gameBoard[2][2] == "O"))  ||   ((gameBoard[0][2] == "O") && (gameBoard[1][1] == "O") && (gameBoard[2][0] == "O"))  ){
            return -1;
        }else if ( (R00.getText() != "") && (R01.getText() != "") && (R02.getText() != "") && (R10.getText() != "") && (R11.getText() != "") && (R12.getText() != "") && (R20.getText() != "") && (R21.getText() != "") && (R22.getText() != "")){
            return 0;
        }else{
            return 2; //Game not finished
        }
        
    }

    public void AImove(){
        if (state.winstate != true){  
            int score;  
            int bestScore = -1000000;
            int bestmove[] = new int[2];
            boolean isHumanMax;

            //Checks every possibel open go and sets it the ai go --> than runs minimax to see what score this will produce
            // resetts the position before chekcing if the score is better than any previous gos
            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 3; j ++){
                    if (gameBoard[i][j] == ""){
                        gameBoard[i][j] = ai;
                        if(App.aiToggle == 1){
                            isHumanMax = false;
                            score = minimax(gameBoard, 9-counter, isHumanMax);
                        }else{
                            isHumanMax = true;
                            score = minimax(gameBoard, 9-counter, isHumanMax);
                        }
                        gameBoard[i][j] = "";
                        if (score > bestScore){
                            bestScore = score;
                            bestmove[0] = i;
                            bestmove[1] = j;
                        }
                    }
                }
            }

            int x = bestmove[0];
            int y = bestmove[1];
            gameBoard[x][y] = ai;
            testArray();
            boardUpdate();
            counter+=1;
        }
    }

    /*
     * X: 1
     * O: -1
     * tie = 0
     * no winner = 2
     */
    public int minimax(String[][] board, int depth, boolean ismaximising){
        int result = boardWinChecker();
        if (result != 2 || depth == 0){
            return result;
        }

        if (ismaximising == true){
            int bestScore = -10000;
            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 3; j ++){
                    if(gameBoard[i][j] == ""){
                        gameBoard[i][j] = "X";
                        int score = minimax(gameBoard, depth -1, false);
                        gameBoard[i][j] = "";
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        } else{
            int bestScore = 100000;
            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 3; j ++){
                    if(gameBoard[i][j] == ""){
                        gameBoard[i][j] = "O";
                        int score = minimax(gameBoard, depth -1, true);
                        gameBoard[i][j] = "";
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }

    }

    public void boardUpdate(){
        if((counter % 2) == 0){
            turn = "O";
            lblturnDisplay.setText(turn + " Turn");

        }else{
            turn = "X";
            lblturnDisplay.setText(turn + " Turn");
        }
        
        R00.setText(gameBoard[0][0]);R01.setText(gameBoard[0][1]);R02.setText(gameBoard[0][2]);
        R10.setText(gameBoard[1][0]);R11.setText(gameBoard[1][1]);R12.setText(gameBoard[1][2]);
        R20.setText(gameBoard[2][0]);R21.setText(gameBoard[2][1]);R22.setText(gameBoard[2][2]);

        if (gameBoard[0][0] != ""){
            oo.setVisible(false);
        }
        if (gameBoard[0][1] != ""){
            o1.setVisible(false);
        }
        if (gameBoard[0][2] != ""){
            o2.setVisible(false);
        }
        
        if (gameBoard[1][0] != ""){
            Io.setVisible(false);
        }
        if (gameBoard[1][1] != ""){
            I1.setVisible(false);
        }
        if (gameBoard[1][2] != ""){
            I2.setVisible(false);
        }

        if (gameBoard[2][0] != ""){
            two0.setVisible(false);
        }
        if (gameBoard[2][1] != ""){
            two1.setVisible(false);
        }
        if (gameBoard[2][2] != ""){
            two2.setVisible(false);
        }

        winchecker();
    }
}


import javax.swing.*;
import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SettingsMenu extends JFrame implements ActionListener {
    App app = new App();

    SettingsMenu(){
        this.setTitle("Settings Menu");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Window title
        JLabel title = new JLabel();
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.TOP);
        title.setBounds(0,0,250,250);
        title.setText("Settings");
        title.setFont(new Font("Ariel",Font.BOLD,20));

        //window BackButton
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(e -> {
            this.dispose();
            try {
                App.main(null);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                System.out.println("Error");
            }
        });
        btnBack.setSize(75, 30);
        btnBack.setLocation(0, 0);

        //Window toggle text
        JLabel txtToggle = new JLabel();
        txtToggle.setFont(new Font("Ariel",Font.PLAIN,12));
        txtToggle.setText(updateTxtToggle());
        txtToggle.setHorizontalAlignment(JLabel.CENTER);
        txtToggle.setVerticalAlignment(JLabel.CENTER);
        txtToggle.setBounds(0, 50, 250, 75);

        //Window  toggle button
        JButton btnToggle = new JButton("Toggle AI Opponent");
        btnToggle.addActionListener(e -> {
            if (app.aiToggle == 1){
                app.aiToggle = 2;
            }else {
                app.aiToggle = 1;
            }
            txtToggle.setText(updateTxtToggle());
        });
        btnToggle.setSize(150, 50);
        btnToggle.setLocation(50, 150);

        //Adding components to window
        this.add(btnBack); this.add(title);
        this.add(btnToggle);this.add(txtToggle);
    }

    public String updateTxtToggle(){
        String txtBase = "AI Player: ";
        String txt;
        if (app.aiToggle == 1){
            txt = txtBase + "X";
        }else{
            txt = txtBase + "O";
        }

        return txt;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
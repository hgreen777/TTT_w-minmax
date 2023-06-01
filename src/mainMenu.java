import javax.swing.*;
import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class mainMenu extends JFrame implements ActionListener {
    App app = new App();
    
    mainMenu(){
        JLabel title = new JLabel();
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.TOP);
        title.setBounds(0,0,250,250);
        title.setText("Select Gamemode");
        title.setFont(new Font("Ariel",Font.BOLD,20));

        JButton btn1Player = new JButton("1 Player");
        btn1Player.addActionListener(e -> {
            this.dispose();
            app.onePlayerStartup();
        });
        btn1Player.setSize(100,50);
        btn1Player.setLocation(75, 50);

        JButton btn2Player = new JButton("2 Player");
        btn2Player.addActionListener(e -> {
                this.dispose();
                app.twoPlayerStartup();
        });
        btn2Player.setSize(100,50);
        btn2Player.setLocation(75, 125);


        this.add(title);
        this.add(btn1Player);
        this.add(btn2Player);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
    }
}

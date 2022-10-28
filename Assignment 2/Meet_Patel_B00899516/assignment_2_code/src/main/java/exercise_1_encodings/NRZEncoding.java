package exercise_1_encodings;

import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 * @author Meet Patel (B00899516 | mt631537@dal.ca)
 */

public class NRZEncoding extends JFrame {
    static String binaryString;
    public NRZEncoding()
    {
        setSize(600,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    public void paint(Graphics g)
    {
        g.setColor(Color.black);
        g.drawLine(0,150,1000,150);
        int num = 30;
        for(int i=0;i<binaryString.length();i++){
            if(binaryString.charAt(i)=='0'){
                g.drawLine(i*num,150+30,i*num+30,150+30);
                if (i + 1 < binaryString.length()) {
                    if (binaryString.charAt(i + 1) == '1') {
                        g.drawLine(i * num + 30, 150 - 30, i * num + 30, 150 + 30);
                    }
                }
            }else if(binaryString.charAt(i)=='1'){
                g.drawLine(i*num, 150-30, i*num+30, 150-30);
                if (i + 1 < binaryString.length()) {
                    if (binaryString.charAt(i + 1) == '0') {
                        g.drawLine(i * num + 30, 150 + 30, i * num + 30, 150 - 30);
                    }
                }
            }
        }
    }
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter Binary String.");
        binaryString = scanner.nextLine();
        new NRZEncoding().setVisible(true);
        System.out.println("Waveform Generated Successfully!");
    }
}

package practise_use_slider;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;

public class SetBackgroundColor extends JFrame{
	
	JPanel panel;
	JSlider slide;
	JLabel lab;
	JLabel photo_lab;
	JButton open;
	BufferedImage img = null;
	ImageIcon ico;
	int width, height;
	
	
	public SetBackgroundColor(){
	
		
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(800,500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
		open = new JButton("Open");
		JButton save = new JButton("Save");
		photo_lab = new JLabel();
		
		lab = new JLabel();
		slide = new JSlider(JSlider.HORIZONTAL,0,255,0);
		
		panel.add(lab);
		panel.add(photo_lab);
		panel.add(Box.createVerticalGlue());
		panel.add(Box.createRigidArea(new Dimension(0,50)));
		panel.add(open);
		panel.add(Box.createRigidArea(new Dimension(0,10)));
		panel.add(slide);
		add(panel);
		
		
		
		pack();
		setVisible(true);
		
		open.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JFileChooser choose = new JFileChooser();
				int result = choose.showOpenDialog(null);
				if(result == JFileChooser.APPROVE_OPTION){
					File f = choose.getSelectedFile();
					try{
						
						img = ImageIO.read(f);
						width = img.getWidth();
						height = img.getHeight();
						if(width >= 500){
							JFrame window2 = new JFrame();
							JPanel p2 = new JPanel();
							window2.add(p2);
							window2.setVisible(true);
							window2.setDefaultCloseOperation(window2.EXIT_ON_CLOSE);
							window2.setSize(300, 100);
							JLabel command = new JLabel("Zdjecie jest zbyt duze wybierz inne");
							p2.add(command);
						}else{
						
						ico = new ImageIcon(img.getScaledInstance(100, 250, Image.SCALE_DEFAULT));
						photo_lab.setIcon(ico);
						}
					}catch(IOException ea){
						ea.printStackTrace();
					}
				}
			}
		});
		
		//ImageIcon ico = new ImageIcon(img.getScaledInstance(322, 642, Image.SCALE_DEFAULT));
		
		
		lab.setOpaque(true);
		slide.setMajorTickSpacing(10);
		slide.setMinorTickSpacing(5);
		slide.setPaintLabels(true);
		slide.setPaintTicks(true);
		slide.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){
				JSlider source = (JSlider)e.getSource();
				
				ImageIcon ico2 = new ImageIcon(img.getScaledInstance(100+source.getValue(), 250+source.getValue(), Image.SCALE_DEFAULT));
				photo_lab.setIcon(ico2);
			}});
		}
	
	public void changeSizeImage(){
		
	}
	public static void main(String[] args){
		//SwingUtilities.invokeLater(new Runnable(){
			/*public void run(){
				new SetBackgroundColor();
			}
		});*/
		SetBackgroundColor set = new SetBackgroundColor();
		
	}
	
	

}

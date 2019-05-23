///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Nazwa aplikacji: Image Resizer
//Autor: Damian Kołakowski
//Czas publikacji: Maj 2019
//Opis aplikacji: Image Resizer jest modulem ktorego glownym zadaniem jest zmiana rozmiarow wczytanego przez uzytkownika zdjecia
//niezalezenie od rozmiaru oryginalnego. 
//
//Poprzez kontrolke JSlider uzytkownik ma mozliwosc powiekszania czy tez pomniejsza zdjecia wczytanego.
//
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

package practise_use_slider;

import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.*;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class ResizeSelectedPhoto extends JFrame {

	private JPanel contentPane;
	BufferedImage img = null;
	ImageIcon ico,ico2;
	JLabel image;
	int width, height, imageH2,imageW2;
	public JSlider slider;

	//////////////////////////////Load file by the FileChooser and display on Panel//////////////////////////////////////////////
	public void openSource()
	{
		JFileChooser choose = new JFileChooser();
		int result = choose.showOpenDialog(null);
		if(result == JFileChooser.APPROVE_OPTION){
			File f = choose.getSelectedFile();
			try{
				
				img = ImageIO.read(f);
				width = img.getWidth();
				height = img.getHeight();
				
				//Calculate right measure of photo
				int a = img.getWidth() - image.getWidth() / 2;
				imageW2 = img.getWidth() - a;
				
				int b = img.getHeight() - image.getHeight() / 2;
				imageH2 = img.getHeight() - b;
				ico = new ImageIcon(img.getScaledInstance(imageW2,imageH2,Image.SCALE_DEFAULT));
				
				image.setIcon(ico);
				
			}catch(IOException ea){
				ea.printStackTrace();
			}
		}
	}
	
	public void changeImageMeasure()
	{
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				JSlider slider = (JSlider)e.getSource();
				ico2 = new ImageIcon(img.getScaledInstance(ico.getIconWidth()+slider.getValue(), ico.getIconHeight()+slider.getValue(), Image.SCALE_DEFAULT));
				//System.out.println(ico.getIconWidth()+slider.getValue()+ "" + ico.getIconHeight()+slider.getValue());
				image.setIcon(ico2);
			}
		});
		
	}
	
	public void saveImagetoFile() throws IOException
	{
		//Part in build
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResizeSelectedPhoto frame = new ResizeSelectedPhoto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ResizeSelectedPhoto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 544, 382);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		image = new JLabel("",JLabel.CENTER);
		image.setOpaque(true);
		image.setBounds(10, 11, 480, 220);
		panel.add(image);
		
		JButton btnNewButton = new JButton("Open Browser");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				openSource();
			}
		});
		btnNewButton.setBounds(180, 242, 107, 23);
		panel.add(btnNewButton);
		
		slider = new JSlider();
		slider.setMaximum(50);
		slider.setMajorTickSpacing(10);
		slider.setMinorTickSpacing(5);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setValue(0);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				slider.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent e) {
						
						changeImageMeasure();
					}
				});
			}
		});
		slider.setBounds(45, 276, 394, 47);
		panel.add(slider);
	}
}

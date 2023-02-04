import java.awt.FlowLayout;  
import java.awt.event.ItemEvent;  
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;  
import javax.swing.JToggleButton;  
import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import java.lang.Math;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
  
public class ButtonTest extends JFrame implements ItemListener {
	
	 static boolean buttonState;
	 static boolean WateroffState;
	static double mWaterLevelML;
	static double mWaterLevelCM;
	static JProgressBar progressBar = new JProgressBar(0, 2800);   //progress bar min 0 ml, max 2800 ml
	 static JLabel lblNewLabel_2 = new JLabel("New label");
	 static 	JLabel lblNewLabel_3 = new JLabel("New label");
	 static int WaterMLTemp;
	 
	 //we declare them static because we use them globally
		
	 static void UpdateVolume()
     {
     	
     	if (mWaterLevelCM<=8) {
				//when less than 8 then is in the cone, detail see
				//https://jonkopinguniversity-my.sharepoint.com/:w:/g/personal/liyu21ka_student_ju_se/ESifvKKjllRBudyUd_TDJQgBK51ubSfp3KJl70MGRA2Jnw?e=kdR0xA
				
			    mWaterLevelML=((11.5811+125.4827+ Math.sqrt(11.5811*125.4827))/3)*mWaterLevelCM;
			    //if the water level is less than or equal to 8, it is within the lower cone, the valume calculate like this
			    lblNewLabel_2.setText(String.valueOf((mWaterLevelCM))) ;
				lblNewLabel_3.setText(String.valueOf((mWaterLevelML))) ;
			}
			 
			if (mWaterLevelCM>8) {
				//above the cone, detail see
				//https://jonkopinguniversity-my.sharepoint.com/:w:/g/personal/liyu21ka_student_ju_se/ESifvKKjllRBudyUd_TDJQgBK51ubSfp3KJl70MGRA2Jnw?e=kdR0xA
			    mWaterLevelML= 125.4827*(mWaterLevelCM-8)+467.16;
			    //if the waterlevel is greater than 8, then volume we added more is on the upper cylinder
			    lblNewLabel_2.setText(String.valueOf((mWaterLevelCM))) ;
				lblNewLabel_3.setText(String.valueOf((mWaterLevelML))) ;
				
			}
     }
	 
    public static void main(String[] args) {  
        new ButtonTest();  
        buttonState=false;   //for initial the values, else will be null
        WateroffState=false;
        mWaterLevelML=0;
        mWaterLevelCM=0;
        
       
        
while (true) {    
	System.out.println(buttonState);   //display state of ON/OFF button
	System.out.println(WateroffState);  //display state of wateroff button
	//System.out.println(Math.round(5.443));

	 try{Thread.sleep(500);}catch(InterruptedException e){System.out.println(e);}  
	 //need to use try catch for Thread.sleep(500) which delay 0.5s
	 
	 while (buttonState==true)   //if state is on
	 {
	 	//----------------------------------
		 
		 if (mWaterLevelML <= 150) {
			
			    // water tank is empty when "mWaterLevelML"=0, we have safety range

			while (mWaterLevelML <= 2090)  //if water is less than 150ml then to we fill until reach 2100ml, below can fill not precise
			{
		
				 if(buttonState==false) { break;	 }   //actively listen to stop button
				 
			    progressBar.setValue((int) (mWaterLevelML + 0.25));  
			    //set value need to be int and we have a double for mWaterLevelML, add 4 because we will add later, display first
						System.out.println(mWaterLevelML);
						System.out.println(mWaterLevelCM);
						System.out.println(buttonState);   //here

						// delay the thread
						try{Thread.sleep(100);}catch(InterruptedException e){System.out.println(e);}  
						//need to use try catch for Thread.sleep(500) which delay 0.5s
						
						mWaterLevelCM += 0.25;
						//water level go up
						
						UpdateVolume();
						
						
			}
			while (2090 <mWaterLevelML & mWaterLevelML <= 2100)  //to be more precise close to the top
			{
				 if(buttonState==false) { break;	 }   //actively listen to stop button
				 
			    progressBar.setValue((int) (mWaterLevelML + 0.01));  
			    //set value need to be int and we have a double for mWaterLevelML, add 4 because we will add later, display first
						System.out.println(mWaterLevelML);
						System.out.println(mWaterLevelCM);

						// delay the thread
						try{Thread.sleep(100);}catch(InterruptedException e){System.out.println(e);}  
						//need to use try catch for Thread.sleep(500) which delay 0.5s
						
						mWaterLevelCM += 0.01;
						//water level go up
						
						UpdateVolume();
						
						
			}
			
			}

		//----------------
			while (WateroffState & mWaterLevelML>160) {   //if water off button is pressed and water level greater than 0
				
				 if(buttonState==false) { break;	 }   //actively listen to stop button
				 
				mWaterLevelCM -=0.20;
				System.out.println("wateroff");
				//calculation see https://jonkopinguniversity-my.sharepoint.com/:w:/g/personal/liyu21ka_student_ju_se/ESifvKKjllRBudyUd_TDJQgBK51ubSfp3KJl70MGRA2Jnw?e=kdR0xA
				UpdateVolume();
				
				 progressBar.setValue((int) (mWaterLevelML -0.20));
			
				 try{Thread.sleep(100);}catch(InterruptedException e){System.out.println(e);}  
				}
			
			
			
			while (mWaterLevelML>=150 &WateroffState & mWaterLevelML<=160) {   //if water off button is pressed and water level greater than 0
				
				 if(buttonState==false) { break;	 }   //actively listen to stop button
				
				mWaterLevelCM -=0.01;
				System.out.println("wateroff");
				//calculation see https://jonkopinguniversity-my.sharepoint.com/:w:/g/personal/liyu21ka_student_ju_se/ESifvKKjllRBudyUd_TDJQgBK51ubSfp3KJl70MGRA2Jnw?e=kdR0xA
				UpdateVolume();
				
			//	WaterMLTemp=(int) Math.round(mWaterLevelML);
				
				if (Math.round(mWaterLevelML)==150) {					try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println(e);} 	}
				//show the teacher water stopped at 150 ml
					
				 progressBar.setValue((int) (mWaterLevelML -0.01));
			
				 try{Thread.sleep(100);}catch(InterruptedException e){System.out.println(e);}  
				}
			//----------------
		 //------------------------------
	 }
	 
	}


    }  
    
   
    private JToggleButton button;  
    private JToggleButton button2;
    private JLabel lblNewLabel;
    private final JLabel lblNewLabel_4 = new JLabel("Made by Yushuo (Kevin)");
    private final JButton btnNewButton = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");
    
    
    
    ButtonTest() {  
        setTitle("JToggleButton with ItemListener Example");
        setJToggleButton();  
        setAction();  
        setSize(641, 525);  
        setVisible(true);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    }  
    
    private void setJToggleButton() {  
        getContentPane().setLayout(null);
        button = new JToggleButton("ON");  
        button.setBounds(52, 5, 15, 21);
        getContentPane().add(button); 
        
        button2 = new JToggleButton("water off");  
        button2.setBounds(40, 388, 139, 60);
        getContentPane().add(button2);
		Image img = new ImageIcon(this.getClass().getResource("/tank.jpg")).getImage();
		
		
		progressBar.setStringPainted(true);
		progressBar.setOrientation(SwingConstants.VERTICAL);
		progressBar.setBounds(279, 169, 47, 227);
		getContentPane().add(progressBar);
		
		JLabel label = new JLabel ("");
		label.setIcon(new ImageIcon(img));
		label.setBounds(213, 169, 162, 240);
		getContentPane().add(label);
		
		lblNewLabel = new JLabel("CM");
		lblNewLabel.setBounds(415, 217, 45, 13);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ML");
		lblNewLabel_1.setBounds(415, 274, 45, 13);
		getContentPane().add(lblNewLabel_1);
		
		
		lblNewLabel_2.setBounds(470, 217, 88, 13);
		getContentPane().add(lblNewLabel_2);
		lblNewLabel_2.setText(String.valueOf((mWaterLevelML))) ;
		
	
		lblNewLabel_3.setBounds(456, 274, 102, 13);
		getContentPane().add(lblNewLabel_3);
		lblNewLabel_3.setText(String.valueOf((mWaterLevelCM))) ;
		lblNewLabel_4.setBounds(363, 419, 162, 29);
		
		getContentPane().add(lblNewLabel_4);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 buttonState=true;
			}
		});
		btnNewButton.setBounds(46, 201, 133, 44);
		
		getContentPane().add(btnNewButton);
		btnStop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonState=false;
			}
		});
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnStop.setBounds(46, 309, 133, 44);
		
		getContentPane().add(btnStop);
    }  
    private void setAction() {    //set the button's states
        button.addItemListener(this);  
        button2.addItemListener(this);  
    }  
    public void itemStateChanged(ItemEvent eve) { 
    	
        if (button.isSelected())  {  //on off button
            button.setText("OFF");  
       // buttonState=true;    we use start and stop button now
        }
        else  {
            button.setText("ON");  
       // buttonState=false;   we use start and stop button now
        }
        
        if (button2.isSelected())  {   //water off button 
           
        	WateroffState=true;}
        else  {
            
        WateroffState=false;}
    }  
}  



package HW01;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;
public class NeuralNetworks implements ActionListener,MouseListener{
	Dimension ScreenSize,FrameSize; 
	double[][] X= new double[4][2];
	double[] Y =new double[4];
	double Xtrain[][]; //xtrain
	double Ytrain[];//       D2
	int D1 = 0;//           1 2 
	           //        D1 3 4 
	int D2 = 2;//           5 6 
	int tD1 = 0;
	int tD2 = 0;
	double Xtest[][];
	double Ytest[];	
    int gwidth=1000,gheigh=800,gx,gy;
    boolean alltrain=false;
    JFrame demo = new JFrame("HW");
    JDialog paint = new JDialog(demo,"paint");
    JTextField learn=new JTextField("請輸入學習率",20);//學習率
    JTextField condition=new JTextField("請輸入收斂條件",20);//收斂條件
    JButton enter = new JButton("輸入");
    JButton choose = new JButton("選擇檔案");
    JLabel outputTrainRate = new JLabel();
    JLabel outputTestRate = new JLabel();
    JLabel outputweights = new JLabel();
    File selectedFile;
    private double l;
    private int end;
    paint panel= new paint();
	Perceptron p = new Perceptron();
	DecimalFormat df=new DecimalFormat("#.###");
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new NeuralNetworks();     
        
	}
	public NeuralNetworks()
	{
		demo.setSize(500,400);
        demo.setResizable(false);
        demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        paint.setSize(gwidth, gheigh);
        paint.setResizable(false);
        
        outputTrainRate.setBounds(0, 100, 500, 40);
        outputTestRate.setBounds(0, 150, 500, 40);
        outputweights.setBounds(0, 200, 500, 40);
        enter.setBounds(0,50,60,30);
        choose.setBounds(100,0,100,20);
        choose.addActionListener(this);
        learn.addMouseListener(this);
        condition.addMouseListener(this);
        enter.addActionListener(this);
        learn.setBounds(0,0,100,20);
        condition.setBounds(0,25,100,20);
        demo.getContentPane().add(learn);
        demo.getContentPane().add(condition);
        demo.getContentPane().add(enter);
        demo.getContentPane().add(choose);
        outputTrainRate.setText("訓練辨識率: "+p.getTrainRate());
        outputTestRate.setText("測試辨識率: "+p.getTestRate());
        outputweights.setText("鍵結值: "+df.format(p.weights[0])+", "+df.format(p.weights[1])+", "+df.format(p.weights[2]));
        
        demo.getContentPane().add(outputTrainRate);
        demo.getContentPane().add(outputTestRate);
        demo.getContentPane().add(outputweights);

        demo.getContentPane().setLayout(new BorderLayout());
        FrameSize = paint.getSize();
        ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
        paint.setLocation((ScreenSize.width-FrameSize.width)/2,(ScreenSize.height-FrameSize.height)/2);
        gx=(ScreenSize.width-gwidth)/2; 
		gy=(ScreenSize.height-gheigh)/2; 
		
	    

		//System.out.println(gx);
		//System.out.println(gy);
		
        demo.setVisible(true);
	}
	public void scan()
	{
		int i = 0;
        String[] strs = new String[10000];
        Scanner sc = null;
        try{
              sc = new Scanner(selectedFile);
          }catch(FileNotFoundException e){  
              e.printStackTrace();  
              System.exit(0);
          }
        while(sc.hasNext()){
          strs[i] = sc.next();
          
            i++;
          }
        
        if(i==12)
        	alltrain = true;
        if(alltrain == true)
        {
        	Xtrain=new double[4][2];
        	Ytrain = new double[4];
        	int x=0,j,k=0;
            for(i=0;i<4;i++)
            {
            	for(j=0;j<2;j++)
            	{
            		if(k%3==2){
            			k++;
            		}
            		
            		Xtrain[i][j] = Double.parseDouble(strs[k]);
            		k++;   				
            	}
            }
            k=0;
            for(i=0;i<4;i++)
            {
            	while(true)
            	{
            		if(k%3==2){
            			Ytrain[i] = Double.parseDouble(strs[k]);
            			k++;
            			break;
                	}
            		k++;
            	}
            	//System.out.println(Y[i]);
            }
        }
        else
        {
        	 D1 = i*2/3;//all data
             tD1 = i/3;
             sc.close();
             Xtrain=new double[D1/3][D2];
             Xtest = new double[tD1-D1/3][D2];
             int x=0,j,k=0;
             for(i=0;i<D1/2;i++)
             {
             	if(i<D1/3)
             	{
             		for(j=0;j<D2;j++)
                 	{
                 		if(k%3==2){
                 			k++;
                 		}
                 		
                 		Xtrain[i][j]= Double.parseDouble(strs[k]);
                 		//System.out.println(Xtrain[i][j]);
                 		k++; 
                 		
                 	}
             		
             		
             	}
             	else
             	{
             		
             		for(j=0;j<D2;j++)
                 	{
                 		if(k%3==2){
                 			k++;
                 		}
                 		
                 		
                 		Xtest[i-D1/3][j]= Double.parseDouble(strs[k]);
                 		k++; 
                 		
                 	}
             	}
             	
             }
           
             
             k=0;
             Ytrain = new double[D1/3];
             Ytest = new double[tD1-D1/3];
             for(i=0;i<D1/2;i++)
             {
             	if(i<D1/3)
             	{
             		while(true)
                 	{
                 		if(k%3==2){
                 			
                 			Ytrain[i] = Double.parseDouble(strs[k]);
                 			k++;
                 			break;
                 			
                 			
                     	}
                 		k++;
                 		
                 	}
             		
             	}
             	else
             	{
             		while(true)
                 	{
             			
                 		if(k%3==2){
                 			Ytest[i-D1/3] = Double.parseDouble(strs[k]);
                 			k++;
                 			break;
                     	}
                 		k++;
                 	}
             	}
             	
             }
        }
       
        
        
	}
	public void actionPerformed(ActionEvent e) { 
		if(e.getSource()==enter)
		{   
			
			paint.repaint();
			l = Double.parseDouble(learn.getText());
			if(condition.getText().indexOf("%")!=-1)
			{
				StringBuffer sb = new StringBuffer(condition.getText());
				sb.deleteCharAt(condition.getText().length()-1);
				condition.setText(sb.toString());
				end = Integer.parseInt(condition.getText());
				p.setTarget(end/100);
				end =100000;
			}
			else
				end = Integer.parseInt(condition.getText());
			
			double lv=0,sv=0;
			for(int i =0;i<Ytrain.length;i++)
			{
				lv=Ytrain[0];
				if(Ytrain[i]>lv){
					lv = Ytrain[i];
					sv = Ytrain[0];
				}
				
			}
	
			p.setValue(lv, sv);
			p.Train(Xtrain,Ytrain,-1.0, l,end );
			double c = p.weights[0];
			double a = p.weights[1];
			double b = p.weights[2];
			for(int i=0;i<Xtrain.length;i++)
			{
				for(int j=0;j<2;j++)
				{
					if(Xtrain[i][j]>=5.0)
					{
						panel.setmul();
					}
				}
			}
			if( alltrain== false)
			{
				
				p.Test(Xtest, Ytest);
				panel.setpoint2(Xtest);
			}		
			panel.setnum(a,b,c);
			panel.setpoint1(Xtrain);
			paint.add(panel);
			outputTrainRate.setText("訓練辨識率: "+p.getTrainRate());
			outputTestRate.setText("測試辨識率: "+p.getTestRate());
	        outputweights.setText("鍵結值: "+df.format(p.weights[0])+", "+df.format(p.weights[1])+", "+df.format(p.weights[2]));
			
	        demo.setVisible(true);
	        paint.setVisible(true);   
		}
		
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==learn)
		{
			learn.setText("");
		}
		if(e.getSource()==condition)
		{
			condition.setText("");
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}

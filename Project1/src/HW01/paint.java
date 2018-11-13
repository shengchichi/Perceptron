package HW01;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

public class paint extends JPanel{
	 private double a,b,c;
	 private double point[][];
	 private double point2[][];
	 private int mul =100;
	 boolean paintp2=false;
	 @Override
	 protected void paintComponent(Graphics g)
	  {
		 super.paintComponent(g);
		 g.setColor(Color.BLACK);
		 Graphics2D g2 = (Graphics2D) g;
		 g2.translate(500, 400);
		 g2.draw(new Line2D.Double(-250.0,0.0,250.0,0.0));
		 g2.draw(new Line2D.Double(0.0,250.0,0.0,-250.0));
		 g2.draw(new Line2D.Double(-250.0,.0,250.0,0.0));
		 g2.setColor(Color.RED);
		 g2.draw(new Line2D.Double(-200.0,(c*mul+200*a)/-b,200.0,(c*mul-200*a)/-b));
		 for(int i =0;i<point.length;i++)
		 {
			 Ellipse2D.Double shape = new Ellipse2D.Double(mul*point[i][0], -mul*point[i][1], 5.0, 5.0);
			 if(a*point[i][0]+b*point[i][1]-c>0){
				g2.setColor(Color.GREEN);
				g2.fill(shape);
			 }
			 else
			 {
				 g2.setColor(Color.BLUE);
					g2.fill(shape);
			 }
		 }
		 if(paintp2==true)
		 {
			 System.out.println("dd");
			 for(int i =0;i<point2.length;i++)
			 {
				 Ellipse2D.Double shape = new Ellipse2D.Double(mul*point2[i][0], -mul*point2[i][1], 5.0, 5.0);
				 if(a*point2[i][0]+b*point2[i][1]-c>0){
					g2.setColor(Color.GREEN);
					g2.fill(shape);
				 }
				 else
				 {
					 g2.setColor(Color.BLUE);
						g2.fill(shape);
				 }
			 }
		 }
		 
			 
	  }
	 protected void setnum(double i,double j, double k)
	 {
		 
		 a = i;
		 b = j;
		 c = k;

	 }
	
	 protected void setpoint1(double a[][])
	 {
		point = new double[a.length][2];
		for(int i=0;i<a.length;i++)
		{
			for(int j=0;j<2;j++){
				point[i][j] = a[i][j];
				//System.out.print(a[i][j]+" ");
			}
			//System.out.println();

		}
		
		
		 
	 }
	 protected void setpoint2(double a[][])
	 {
		
		point2 = new double[a.length][2];
		for(int i=0;i<a.length;i++)
		{
			for(int j=0;j<2;j++){
				point2[i][j] = a[i][j];
				//System.out.print(a[i][j]+" ");
			}
			//System.out.println();
			
		}
		paintp2 = true;
	 }
	 protected void setmul()
	 {
		 this.mul = 30;
	 }
}

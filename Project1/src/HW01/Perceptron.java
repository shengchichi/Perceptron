package HW01;

import java.util.Random;

public class Perceptron {
	double[] weights={-1,0,0};
	private double threshold;
    private double[][] x;
    private double trainSuccessRate = 0;//訓練成功率
    private double testSuccessRate = 0;//訓練成功率
	private double targetRate = 1;
	private double largerValue,smallerValue;
	
	public Perceptron()
	{
		
	}
    public void Train(double[][] inputs, double[] outputs, double threshold, double learn, int n)
    {
    	
    	
    	
    	this.threshold = threshold;
        int p = outputs.length;
        weights = new double[3];//w0,w1,w2
        x =new double[inputs.length][3];
        Random r = new Random();
        weights[0] = threshold; 
       
        
        //initialize weights
        for(int i=1;i<3;i++)
        {
            weights[i] = r.nextDouble();
            
        }
        //x[]
        for(int i=0;i<inputs.length;i++)
        {
        	x[i][0] = -1;
        	x[i][1] = inputs[i][0];
        	x[i][2] = inputs[i][1];
        }
        double times=0;
        for(int i=0;i<n;i++)
        {
        	
            for(int j =0;j<inputs.length;j++)
            {
            	//System.out.println(inputs[j]);
                double output = Cal(x[j],weights);
                double error = outputs[j] - output;
                for(int k = 0;k<3;k++)
                {
                    double delta = learn * x[j][k] * error;
                    weights[k] += delta;
                }
               
                if (error == 0)
					times++;
                
            }
            trainSuccessRate = times / ((i + 1) * p);//成功次數/總次數
            if(trainSuccessRate>=targetRate)
            {
            	testSuccessRate = trainSuccessRate;
            	return;
            }
            	
        }  

    }
    public void Test(double[][] inputs, double[] outputs)
    {
    	double[][] xt = new double[inputs.length][3];
		for (int i = 0; i < inputs.length; i++) {
			xt[i][0] = -1;
			xt[i][1] = inputs[i][0];
			xt[i][2] = inputs[i][1];
		}
		double times = 0;
		for (int i = 0; i < inputs.length; i++) {
			double output = Cal(xt[i], weights);
			double error = outputs[i] - output;
			
			if (error == 0)
				times++;
			
		}
		testSuccessRate = times / inputs.length;
    }

    public double Cal(double[] x,double[] w)
    {
        double result;
        result = x[0] * w[0] + x[1] * w[1] + x[2] * w[2];
        if(result>=0)
            return largerValue;
        else
            return smallerValue;
    }
    public void setValue(double largerValue,double smallerValue)
    {
    	this.largerValue = largerValue;
    	this.smallerValue =smallerValue;
    }
    public void setTarget(double targetRate)
    {
    	this.targetRate = targetRate;
    }
    public double getTrainRate()
    {
    	return trainSuccessRate;
    }
    public double getTestRate()
    {
    	return testSuccessRate;
    }
  
}

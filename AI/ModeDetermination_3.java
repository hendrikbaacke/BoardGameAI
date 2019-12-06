package AI;

public class ModeDetermination_3 {


    private double[] mode1={3,2,6,1.8,0,0};
    private double[] mode2={3.3,2,6,1.8,35,50*35};
    private double[] mode3={2.9,2,15,3,4,50*4};
    private double[] mode4={2.9,2,15,3,15,50*15};
    private double[] mode5={2.8,2.3,25,3,15,50*15};
    private double[] mode6={2.8,2.1,25,3,25,50*25};
    private double[] mode7={2.7,2.3,25,3,30,50*30};
    private double[] mode8={2.4,2.3,25,3,35,50*35};
    private double[] mode9={2.2,2.3,25,3,40,50*40};
    private double[] defaultMode= {1,1,1,1,1,1};


    public double[] determineMode_3(double centerDistance, double cohesion){
    	//System.out.println("closing dist " + centerDistance);
    	
        if(centerDistance<0)
            return mode1;
        if(centerDistance<5)
            return mode2;
        if(centerDistance>=5 && cohesion>=0 && cohesion<4)
            return mode3;
        if(centerDistance>=5 && cohesion>=4 && cohesion<10)
            return mode4;
        if(centerDistance>=5 && cohesion>=10 && cohesion<16)
            return mode5;
        if(centerDistance>=5 && cohesion>=16 && cohesion<22)
            return mode6;
        if(centerDistance>=5 && cohesion>=22 && cohesion<28)
            return mode7;
        if(centerDistance>=5 && cohesion>=28 && cohesion<34)
            return mode8;
        if(centerDistance>=5 && cohesion>=34)
            return mode9;

        return defaultMode;

    }

}

package AI;

import src.Board;

public class ModeDetermination_2 {
    private double DistanceM1 =  Math.sqrt(Math.pow(Board.hashBoard.get("E5").centerX- Board.hashBoard.get("G6").centerX, 2) + Math.pow(Board.hashBoard.get("E5").centerY - Board.hashBoard.get("G6").centerY, 2));

//TODO: Assign weigths

    private double[] mode1={-3000,2,6,1.8,0,0};
    private double[] mode2={-3000.3,2,6,1.8,35,50*35};
    private double[] mode3={-3000,2,15,3,4,50*4};
    private double[] mode4={-3000,2,15,3,15,50*15};
    private double[] mode5={2.8,2.3,25,3,15,50*15};
    private double[] mode6={2.8,2.1,25,3,25,50*25};
    private double[] mode7={2.7,2.3,25,3,30,50*30};
    private double[] mode8={2.4,2.3,25,3,35,50*35};
    private double[] mode9={2.2,2.3,25,3,40,50*40};
    private double[] defaultMode= {1,1,1,1,1,1};


    public double[] determineMode_2(double centerDistance, double cohesion){

        if(centerDistance> DistanceM1)
            return mode1;
        if(centerDistance<DistanceM1)
            return mode2;
        if(centerDistance<=DistanceM1 && cohesion>=0 && cohesion<4)
            return mode3;
        if(centerDistance<=DistanceM1 && cohesion>=4 && cohesion<10)
            return mode4;
        if(centerDistance<=DistanceM1 && cohesion>=10 && cohesion<16)
            return mode5;
        if(centerDistance<=DistanceM1 && cohesion>=16 && cohesion<22)
            return mode6;
        if(centerDistance<=DistanceM1 && cohesion>=22 && cohesion<28)
            return mode7;
        if(centerDistance<=DistanceM1 && cohesion>=28 && cohesion<34)
            return mode8;
        if(centerDistance<=DistanceM1 && cohesion>=34)
            return mode9;

        return defaultMode;

    }
}

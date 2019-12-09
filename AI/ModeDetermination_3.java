package AI;

public class ModeDetermination_3 {

//TODO: Assign weigths

    //1. closing Distance, 2. Cohesion, 3.breakGroup, 4. strengthenGroup, 5. Difference Opponent Marbles, 6. Difference Own Marbles


    private double[] mode1={  0    ,  0  ,  0    ,  0  ,  0  ,  0 }; //get to the middle, except we can kill

    private double[] mode2={  -0.2  ,  50 ,  200  ,  0  ,  0    ,  -1000 }; //1.breaking groups 2.cohesion

    private double[] mode3={  -0.2  ,  0  ,  0    ,  0  ,  0    ,  -1000 };

    private double[] mode4={  -1    ,  0  ,  0    ,  0  ,  500  ,  -1000 };

    private double[] mode5={  -0.2  ,  50 ,  200  ,  0  ,  0    ,  -1000 };

    private double[] mode6={  -0.2  ,  0  ,  0    ,  0  ,  0    ,  -1000 };

    private double[] mode7={  -1    ,  0  ,  0    ,  0  ,  500  ,  -1000 };

    private double[] mode8={  -0.2  ,  50 ,  200  ,  0  ,  0    ,  -1000 };

    private double[] mode9={  -0.2  ,  0  ,  0    ,  0  ,  0    ,  -1000 };



    private double[] defaultMode= {1,1,1,1,1,1};

    public double[] determineMode_3(double centerDistance, double cohesion){

        //    if(centerDistance> DistanceM1)

        return mode1;

//        if(centerDistance<DistanceM1)
//            return mode2;
//        if(centerDistance<=DistanceM1 && cohesion>=0 && cohesion<4)
//            return mode3;
//        if(centerDistance<=DistanceM1 && cohesion>=4 && cohesion<10)
//            return mode4;
//        if(centerDistance<=DistanceM1 && cohesion>=10 && cohesion<16)
//            return mode5;
//        if(centerDistance<=DistanceM1 && cohesion>=16 && cohesion<22)
//            return mode6;
//        if(centerDistance<=DistanceM1 && cohesion>=22 && cohesion<28)
//            return mode7;
//        if(centerDistance<=DistanceM1 && cohesion>=28 && cohesion<34)
//            return mode8;
//        if(centerDistance<=DistanceM1 && cohesion>=34)
//            return mode9;
//
//        return defaultMode;



    }

}

package AI.WeightOptimisation;

import AI.ReadMatrix;

import java.io.*;

public class ReadResult {
    public final static boolean DEBUG = true;

    public final static String COMMENT = "//";

    public static int FileNumber;


    public static int[] ReadIn( String File ) {

        int[] Results =new int[2];

        if( File.length() < 1 )
        {
            System.out.println("Error! No filename specified.");
            System.exit(0);
        }

        boolean seen[] = null;

        int name = 0;
        int gen = 0;
        int moves = 0;
        int marbles = 0;


        try 	{
            FileReader fr = new FileReader(File);
            BufferedReader br = new BufferedReader(fr);

            String record = new String();

            //! THe first few lines of the file are allowed to be comments, staring with a // symbol.
            //! These comments are only allowed at the top of the file.

            //! -----------------------------------------
            while ((record = br.readLine()) != null)
            {
                if( record.startsWith("//") ) continue;
                break; // Saw a line that did not start with a comment -- time to start reading the data in!
            }

            if( record.startsWith("AInumber: ") )
            {
                name = Integer.parseInt( record.substring(10) );
                if(DEBUG) System.out.println(COMMENT + " AInumber: "+name);
            }

            record = br.readLine();

            if( record.startsWith("Gen: ") )
            {
                gen = Integer.parseInt( record.substring(5) );
                if(DEBUG) System.out.println(COMMENT + " Gen: "+gen);
            }

            record = br.readLine();

            if( record.startsWith("Moves: ") )
            {
                moves = Integer.parseInt( record.substring(7) );
                Results[0] = moves;
                if(DEBUG) System.out.println(COMMENT + " Moves: "+moves);
            }

            record = br.readLine();

            if( record.startsWith("Marbles: ") )
            {
                marbles = Integer.parseInt( record.substring(9) );
                Results[1] = marbles;
                if(DEBUG) System.out.println(COMMENT + " Marbles: "+marbles);
            }
        }
        catch (IOException ex)
        {
            // catch possible io errors from readLine()
            System.out.println("Error! Problem reading file "+File);
            System.exit(0);
        }

        Results[0] = moves;
        Results[1] = marbles;

        return Results;

    }

    public static void ReadOut(){
        FileNumber++;
        for(int i=1; i<4; i++) {
            File doc = new File(System.getProperty("user.dir") + ReadMatrix.Slash + "AI" + ReadMatrix.Slash + "Results" + ReadMatrix.Slash + "AIResults" + ReadMatrix.gen + "_" + FileNumber + "_" + i + ".txt");

            System.out.println("Path : " + doc.getAbsolutePath());
            try {
                FileWriter fw = new FileWriter(doc, true);
                fw.write("AInumber: " + FileNumber);
                fw.write(System.lineSeparator());
                fw.write("Gen: " + ReadMatrix.gen);
                fw.write(System.lineSeparator());
                fw.write("Moves: 0");
                fw.write(System.lineSeparator());
                fw.write("Marbles: 0");
                fw.write(System.lineSeparator());
                fw.close();
            } catch (IOException ex) {
                System.err.println("Couldn't log this");
            }
        }
    }

    public static void AddResult(String filePath, int[] result){
        File fileToBeModified = new File(filePath);
        String oldContent = "";
        BufferedReader reader = null;
        FileWriter writer = null;
        String oldString1 = null;
        String oldString2 = null;

        try{
            reader = new BufferedReader(new FileReader(fileToBeModified));

            //Reading all the lines of input text file into oldContent
            String line = reader.readLine();

            while (line != null) {
                oldContent = oldContent + line + System.lineSeparator();
                if( line.startsWith("Moves: ") ){
                    oldString1 = line;
                }
                if( line.startsWith("Marbles: ") ){
                    oldString2 = line;
                }
                line = reader.readLine();
            }

            //Replacing oldString with newString in the oldContent
            String newContent = oldContent.replaceAll(oldString1, "Moves: "+(Integer.parseInt( oldString1.substring(7))+result[0]));
            newContent = newContent.replaceAll(oldString2, "Marbles: "+(Integer.parseInt( oldString2.substring(9))+result[1]));

            //Rewriting the input text file with newContent
            writer = new FileWriter(fileToBeModified);
            writer.write(newContent);
            //writer.write(newContent2);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                //Closing the resources
                reader.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

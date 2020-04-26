/***********************************************************************
 *  Author: Benjamin Le
 *  Purpose: Reads, processes and writes to file
 *  Date last modified: 27 May 2019
 * *********************************************************************/

import java.io.*;

/***********************************************************************
 *  Submodule: FileManager
 *  Import: ss (ShipStorage), fileName (String)
 *  Export: none
 *  Assertion: reads each line of the file
 *  *********************************************************************/

public class FileManager
{
    public static void readFile(ShipStorage ss, String fileName)
    {
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        
        int lineNum;
        String line;
    
        try
        {
            fileStrm = new FileInputStream(fileName);
            rdr = new InputStreamReader(fileStrm);
            bufRdr = new BufferedReader(rdr);

            lineNum = 0;
            line = bufRdr.readLine();

            while (line != null)
            {
                lineNum++;
                processLine(ss, line);
                line = bufRdr.readLine();
            }
            fileStrm.close();
        }
        catch (IOException e)
        {
            if (fileStrm != null)
            {
                try 
                {
                    fileStrm.close();
                }
                catch (IOException ex2)
                {
                }
            }
            System.out.println("Error in file processing " + e.getMessage());

        }

    }


/***********************************************************************
 *  Submodule: processLine
 *  Import: ss (ShipStorage), line (String)
 *  Export: none
 *  Assertion: reads and process each line
 *  *********************************************************************/
    
    public static void processLine(ShipStorage ss, String line)
    {
        char shipChoice;
        String serialNum;
        int year;
        int cylinders;
        String fuel;
        String hull;
        double maxDepth, wingSpan;
        String ordnance; 

        try
        {
            String[] lineArray = line.split(",");
        
            if (lineArray.length != 7)
            {
                throw new IllegalArgumentException("Invalid");
            }

            if (lineArray[0].length() != 1)
            {
                throw new IllegalArgumentException("Invalid 1");
            }
    
            shipChoice = lineArray[0].charAt(0);
            serialNum = lineArray[1];
            year = Integer.parseInt(lineArray[2]);    
            cylinders = Integer.parseInt(lineArray[3]);
            fuel = lineArray[4];

            Engine engine = new Engine(cylinders, fuel);
    
            switch (shipChoice)
            {
                case 'S': case 's':
                    hull = lineArray[5];
                    maxDepth = Double.parseDouble(lineArray[6]);
                                   
                    Submarine submarine = new Submarine(serialNum, year, hull, maxDepth, engine);

                    ss.addShip(submarine);
                    break;

                case 'F': case 'f':
                    wingSpan = Double.parseDouble(lineArray[5]);
                    ordnance = lineArray[6];
                
                    FighterJet fighterjet = new FighterJet(serialNum, year, wingSpan, ordnance, engine);        

                    ss.addShip(fighterjet);
                    break;

                default:
                    throw new IllegalArgumentException("Invalid object");
            }
        
        } 
        catch (IllegalArgumentException e)
        { 
            System.out.println("Invalid" + e.getMessage());           
        }
    }
     
                          
/***********************************************************************
 *  Submodule: writeOneRow 
 *  Import: ss (ShipStorage), fileName (String)
 *  Export: none
 *  Assertion: outputs ships to text file
 *  *********************************************************************/
   
     public static void writeOneRow(ShipStorage ss, String fileName)
    {
        FileOutputStream fileStrm = null;
        PrintWriter pw;

        try
        {          
            fileStrm = new FileOutputStream(fileName);
            pw = new PrintWriter(fileStrm);
        
            for (int ii = 0; ii < ss.getShipCount(); ii++)            
            {
                pw.println(ss.getShipArray()[ii].toFileString());
            }

        pw.close();
        }
        catch (IOException e)
        {
            if (fileStrm != null)
            {
                try
                {
                    fileStrm.close();
                }
                catch (IOException ex2)
                {
                }
            }
            System.out.println("Error in writing to file " + e.getMessage());
        }
    }
}

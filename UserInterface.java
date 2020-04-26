/***********************************************************************
 *  Author: Benjamin Le
 *  Purpose: User input/output and display main menu of program
 *  Date last modified: 27 May 2019
 * *********************************************************************/
import java.util.*;

public class UserInterface
{
    // class constants
    public static final String STL = "steel";
    public static final String ALY = "alloy";
    public static final String TIT = "titanium";
    public static final String BAT = "battery";
    public static final String DSL = "diesel";
    public static final String BIO = "bio";
    public static final double MINDEPTH = 0.0;
    public static final double MAXDEPTH = -500.0;
    public static final double MINSPAN = 2.20;
    public static final double MAXSPAN = 25.6;
    public static final int MINYEAR = 1950;
    public static final int MAXYEAR = 2022;
    public static final int MINCYL = 2;
    public static final int MAXCYL = 20;

    // private class fields
    private ShipStorage shipStorage;


/************************************************************************
 * Default Constructor
 * Import: none
 * Export: none
 * Assertion: creates the object
 * **********************************************************************/

    public UserInterface()
    {
        shipStorage = new ShipStorage();       
    }


/************************************************************************
 * Submodule: shipMenu
 * Import: none
 * Export: none
 * Assertion: lists all options from program for user to select
 * **********************************************************************/

    public void shipMenu()
    {
        Scanner sc = new Scanner(System.in);
        int shipmenu;
        String fileName;

        do
        {
            shipmenu = integerInput("\nSelect an option\n" +
                                    "1) Add Ships\n" +
                                    "2) Destination Check\n" +
                                    "3) Find Duplicates\n" +
                                    "4) View Ships\n" +
                                    "5) Load Ships\n" +
                                    "6) Save Ships\n" +
                                    "7) Exit", 1, 7);

            switch (shipmenu)
            {
                case 1:
                    addShip();
                    break;

                case 2:
                    destinationCheck();
                    break;

                case 3: 
                    findDuplicates();   
                    break;

                case 4:
                    System.out.println(shipStorage.viewShips());
                    break;

                case 5:
                    fileName = stringInput("Enter a file name");
                    FileManager.readFile(shipStorage, fileName);
                    break;

                case 6:
                    fileName = stringInput("Enter a file name");
                    FileManager.writeOneRow(shipStorage, fileName);
                    break;

                case 7:
                    System.out.println("Thank you for using Ben's program. Good bye!");
                    break;

                default:
                    System.out.println("Error. Enter a number between 1 and 7"); 
            }
        } while (shipmenu != 7);
    }
    

/************************************************************************
 * Submodule: addShip
 * Import: none
 * Export: none
 * Assertion: prompts user to enter details of the ship depending on choice
 *            of submarine or fighter jet
 * **********************************************************************/

    public void addShip()
    {
        String prompt = "\nPick a ship you want to create\n" +
                        "1) Submarine\n" + 
                        "2) Fighter Jet";

        String serialNum, hull, ordnance, fuel;
        int num, year, cylinders;
        double maxDepth, wingSpan;
        boolean valid;

        num = integerInput(prompt, 1, 2);

        do
        {
            serialNum = stringInput("\nEnter the serial number in the format XXX.YYY\n" +
                                    "XXX must be between 100 and 300 (inclusive)\n" +
                                    "YYY must be between 1 and 999 (inclusive)");
            valid = validateSerialNum(serialNum);
        } while (!valid);            
            
        year = integerInput("\nEnter the commission year between 1950 and 2022 (inclusive)", 1950, 2022);

        cylinders = integerInput("\nEnter the number of cylinders between 2 and 20 (inclusive)", 2, 20);
        
        do
        {
            fuel = stringInput("\nEnter the fuel type (battery, diesel or bio)");
            valid = validateFuel(fuel);
        } while (!valid);
 
        Engine engine = new Engine(cylinders, fuel);
       
        if (num == 1) // submarine creation
        {
            do
            {
                hull = stringInput("\nEnter the hull type (steel, alloy or titanium)");
                valid = validateHull(hull);
            } while (!valid);

            maxDepth = realInput("\nEnter the max depth between -500.0 and 0.0 (inclusive)", -500.0, 0.0); 

            Submarine newSub = new Submarine(serialNum, year, hull, maxDepth, engine);
                
            shipStorage.addShip(newSub);
        }
        else if (num == 2)
        {   
            wingSpan = realInput("\nEnter the wing span between 2.20 and 25.6 (inclusive)", 2.20, 25.6);
            
            do
            {
                ordnance = stringInput("\nEnter the ordnance (must not be empty)");
                valid = validateOrdnance(ordnance);
            } while (!valid);

            FighterJet newJet = new FighterJet(serialNum, year, wingSpan, ordnance, engine);
            
            shipStorage.addShip(newJet);
        }
    }


/************************************************************************
 * Submodule: destinationCheck
 * Import: none
 * Export: none
 * Assertion: prompts user to enter travel distance and prints out
 * **********************************************************************/
    
    public void destinationCheck()
    {
        int travelDistance;

        travelDistance = integerInput("Enter the travel distance", 0, Integer.MAX_VALUE);
        System.out.println(shipStorage.destinationCheck(travelDistance));
    }


/************************************************************************
 * Submodule: integerInput
 * Import: prompt (String), min (integer), max (integer)
 * Export: num (integer)
 * Assertion: used for any integer inputs from user and outputs
 * **********************************************************************/

    public int integerInput(String prompt, int min, int max)
    {
        int num = 0;
        Scanner sc = new Scanner(System.in);

        do
        {
            try
            {     
                System.out.println(prompt);
                num = sc.nextInt();
                prompt = "Error. Enter a valid number between " + min + " and " + max;
            }
            catch (InputMismatchException e)
            {
                sc.nextLine();
                prompt = "Error. Must be an integer";
            }
        } while ((num < min) || (num > max));
        return num;
    }


/************************************************************************
 * Submodule: realInput
 * Import: prompt (String), min (real), max (real)
 * Export: num (real)
 * Assertion: used for any real inputs from user
 * **********************************************************************/

    public double realInput(String prompt, double min, double max)
    {
        double num = 0;
        Scanner sc = new Scanner(System.in);

        do
        {
            try
            {     
                System.out.println(prompt);
                num = sc.nextDouble();
                prompt = "Error. Enter a valid number between " + min + " and " + max;
            }
            catch (InputMismatchException e)
            {
                sc.nextLine();
                prompt = "Error. Must be a real number";
            }
        } while ((num < min) || (num > max));
        return num;
    }


/************************************************************************
 * Submodule: stringInput
 * Import: prompt (String)
 * Export: str (String)
 * Assertion: used for any string inputs from user
 * **********************************************************************/

    public String stringInput(String prompt)
    {
        Scanner sc = new Scanner(System.in);

        System.out.println(prompt);
        String str = sc.nextLine();
        return str;
    }


/************************************************************************
 * Submodule: findDuplicates
 * Import: none
 * Export: none
 * Assertion: calls findDuplicates from ShipStorage class and prints out ships
 * **********************************************************************/
    
    public void findDuplicates()
    {
        String dupShip;
        dupShip = shipStorage.findDuplicates();

        if (dupShip != null)
        {
            System.out.println(dupShip);
        }
        else
        {
            System.out.println("No duplicates found");
        }
    }

// PRIVATE SUBMODULES
/************************************************************************
 * Submodule: validateSerialNum
 * Import: inSerialNum (real)
 * Export: valid (boolean)
 * Assertion: serial number must be in format XXX.YYY 
 *            where XXX is betwen 100 and 300, YYY is between 1 and 999
 * **********************************************************************/

     private boolean validateSerialNum(String inSerialNum)
     {
        int XXX = 0, YYY = 0;
        String[] serialNum = new String[2]; // creates array of strings size 2

        try
        {
            serialNum = inSerialNum.split("\\."); // split the serial number where there is a "."
            XXX = Integer.parseInt(serialNum[0]); // XXX is first index of array
            YYY = Integer.parseInt(serialNum[1]); // YYY is second index of array
        }
        catch (NumberFormatException e)
        {
            System.out.println("Invalid serial number " + inSerialNum);
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Invalid. Serial number must be in format XXX.YYY");
        }
        return ((XXX >= 100) && (XXX <= 300) && (YYY >= 1) && (YYY <= 999) && (serialNum[0].length() == 3) && (serialNum[1].length() == 3));
    }


/************************************************************************
 * Submodule: validateYear
 * Import: inYear (integer)
 * Export: valid (boolean)
 * Assertion: year is between 1950 and 2022 inclusive
 * **********************************************************************/

    private boolean validateYear(int inYear)
    {
        return ((inYear >= MINYEAR) && (inYear <= MAXYEAR));
    }


/************************************************************************
 * Submodule: validateHull
 * Import: inHull (String)
 * Export: valid (boolean)
 * Assertion: hull is either steel, alloy or titanium
 * **********************************************************************/

    private boolean validateHull(String inHull)
    {
        inHull = inHull.toLowerCase();
        return ((inHull.equals(STL)) || (inHull.equals(ALY)) || (inHull.equals(TIT)));
    }


/************************************************************************
 * Submodule: validateMaxDepth
 * Import: inMaxDepth (real)
 * Export: valid (boolean)
 * Assertion: maxDepth is between -500 and 0.0 inclusive
 * **********************************************************************/

    private boolean validateMaxDepth(double inMaxDepth)
    {
        return ((inMaxDepth >= MINDEPTH) && (inMaxDepth <= MAXDEPTH));
    }


/************************************************************************
 * Submodule: validateWingSpan
 * Import: inWingSpan (real)
 * Export: valid (boolean)
 * Assertion: wingSpan between 2.20 and 25.6 inclusive
 * **********************************************************************/

    private boolean validateWingSpan(double inWingSpan)
    {
        return ((inWingSpan >= MINSPAN) && (inWingSpan <= MAXSPAN));
    }


/************************************************************************
 * Submodule: validateOrdnance
 * Import: inOrdnance (String)
 * Export: valid (boolean)
 * Assertion: ordnance is my any valid string
 * **********************************************************************/

    private boolean validateOrdnance(String inOrdnance)
    {
        return ((inOrdnance != null) && (!inOrdnance.isEmpty()));
    }


/**********************************************************************
 * Submodule: validateCylinders
 * Import: inCylinders (integer)
 * Export: valid (boolean)
 * Assertion: cylinders is between 2 and 20 inclusive
 * *******************************************************************/

    private boolean validateCylinders(int inCylinders)
    {
        return ((inCylinders >= MINCYL) && (inCylinders <= MAXCYL));
    }


/**********************************************************************
 * Submodule: validateFuel
 * Import: inFuel (String)
 * Export: valid (boolean)
 * Assertion: fuel is either "Battery" "Diesel" or "Bio"
 * *******************************************************************/

    private boolean validateFuel(String inFuel)
    {
        inFuel = inFuel.toLowerCase();
        return ((inFuel.equals(BAT)) || (inFuel.equals(DSL)) || (inFuel.equals(BIO)));
    }
}

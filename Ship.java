/***********************************************************************
 *  Name: Benjamin Le
 *  Purpose: Contains constructors, methods and validation for Ship 
 *  Date last modified: 27 May 2019
 *  *********************************************************************/

import java.util.*;

public abstract class Ship
{
    // class constants
    public static final int MINYEAR = 1950;
    public static final int MAXYEAR = 2022;

    // private class fields
    private String serialNum;
    private int year;
    private Engine engine;

/***********************************************************************
 *  Default Constructor
 *  Import: none
 *  Export: none
 *  Assertion: serial number 169.420 of year 2022 with engine
 *  *********************************************************************/

    public Ship()
    {
        serialNum = "169.420";
        year = MAXYEAR;
        engine = new Engine(engine);
    }


/***********************************************************************
 *  Alternate Constructor
 *  Import: inSerialNum (real), inYear (integer), inEngine (Engine)
 *  Export: address of new Ship
 *  Assertion: creates the object if imports are valid, otherwise fails 
 *  *********************************************************************/

    public Ship(String inSerialNum, int inYear, Engine inEngine)
    {
        if ((validateSerialNum(inSerialNum)) && (validateYear(inYear)) && (validateEngine(inEngine)))
        {
            serialNum = inSerialNum;
            year = inYear;
            engine = inEngine;
        }
        else
        {
            throw new IllegalArgumentException("Invalid import value");
        }
    }


/***********************************************************************
 *  Copy Constructor
 *  Import: inShip (Ship)
 *  Export: address of new Ship 
 *  Assertion: creates an object with an identical object state as the import
 *  *********************************************************************/

    public Ship(Ship inShip)
    {
        serialNum = inShip.getSerialNum();
        year = inShip.getYear();
        engine = inShip.getEngine();
    }


/***********************************************************************
 *  Abstract Clone
 *  Import: none
 *  Export: none
 *  Assertion: creates a clone of ship
 *  *********************************************************************/

    public abstract Ship clone();


// MUTATORS
/***********************************************************************
 *  Submodule: setSerialNum
 *  Import: inSerialNum (String)
 *  Export: none
 *  Assertion: sets serialNum to inSerialNum
 *  *********************************************************************/

    public void setSerialNum(String inSerialNum)
    {
        if (validateSerialNum(inSerialNum))
        {
            serialNum = inSerialNum;
        }
        else
        {
            throw new IllegalArgumentException("Invalid serial number");
        }
    }

/***********************************************************************
 *  Submodule: setYear
 *  Import: inYear (integer)
 *  Export: none
 *  Assertion: sets year to inYear
 *  *********************************************************************/

    public void setYear(int inYear)
    {
        if (validateYear(inYear))
        {
            year = inYear;
        }
        else
        {
            throw new IllegalArgumentException("Invalid year");
        }
    }


/***********************************************************************
 *  Submodule: setEngine
 *  Import: inEngine (Engine)
 *  Export: none
 *  Assertion: sets a copy of engine to inEngine
 *  *********************************************************************/

    public void setEngine(Engine inEngine)
    {
        engine = new Engine(inEngine);
    }


// ACCESSORS
    public String getSerialNum()
    {
        return serialNum;
    }


    public int getYear()
    {
        return year;
    }


    public Engine getEngine()
    {
        return new Engine(engine);
    }


/***********************************************************************
 *  Submodule: equals
 *  Import: inObj (Object)
 *  Export: same (boolean)
 *  Assertion: compares two ships to see if they are the same
 *  *********************************************************************/

    public boolean equals(Object inObj)
    {
        boolean same = false;
        
            if (inObj instanceof Ship)
        {
            Ship inShip = (Ship)inObj;
            same = (serialNum.equals(inShip.getSerialNum())) && (year == inShip.getYear())
            && (engine.equals(inShip.getEngine()));
        }
            return same;
    }

/***********************************************************************
 *  Submodule: toString
 *  Import: none
 *  Export: str (String)
 *  *********************************************************************/

    public String toString()
    {
        String str;

        str = "The ship " + serialNum + " was commissioned in " + year + "," + engine.toString();
        return str;
    }


/***********************************************************************
 *  Submodule: toFileString
 *  Import: none
 *  Export: str (String)
 *  *********************************************************************/

    public String toFileString()
    {
        return serialNum + "," + year + "," + engine.getCylinders() + "," + engine.getFuel(); 
    }


/***********************************************************************
 *  Submodule: calcTravel
 *  Import: travelDistance (integer)
 *  Export: subTime (real)
 *  Assertion: 
 *  *********************************************************************/

    public abstract double calcTravel(int travelDistance);


/************************************************************************
 * Submodule: validateSerialNum
 * Import: inSerialNum (real)
 * Export: valid (boolean)
 * Assertion: serial number must be be in format XXX.YYY 
 *            where XXX is between 100 and 300, YYY between 1 and 999 (inclusive)
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
            System.out.println("Invalid. Serial number must be in form XXX.YYY");   
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
 * Submodule: validateEngine
 * Import: inEngine (Engine)
 * Export: valid (boolean)
 * Assertion: engine is not null
 * **********************************************************************/

    private boolean validateEngine(Engine inEngine)
    {
        return (inEngine != null);
    }
}

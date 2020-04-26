/***************************************************************************
 * Name: Benjamin Le
 * Purpose: Manages the ships
 * Date last modified: 27 May 2019
 * ************************************************************************/

import java.util.*;

public class ShipStorage
{
    // class constants
    public static final int MAXSHIP = 30;
   
 
    // private class fields
    private Ship[] shipArray;
    private int shipCount;
   
 
/***************************************************************************
 * Default Constructor
 * Import: none
 * Export: none
 * Assertion: creates shipArray made of Ship with a size of 30 and initalises shipCount as 0
 * ************************************************************************/
    
    public ShipStorage()
    {
        shipArray = new Ship[MAXSHIP];
        shipCount = 0;
    }
      

/***************************************************************************
 * Submodule: addShip
 * Import: addedShip (Ship)
 * Export: none
 * Assertion: adds a copy of the ship to the array unless array full or added ship is null
 * ************************************************************************/

    public void addShip(Ship addedShip)
    { 
        if (shipCount >= MAXSHIP)
        {
            throw new IllegalArgumentException("Array is full");
        }
        else if (addedShip == null)
        {
            throw new IllegalArgumentException("Added ship is null");
        }
        else  
        {
            shipArray[shipCount] = addedShip.clone();
            shipCount = shipCount + 1;
        }
    }


/***************************************************************************
 * Submodule: destinationCheck
 * Import: travelDistance (integer)
 * Export: outStr (String)
 * Assertion: determines amount of time it takes to cover a specific distance and outputs the fastest ship
 * ************************************************************************/

    public String destinationCheck(int travelDistance)
    {
        double fastestTime = Double.MAX_VALUE;
        int shipIndex = 0;
        Ship fastestShip = null; 
        double shipTime;   
        String outStr = "";

        if (shipCount == 0)
        {
            outStr = "There are no ships in the ship storage";
        }
        else
        {
            for (int ii = 0; ii < shipCount; ii++)          
            {
                shipTime = shipArray[ii].calcTravel(travelDistance);
                
                if (shipTime < fastestTime)
                {
                    fastestShip = shipArray[ii];
                    fastestTime = shipTime;
                }
            }
            outStr = fastestShip.toString() + " with a travel time of " + fastestTime + " hours.";
        }
        return outStr;
    }

/***************************************************************************
 * Submodule: findDuplicates
 * Import: none
 * Export: dupShip (String)
 * Assertion: loops through shipArray and outputs all the ships that are the same
 * ************************************************************************/

    public String findDuplicates()
    {
        String dupShip = "";
    
        for (int ii = 0; ii < shipCount; ii++)
        {
            for (int jj = ii + 1; jj < shipCount; jj++)
            {
                if (shipArray[ii].equals(shipArray[jj]))
                {
                    dupShip += shipArray[ii].toString() + "\n";
                }
            }
        }
        return dupShip;
    }
         

/***************************************************************************
 * Submodule: viewShips
 * Import: none
 * Export: shipString (String)
 * Assertion: ouputs the details of the ships in a string
 * ************************************************************************/

    public String viewShips()
    {
        String shipString = "";
        int ii;

        for (ii = 0; ii < shipCount; ii++)
        {
            shipString += shipArray[ii].toString()+"\n";
        }
        return shipString;        
    }


// ACCESSORS
    public Ship[] getShipArray()
    {
        Ship[] shipCopy = new Ship[shipArray.length];
        
        for (int ii = 0; ii < shipArray.length; ii++)
        {
            shipCopy[ii] = shipArray[ii];     
        }
        return shipCopy;
    }


    public int getShipCount()
    {
        return shipCount;
    }


/***************************************************************************
 * Submodule: equals
 * Import: inObj (Object)
 * Export: same (boolean)
 * Assertion: compares shipCount and shipArray to see if they're equal
 * ************************************************************************/

    public boolean equals(Object inObj)
    {
        boolean same = false;
    
        if (inObj instanceof ShipStorage)
        {
            ShipStorage inShipStorage = (ShipStorage)inObj;
            same = ((shipCount == inShipStorage.getShipCount()) && (shipArray.equals(inShipStorage.getShipArray())));
        }
            return same;
    }

/***************************************************************************
 * Submodule: equalsArray
 * Import: arrayOne (Object), arrayTwo (Object)
 * Export: equal (boolean)
 * Assertion: compares two arrays to see if they're equal
 * ************************************************************************/
    public boolean equals(Object[] arrayOne, Object[] arrayTwo)
    {
        boolean equal = true;
        int count;

        if (arrayOne.length != arrayTwo.length)
        {
            equal = false;
        }
        else
        {
            count = 0;

            do
            {
                equal = arrayOne[count].equals(arrayTwo[count]);
                count = count + 1;
            } while ((equal) && (count < arrayOne.length));
        }
        return equal;  
    }   
}

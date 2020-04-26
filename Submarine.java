/***********************************************************************
 *  Author: Benjamin Le
 *  Purpose: Contains constructors, methods and validation for Submarine
 *  Date last modified: 27 May 2019
 * *********************************************************************/

import java.util.*;

public class Submarine extends Ship
{
    // class constants    
    public static final String STL = "steel";
    public static final String ALY = "alloy";
    public static final String TIT = "titanium";
    public static final double MINDEPTH = 0.0; 
    public static final double MAXDEPTH = -500.0;
    public static final double TOL = 0.0001;


    // private class fields
    private String hull;
    private double maxDepth;


/***********************************************************************
 *  Default Constructor
 *  Import: none
 *  Export: none
 *  Assertion: calls super default constructor and includes hull made of
 *             titanium and has a max depth of -500.0
 *  *********************************************************************/

    public Submarine()
    {
        super();
        hull = TIT;
        maxDepth = MAXDEPTH;
    }


/***********************************************************************
 *  Alternate Constructor
 *  Import: inSerialNum, inYear, inHull, inMaxDepth, inEngine
 *  Export: address of new Submarine object
 *  Assertion: creates the object if imports are valid, otherwise fails
 *  *********************************************************************/

    public Submarine(String inSerialNum, int inYear, String inHull, double inMaxDepth, Engine inEngine)
    {
        super(inSerialNum, inYear, inEngine);


        if ((validateHull(inHull)) && (validateMaxDepth(inMaxDepth)))
        {
            hull = inHull.toLowerCase();
            maxDepth = inMaxDepth;
        } 
        else
        {
            throw new IllegalArgumentException("Invalid import value"); 
        }
    }


/***********************************************************************
 *  Copy Constructor
 *  Import: inSubmarine 
 *  Export: address of new Submarine object
 *  Assertion: creates an object with an identical object state as the import
 *  *********************************************************************/

    public Submarine(Submarine inSubmarine)
    {
        super(inSubmarine);
        hull = inSubmarine.getHull();
        maxDepth = inSubmarine.getMaxDepth();
    }


/***********************************************************************
 *  Submodule: clone
 *  Import: none
 *  Export: cloneSubmarine
 *  Assertion: creates a clone of the object submarine
 *  *********************************************************************/
    
    public Ship clone()
    {
        Submarine cloneSubmarine;
        cloneSubmarine = new Submarine(this); 
        return cloneSubmarine;
    }
   
 
// MUTATORS 
/***********************************************************************
 *  Submodule: setHull
 *  Import: inHull
 *  Export: none
 *  Assertion: sets hull to inHull
 *  *********************************************************************/
 
    public void setHull(String inHull)
    {
        if (validateHull(inHull))
        {
            hull = inHull.toLowerCase();
        } 
        else
        {
            throw new IllegalArgumentException("Invalid. Hull must be either steel, alloy or titanium.");
        }
    }     


/***********************************************************************
 *  Submodule: setMaxDepth
 *  Import: inMaxDepth
 *  Export: none
 *  Assertion: sets maxDepth to inMaxDepth
 *  *********************************************************************/
    
    public void setMaxDepth(double inMaxDepth)
    {
        if (validateMaxDepth(inMaxDepth))
        { 
            maxDepth = inMaxDepth;
        }
        else
        {
            throw new IllegalArgumentException("Invalid. Max depth must be between -500.0 and 0.0 (inclusive).");
        }
    }


// ACCESSORS
    public String getHull()
    {
        return hull;
    }


    public double getMaxDepth()
    { 
        return maxDepth;
    }


/***********************************************************************
 *  Submodule: equals
 *  Import: inObj (Object)
 *  Export: same (boolean)
 *  Assertion: compare two submarines to see if they are the same
 *  *********************************************************************/

    public boolean equals(Object inObj)
    {
        boolean same = false;
        
        if (inObj instanceof Submarine)
        {
            Submarine inSubmarine = (Submarine)inObj;
            same = ((super.equals(inSubmarine)) && (hull.equals(inSubmarine.getHull())) && Math.abs(maxDepth - inSubmarine.getMaxDepth()) < TOL);
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

        str = super.toString() + " It is a submarine with a " + hull + " hull and a max depth of " + maxDepth + " meters.";

        return str;
    }


/***********************************************************************
 *  Submodule: toFileString
 *  Import: none
 *  Export: str (String)
 *  *********************************************************************/

    public String toFileString()
    {
        String str;

        str = ("S," + super.toFileString() + "," + hull + "," + maxDepth);
       
        return str;
    }


/***********************************************************************
 *  Submodule: calcTravel
 *  Import: travelDistance (integer)
 *  Export: subTime (real)
 *  Assertion: calculates the time it takes for a submarine to travel a 
 *             certain distance
 *  *********************************************************************/

    public double calcTravel(int travelDistance)
    {
        double subTime;

        subTime = ((double)(travelDistance) / (double)(super.getEngine().getCylinders())) * (1.0/(10.0 + (getMaxDepth() * -1.0)));

        subTime = Math.round(subTime * 100.0) / 100.0; // rounds to 2 decimal places
        return subTime;
    }


// PRIVATE SUBMODULES
/***********************************************************************
 *  Submodule: validateHull
 *  Import: inHull (String)
 *  Export: valid (boolean)
 *  Assertion: hull is either "steel" "alloy" or "titanium"
 *  *********************************************************************/

    private boolean validateHull(String inHull)
    {
        inHull = inHull.toLowerCase();
        return ((inHull.equals(STL)) || (inHull.equals(ALY)) || (inHull.equals(TIT)));
    }


/***********************************************************************
 *  Submodule: validateMaxDepth
 *  Import: inMaxDepth (real)
 *  Export: valid (boolean)
 *  Assertion: maxDepth is between -500.0 and 0.0 inclusive
 *  *********************************************************************/

    private boolean validateMaxDepth(double inMaxDepth)
    {
        return ((inMaxDepth >= MAXDEPTH) && (inMaxDepth <= MINDEPTH));
    }
}

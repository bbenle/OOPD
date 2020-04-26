/**********************************************************************
 * Name: Benjamin Le
 * Purpose: Contains constructors, methods and validation for Engine
 * Date last modified: 27 May 2019
 * *******************************************************************/

import java.util.*;

public class Engine
{
    // class constants
    public static final int MINCYL = 2;
    public static final int MAXCYL = 20;
    public static final String BAT = "battery";
    public static final String DSL = "diesel";
    public static final String BIO = "bio";


    //private class fields
    private int cylinders;
    private String fuel;


/**********************************************************************
 * Default Constructor
 * Import: none
 * Export: none
 * Assertion: 20 cylinders with diesel as fuel type
 * *******************************************************************/

    public Engine()
    {
        cylinders = MAXCYL;
        fuel = DSL;
    }


/**********************************************************************
 * Alternate Constructor
 * Import: inCylinders, inFuel
 * Export: address of new Engine object
 * Assertion: creates the object if imports are valid, otherwise fails
 * *******************************************************************/

    public Engine(int inCylinders, String inFuel)
    {
        if ((validateCylinders(inCylinders)) && (validateFuel(inFuel)))
        {
            cylinders = inCylinders;
            fuel = inFuel.toLowerCase();
        }
        else
        {
            throw new IllegalArgumentException("Invalid import value");
        }
    }


/**********************************************************************
 * Copy Constructor
 * Import: inEngine
 * Export: address of new Engine object
 * Assertion: creates an object with an identical object state as the import
 * *******************************************************************/

    public Engine(Engine inEngine)
    {
        cylinders = inEngine.getCylinders();
        fuel = inEngine.getFuel();
    }


/**********************************************************************
 * Submodule: clone
 * Import: none
 * Export: cloneEngine
 * Assertion: creates a clone of the object engine
 * *******************************************************************/

    public Engine clone()
    {
        Engine cloneEngine;
        cloneEngine = new Engine(this);
        return cloneEngine;
    }


// MUTATORS
/**********************************************************************
 * Submodule: setCylinders
 * Import: inCylinders (integer)
 * Export: none
 * Assertion: sets cylinders to inCylinders
 * *******************************************************************/

    public void setCylinders(int inCylinders)
    {
        if (validateCylinders(inCylinders))
        {
            cylinders = inCylinders;
        }
        else
        {
            throw new IllegalArgumentException("Invalid. Cylinders must be between 2 and 20 (inclusive).");
        }
    }


/**********************************************************************
 * Submodule: setFuel
 * Import: inFuel (String)
 * Export: none
 * Assertion: sets fuel to inFuel
 * *******************************************************************/
    
    public void setFuel(String inFuel)
    {
        if (validateFuel(inFuel))
        {
            fuel = inFuel.toLowerCase();
        }
        else
        {
            throw new IllegalArgumentException("Invalid. Fuel must be either battery, diesel or bio.");
        }
    }


// ACCESSORS
    public int getCylinders()
    {
        return cylinders;
    }


    public String getFuel()
    {
        return fuel;
    }


/**********************************************************************
 * Submodule: equals
 * Import: inObj (Object)
 * Export: same (boolean)
 * Assertion: compares two engines to see if they are the same
 * *******************************************************************/

    public boolean equals(Object inObj)
    {
        boolean same = false;
    
        if (inObj instanceof Engine)
        {
            Engine inEngine = (Engine)inObj;
            same = ((cylinders == inEngine.getCylinders()) && (fuel.equals(inEngine.getFuel())));
        }
            return same;
    }


/**********************************************************************
 * Submodule: toString
 * Import: none
 * Export: str
 * *******************************************************************/

    public String toString()
    {
        String str;

        str = " it's engine has " + cylinders + " cylinders and runs on " + fuel + " fuel.";
        return str;
    }


// PRIVATE SUBMODULES
/**********************************************************************
 * Submodule: validateCylinders
 * Import: inCylinders (integer)
 * Export: valid (boolean)
 * Assertion: cylinders is between 2 and 20 inclusive
 * *******************************************************************/

    public boolean validateCylinders(int inCylinders)
    {
        return ((inCylinders >= MINCYL) && (inCylinders <= MAXCYL));
    }


/**********************************************************************
 * Submodule: validateFuel
 * Import: inFuel (String)
 * Export: valid (boolean)
 * Assertion: fuel is either "battery" "diesel" or "bio"
 * *******************************************************************/

    public boolean validateFuel(String inFuel)
    {
        inFuel = inFuel.toLowerCase();
        return ((inFuel.equals(BAT)) || (inFuel.equals(DSL)) || (inFuel.equals(BIO)));
    }
    
}

/***********************************************************************
 *  Name: Benjamin Le
 *  Purpose: Contains constructors, methods and validation for Fighter Jet
 *  Date last modified: 27 May 2019
 *  ********************************************************************/

import java.util.*;

public class FighterJet extends Ship
{
    // class constants
    public static final double MAXSPAN = 25.6;
    public static final double MINSPAN = 2.20;
    public static final double TOL = 0.0001;


    // private class fields
    private double wingSpan; 
    private String ordnance;


/**********************************************************************
 *  Default Constructor
 *  Import: none
 *  Export: none
 *  Assertion: calls super default constructor and includes wing span of 25.6 
 *             and ordnance of Stormbreaker
 *  ********************************************************************/

    public FighterJet()
    {
        super();
        wingSpan = MAXSPAN;
        ordnance = "Stormbreaker";
    }


/***********************************************************************
 *  Alternate Constructor
 *  Import: inSerialNum, inYear, inWingSpan, inOrdnance, inEngine
 *  Export: address of new FighterJet object
 *  Assertion: creates the object if imports are valid, otherwise fails
 *  *********************************************************************/

    public FighterJet(String inSerialNum, int inYear, double inWingSpan, String inOrdnance, Engine inEngine)
    {
        super(inSerialNum, inYear, inEngine);
        
        if ((validateWingSpan(inWingSpan)) && (validateOrdnance(inOrdnance)))
        {
            wingSpan = inWingSpan;
            ordnance = inOrdnance;
        } 
        else
        {
            throw new IllegalArgumentException("Invalid import value"); 
        }
    }


/***********************************************************************
 *  Copy Constructor
 *  Import: inFighterJet
 *  Export: address of new FighterJet object
 *  Assertion: creates an object with an identical object state as the import
 *  *********************************************************************/

    public FighterJet(FighterJet inFighterJet)
    {
        super(inFighterJet);
        wingSpan = inFighterJet.getWingSpan();
        ordnance = inFighterJet.getOrdnance();
    }


/***********************************************************************
 *  Submodule: clone
 *  Import: none
 *  Export: cloneSubmarine
 *  Assertion: clones the object submarine
 *  *********************************************************************/
    
    public Ship clone()
    {
        FighterJet cloneFighterJet;
        cloneFighterJet = new FighterJet(this); 
        return cloneFighterJet;
    }
    

// MUTATORS
/***********************************************************************
 *  Submodule: setWingSpan
 *  Import: inWingSpan (real)
 *  Export: none
 *  Assertion: sets wingSpan to inWingSpan
 *  *********************************************************************/
    
    public void setWingSpan(double inWingSpan)
    {
        if (validateWingSpan(inWingSpan))
        { 
            wingSpan = inWingSpan;
        }
        else
        {
            throw new IllegalArgumentException("Invalid. Wing span must be between 2.20 and 25.6 (inclusive).");
        }
    }

/***********************************************************************
 *  Submodule: setOrdnance
 *  Import: inOrdnance (String)
 *  Export: none
 *  Assertion: sets ordnance to inOrdnance
 *  *********************************************************************/
 
    public void setOrdnance(String inOrdnance)
    {
        if (validateOrdnance(inOrdnance))
        {
            ordnance = inOrdnance;
        } 
        else
        {
            throw new IllegalArgumentException("Invalid. Ordnance must not be empty.");
        }
    }     

// ACCESSORS
    public double getWingSpan()
    { 
        return wingSpan;
    }


    public String getOrdnance()
    {
        return ordnance;
    }

/***********************************************************************
 *  Submodule: equals
 *  Import: inObj (Object)
 *  Export: same (boolean)
 *  Assertion: compare two fighterjets to see if they are the same
 *  *********************************************************************/

    public boolean equals(Object inObj)
    {
        boolean same = false;
        
        if (inObj instanceof FighterJet)
        {
            FighterJet inFighterJet = (FighterJet)inObj;
            same = ((super.equals(inFighterJet)) && Math.abs(wingSpan - inFighterJet.getWingSpan()) < TOL) && (ordnance.equals(inFighterJet.getOrdnance()));
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

        str = super.toString() + " It is a fighter jet with a wing span of " + wingSpan + " meters and equipped with " + ordnance;
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
        
        str = ("F," + super.toFileString() + "," + wingSpan + "," + ordnance);
        return str;    
    }


/***********************************************************************
 *  Submodule: calcTravel
 *  Import: travelDistance (integer)
 *  Export: jetTime (real)
 *  Assertion: calculates the time it takes for a fighter jet to travel a certain distance
 *  *********************************************************************/

    public double calcTravel(int travelDistance)
    {
        double jetTime;

        jetTime = travelDistance / (getWingSpan() * super.getEngine().getCylinders() * 150);

        jetTime = Math.round(jetTime * 100.0) / 100.0; // rounds to 2 decimal places
        return jetTime;
    }        


// PRIVATE SUBMODULES
/***********************************************************************
 *  Submodule: validateWingSpan
 *  Import: inWingSpan (real)
 *  Export: valid (boolean)
 *  Assertion: wingSpan is between 2.20 and 25.6 inclusive
 *  *********************************************************************/

    private boolean validateWingSpan(double inWingSpan)
    {
        return ((inWingSpan >= MINSPAN) && (inWingSpan <= MAXSPAN));
    }

/***********************************************************************
 *  Submodule: validateOrdnance
 *  Import: inOrdnance (String)
 *  Export: valid (boolean)
 *  Assertion: ordnance is any valid string
 *  *********************************************************************/

    private boolean validateOrdnance(String inOrdnance)
    {
        return ((inOrdnance != null) && (!inOrdnance.isEmpty()));
    }
}

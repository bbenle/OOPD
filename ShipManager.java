/***********************************************************************
 *  Author: Benjamin Le
 *  Purpose: ShipManager class
 *  Date last modified: 27 May 2019
 * *********************************************************************/

public class ShipManager
{
    public static void main(String[] args)
    {
        try
        {
            UserInterface ui = new UserInterface();
            ui.shipMenu();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage() + " Sorry!");
        }

    }
}

package com.labsch.util;

import java.awt.Frame;

import com.labsch.azpz.azpzFrame;

/**
 * 
 * @author Martin Labsch, 01.05.2016
 *
 */
public class FrameHandling
{
    /**
     * If you need an running instance of an frame by name, you will get it here!
     * 
     * @author Martin Labsch, 01.05.2016
     * @param frameName
     * @return the frame - if a frame with given name exists<br>
     *         null - if not exists
     */
    public static Frame getAnAzpzFrameByName(String frameName)
    {
        azpzFrame frame = null;

        Frame[] allFrames = azpzFrame.getFrames();

        if (allFrames.length > 0)
        {
            for (int i = 0; i < allFrames.length; i++)
            {
                if (allFrames[i] instanceof azpzFrame && allFrames[i].getName().equals(frameName))
                {
                    frame = (azpzFrame) allFrames[i];
                }
            }
        }

        return frame;
    }
}

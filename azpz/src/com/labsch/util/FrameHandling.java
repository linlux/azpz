package com.labsch.util;

import java.awt.Frame;

import com.labsch.azpz.azpzFrame;

public class FrameHandling
{
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

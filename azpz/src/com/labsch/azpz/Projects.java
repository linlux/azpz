/**
 * 
 */
package com.labsch.azpz;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.JMenu;
import javax.swing.JTable;
import com.labsch.util.FrameHandling;
import com.labsch.util.MenuHandling;

/**
 * @author Martin Labsch, 02.05.2016
 *
 */
public class Projects
{
    static void initProjects()
    {
        JMenu m = MenuHandling.getAnMenuByNameFromFramesMenuBar("menuProjects", "mainFrame");
        m.setEnabled(true);

        /**
         * Hier sollte jetzt das Projects-Menu automatisch selektiert werden, was aber noch nicht funktioniert.
         */
        // MenuSelectionManager selMan = MenuSelectionManager.defaultManager();
        // MenuElement elems[] =
        // { new JMenuBar(), m };
        // selMan.setSelectedPath(elems);

        String SQL = "SELECT `projects_ID`, `start`, `text` FROM projects ";

        ResultSet rs = com.labsch.dbUtils.DBConnection.executeQuery(SQL);
        if (rs == null)
        {
            return;
        }
        else
        {

            try
            {
                ResultSetMetaData rsmd = rs.getMetaData();
                int cols = rsmd.getColumnCount();
                int rows = 0;

                while (rs.next())
                {
                    rows++;
                }
                String[] headers = new String[cols];
                Object[][] records = new Object[rows][cols];

                for (int i = 0; i < cols; i++)
                {
                    headers[i] = rsmd.getColumnName(i + 1);
                }

                rs.beforeFirst();
                int row = -1;
                while (rs.next())
                {
                    row++;

                    for (int i = 0; i < cols; i++)
                    {
                        records[row][i] = rs.getObject(i + 1);
                    }
                }

                azpzFrame mf = (azpzFrame) FrameHandling.getAnAzpzFrameByName("mainFrame");

                JTable jt = new JTable(records, headers);

                mf.getContentPane().add(jt);

            }
            catch (Exception e)
            {
                // TODO: handle exception
                System.out.println(e.getMessage());
            }

        } // end else

    }
}

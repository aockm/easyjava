package com.tockm;

import com.tockm.bean.TableInfo;
import com.tockm.buider.BuildBase;
import com.tockm.buider.BuildPo;
import com.tockm.buider.BuildTable;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        List<TableInfo> tables = BuildTable.getTables();
        BuildBase.execute();
        for (TableInfo table : tables) {
            BuildPo.execute(table);

        }

    }
}

package com.tockm;

import com.tockm.bean.TableInfo;
import com.tockm.buider.*;

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
            BuildQuery.execute(table);
            BuildMapper.execute(table);
            BuildMapperXml.execute(table);
            BuildService.execute(table);
            BuildServiceImpl.execute(table);
            BuildController.execute(table);

        }

    }
}

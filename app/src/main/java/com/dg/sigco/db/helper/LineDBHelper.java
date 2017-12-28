package com.dg.sigco.db.helper;

import android.database.Cursor;

import com.dg.sigco.card.data.Card;
import com.dg.sigco.card.data.Line;
import com.dg.sigco.db.shema.contract.CardContract;
import com.dg.sigco.db.shema.contract.ClientContract;
import com.dg.sigco.db.shema.contract.LineContract;

/**
 * Created by Sofia on 01/11/2017.
 */

public class LineDBHelper {

    private static LineDBHelper lineHelper;

    private DBHelperManager helperManager;
    private LineDBHelper(DBHelperManager helperManager){
        this.helperManager = helperManager;
    }

    public static LineDBHelper getInstance(DBHelperManager helperManager){
        if(lineHelper == null){
            lineHelper = new LineDBHelper(helperManager);
        }
        return lineHelper;
    }

    public long save(Line line) {
        return helperManager.getWritableDatabase().insert(
                LineContract.LineEntry.TABLE_NAME,
                null,
                line.toContentValues());

    }

    public Cursor getLine(int lineId) {
        String[] select = new String[]{
                LineContract.LineEntry._ID,
                LineContract.LineEntry.LINE_ID_COLUMN,
                LineContract.LineEntry.NAME_COLUMN,
                LineContract.LineEntry.CODE_COLUMN,
                LineContract.LineEntry.CONFIG_COLUMN
        };
        return helperManager.getReadableDatabase()
                .query(
                        LineContract.LineEntry.TABLE_NAME,
                        select,
                        LineContract.LineEntry.LINE_ID_COLUMN +" = "+lineId,
                        null,
                        null,
                        null,
                        null);
    }

    public Cursor getLines() {
        String[] select = new String[]{
                LineContract.LineEntry.LINE_ID_COLUMN,
                LineContract.LineEntry.NAME_COLUMN
        };
        return helperManager.getReadableDatabase()
                .query(
                        LineContract.LineEntry.TABLE_NAME,
                        select,
                        null,
                        null,
                        null,
                        null,
                        null);
    }
}

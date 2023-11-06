package cu.innovat.psigplus.dao;

import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;
import android.content.Context;
import android.util.Log;

import java.util.List;
import java.util.ArrayList;

import cu.innovat.psigplus.cim.AcademicGroup;
import cu.innovat.psigplus.cim.LevelGame;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 18/10/23
 */
public class DBManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DBManager(Context c) {
        this.context = c;

    }

    public void initDatabase() throws SQLException {
        dbHelper = new DatabaseHelper(this.context);
        database = dbHelper.getWritableDatabase();
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(this.context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void addAcademicGroups(List<AcademicGroup> groups){
        for(AcademicGroup g : groups){
            addAcademicGroup(g);
        }
    }

    public void addAcademicGroup(AcademicGroup g){
        dbHelper.addAcademicGroup(g);
    }

    public void addLevels(List<LevelGame> levels){
        for(LevelGame l : levels){
            addLevel(l);
        }
    }

    public void addLevel(LevelGame l){
        dbHelper.addLevelGame(l);
    }
}

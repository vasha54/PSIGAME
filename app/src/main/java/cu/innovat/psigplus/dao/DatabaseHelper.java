package cu.innovat.psigplus.dao;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.util.Log;

import cu.innovat.psigplus.cim.AcademicGroup;
import cu.innovat.psigplus.cim.LevelGame;
import cu.innovat.psigplus.dao.SchemaBD;
import cu.innovat.psigplus.dao.SchemaBD.AcademicGroupTable;
import cu.innovat.psigplus.dao.SchemaBD.LevelTable;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 18/10/23
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, SchemaBD.DATABASE_NAME, null, SchemaBD.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        onCreateSchemeDataBase(db);
        Log.i("TAG","onCreateDB");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("TAG","onUpgradeDB");
        onDropSchemeDataBase(db);
        onCreateSchemeDataBase(db);
    }

    private void onCreateSchemeDataBase(SQLiteDatabase db){
        db.execSQL(SchemaBD.SQL_CREATE_ACADEMIC_GROUP_TABLE);
        db.execSQL(SchemaBD.SQL_CREATE_LEVEL_TABLE);
    }

    private void onDropSchemeDataBase(SQLiteDatabase db){
        db.execSQL(SchemaBD.SQL_DROP_ACADEMIC_GROUP_TABLE);
        db.execSQL(SchemaBD.SQL_DROP_LEVEL_TABLE);
    }
    public void addLevelGame(LevelGame l){
        if(!existLevelGame(l.getSlug())){
            try{
                SQLiteDatabase db = this.getWritableDatabase();
                ContentValues values = new ContentValues();

                values.put(LevelTable.C_ID, l.getUuid());
                values.put(LevelTable.C_NAME, l.getName());
                values.put(LevelTable.C_SLUG, l.getSlug());
                values.put(LevelTable.C_CARDINAL, l.getCardinal());

                long valueInsert = db.insert(LevelTable.TABLE_NAME, null, values);
                db.close();

                if(valueInsert!=-1)
                    Log.d(SchemaBD.TAG_DATABASE, "Se insertó el Nivel de juego: "+l.toString());
                else
                    Log.d(SchemaBD.TAG_DATABASE, "Ocurrío un error en la insersección de Nivel de juego: "+l.toString());
            }catch (Exception e){
                Log.e(SchemaBD.TAG_DATABASE, "Ocurrío un error: "+e.getMessage());
            }
        }
    }

    public void addAcademicGroup(AcademicGroup g){
        if(!existAcademicGroup(g.getSlug())){
            try{
                SQLiteDatabase db = this.getWritableDatabase();
                ContentValues values = new ContentValues();

                values.put(AcademicGroupTable.C_ID, g.getUuid());
                values.put(AcademicGroupTable.C_NAME, g.getName());
                values.put(AcademicGroupTable.C_SLUG, g.getSlug());

                long valueInsert = db.insert(AcademicGroupTable.TABLE_NAME, null, values);
                db.close();

                if(valueInsert!=-1)
                    Log.d(SchemaBD.TAG_DATABASE, "Se insertó el Grupo Académico: "+g.toString());
                else
                    Log.d(SchemaBD.TAG_DATABASE, "Ocurrío un error en la insersección de Grupo Academico: "+g.toString());


            }catch (Exception e){
                Log.e(SchemaBD.TAG_DATABASE, "Ocurrío un error: "+e.getMessage());
            }
        }
    }

    public boolean existAcademicGroup(String slug){
        boolean exist =false;
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            String[] args = {slug};
            Cursor cursor = db.rawQuery(SchemaBD.SQL_EXIST_ACADEMIC_GROUP_WITH_SLUG,args);
            cursor.moveToFirst();
            if (cursor.getInt(0) == 1){
                exist =true;
                Log.i(SchemaBD.TAG_DATABASE, "Existe un grupo academico con slug: "+slug);
            }else{
                exist = false;
                Log.i(SchemaBD.TAG_DATABASE, "No existe un grupo academico con slug: "+slug);
            }
            cursor.close();
            db.close();
        }catch (Exception e){
            Log.e(SchemaBD.TAG_DATABASE, "Ocurrío un error: "+e.getMessage());
        }finally{
            return exist;
        }
    }

    public boolean existLevelGame(String slug){
        boolean exist =false;
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            String[] args = {slug};
            Cursor cursor = db.rawQuery(SchemaBD.SQL_EXIST_LEVEL_WITH_SLUG,args);
            cursor.moveToFirst();
            if (cursor.getInt(0) == 1){
                exist =true;
                Log.i(SchemaBD.TAG_DATABASE, "Existe un nivel de juego con slug: "+slug);
            }else{
                exist = false;
                Log.i(SchemaBD.TAG_DATABASE, "No existe un nivel de juego con slug: "+slug);
            }
            cursor.close();
            db.close();
        }catch (Exception e){
            Log.e(SchemaBD.TAG_DATABASE, "Ocurrío un error: "+e.getMessage());
        }finally{
            return exist;
        }
    }
}

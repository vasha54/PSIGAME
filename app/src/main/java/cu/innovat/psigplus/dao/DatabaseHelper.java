package cu.innovat.psigplus.dao;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.util.Log;

import cu.innovat.psigplus.cim.AcademicGroup;
import cu.innovat.psigplus.cim.GameLevel;
import cu.innovat.psigplus.cim.LevelGame;
import cu.innovat.psigplus.cim.Quizz;
import cu.innovat.psigplus.cim.questions.Question;
import cu.innovat.psigplus.cim.questions.TrueOrFalse;

import cu.innovat.psigplus.dao.SchemaBD;
import cu.innovat.psigplus.dao.SchemaBD.AcademicGroupTable;
import cu.innovat.psigplus.dao.SchemaBD.LevelTable;
import cu.innovat.psigplus.dao.SchemaBD.QuestionTable;
import cu.innovat.psigplus.dao.SchemaBD.TrueOrFalseTable;
import cu.innovat.psigplus.util.Util;

import java.util.ArrayList;
import java.util.List;

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
        db.execSQL(SchemaBD.SQL_CREATE_QUESTION_TABLE);
        db.execSQL(SchemaBD.SQL_CREATE_TRUE_OR_FALSE_TABLE);
    }

    private void onDropSchemeDataBase(SQLiteDatabase db){
        db.execSQL(SchemaBD.SQL_DROP_ACADEMIC_GROUP_TABLE);
        db.execSQL(SchemaBD.SQL_DROP_LEVEL_TABLE);
        db.execSQL(SchemaBD.SQL_DROP_TRUE_OR_FALSE_TABLE);
        db.execSQL(SchemaBD.SQL_DROP_QUESTION_TABLE);
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

    public void addQuizz(Quizz quizz){
        Log.i("TAG_DB_PSIGAME_PLUS",DatabaseHelper.class.getName()+"addQuizz");
        if(quizz!=null && quizz.getIdUsser()!=null){

        }
    }

    public void addQuestion(Question q){
        GameLevel level = q.getLevel();
        String idLevel = findIdLevel(level);
        if(idLevel !=null){
            q.setIdLevel(idLevel);
            if( !existQuestion( q.getUuid() ) ){
                try{
                    SQLiteDatabase db = this.getWritableDatabase();
                    ContentValues values = new ContentValues();

                    values.put(QuestionTable.C_ID, q.getUuid());
                    values.put(QuestionTable.C_STATEMENT,q.getSentence());
                    values.put(QuestionTable.C_LEVEL, q.getIdLevel());
                    values.put(QuestionTable.C_AMOUNT_USE, q.getUsed());
                    values.put(QuestionTable.C_LAST_USE,q.getLastUse());

                    long valueInsert = db.insert(QuestionTable.TABLE_NAME, null, values);

                    if( q instanceof TrueOrFalse){
                        TrueOrFalse tof = (TrueOrFalse) q;
                        if(tof !=null)
                            addQTrueOrFalse(tof,db);
                    }
                    db.close();

                    if(valueInsert!=-1)
                        Log.d(SchemaBD.TAG_DATABASE, "Se insertó la pregunta: "+q.toString());
                    else
                        Log.d(SchemaBD.TAG_DATABASE, "Ocurrío un error en la insersección de la pregunta: "+q.toString());
                }catch (Exception e){
                    Log.e(SchemaBD.TAG_DATABASE, "Ocurrío un error: "+e.getMessage());
                }
            }else{
                Log.e(SchemaBD.TAG_DATABASE, "Existe una pregunta con ID: "+q.getUuid());
            }
        }
    }

    public void addQTrueOrFalse(TrueOrFalse tof,SQLiteDatabase db){
        ContentValues values = new ContentValues();

        values.put(TrueOrFalseTable.C_ID, tof.getUuid());
        values.put(TrueOrFalseTable.C_RESULT,tof.isAnswer());

        long valueInsert = db.insert(TrueOrFalseTable.TABLE_NAME, null, values);

        if(valueInsert!=-1)
            Log.d(SchemaBD.TAG_DATABASE, "Se insertó la pregunta: "+tof.toString());
        else
            Log.d(SchemaBD.TAG_DATABASE, "Ocurrío un error en la insersección de la pregunta: "+tof.toString());
    }

    public List<Question> getAllQuestionsThisLevel(String idLevel){
        Log.i("TAG_DB_PSIGAME_PLUS",DatabaseHelper.class.getName()+"getAllQuestionsThisLevel");
        List<Question> questions = new ArrayList<Question>();
        List<Question> qTOF = getAllQuestionsTOFThisLevel(idLevel);

        questions.addAll(questions.size(),qTOF);

        return questions;
    }

    private List<Question> getAllQuestionsTOFThisLevel(String idLevel){
        Log.i("TAG_DB_PSIGAME_PLUS",DatabaseHelper.class.getName()+"getAllQuestionsTOFThisLevel");
        List<Question> questions = new ArrayList<Question>();
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            String[] args = {idLevel};
            Log.i("TAG_DB_PSIGAME_PLUS",DatabaseHelper.class.getName()+"getAllQuestionsTOFThisLevel"+idLevel);
            Cursor cursor = db.query(
                    QuestionTable.TABLE_NAME + " , " + TrueOrFalseTable.TABLE_NAME,
                    new String[] {QuestionTable.C_ID,QuestionTable.C_LEVEL,QuestionTable.C_STATEMENT,
                            QuestionTable.C_AMOUNT_USE,QuestionTable.C_LAST_USE,TrueOrFalseTable.C_RESULT},
                    QuestionTable.C_ID + " = " + TrueOrFalseTable.C_ID + " AND " + QuestionTable.C_LEVEL + " = '" +  idLevel+"'",
                    null, null, null, null);
                Log.i(SchemaBD.TAG_DATABASE, "Tuplas:"+cursor.moveToFirst());

            if(cursor.moveToFirst()){
                do{
                    String ID = cursor.getString(0);
                    String level = cursor.getString(1);
                    String statement = cursor.getString(2);
                    int amountUse = cursor.getInt(3);
                    long lastUse = cursor.getLong(4);
                    int answer = cursor.getInt(5);
                    //TODO String uuid, String sentence, long lastUse, int used, String idlevel, int answer
                    TrueOrFalse q =new TrueOrFalse(ID,statement,lastUse,amountUse,level,answer);
                    questions.add(q);
                }while(cursor.moveToNext());
            }
            cursor.close();
            db.close();
        }catch (Exception e){
            Log.e(SchemaBD.TAG_DATABASE, "Ocurrío un error: "+e.getMessage());
        }finally{
            return questions;
        }

    }

    public String findIdLevel(GameLevel level){
        String id =null;
        String slug = Util.toSlug(level.toString());
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            String[] args = {slug};
            Cursor cursor = db.rawQuery(SchemaBD.SQL_SELECT_ID_WITH_SLUG,args);
            if(cursor.moveToFirst()){
                id = cursor.getString(0);
                Log.i(SchemaBD.TAG_DATABASE, "Existe un level con id: "+id);
            }else{
                Log.i(SchemaBD.TAG_DATABASE, "No existe un level con ID: "+id);
            }
            cursor.close();
            db.close();
        }catch (Exception e){
            Log.e(SchemaBD.TAG_DATABASE, "Ocurrío un error: "+e.getMessage());
        }finally{
            return id;
        }
    }

    public boolean existQuestion(String id){
        boolean exist = false;
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            String[] args = {id};
            Cursor cursor = db.rawQuery(SchemaBD.SQL_EXIST_QUESTION_WITH_ID,args);
            if(cursor.moveToFirst()){
                if (cursor.getInt(0) == 1){
                    exist = true;
                    Log.i(SchemaBD.TAG_DATABASE, "Existe una pregunta con id: "+id);
                }else{
                    exist = false;
                    Log.i(SchemaBD.TAG_DATABASE, "No existe una pregunta con ID: "+id);
                }
            }
            cursor.close();
            db.close();
        }catch (Exception e){
            Log.e(SchemaBD.TAG_DATABASE, "Ocurrío un error consultado si existia una pregunta: "+e.getMessage());
        }finally{
            return exist;
        }
    }

    public boolean existAcademicGroup(String slug){
        boolean exist =false;
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            String[] args = {slug};
            Cursor cursor = db.rawQuery(SchemaBD.SQL_EXIST_ACADEMIC_GROUP_WITH_SLUG,args);
            if(cursor.moveToFirst()){
                if (cursor.getInt(0) == 1){
                    exist = true;
                    Log.i(SchemaBD.TAG_DATABASE, "Existe un grupo academico con slug: "+slug);
                }else{
                    exist = false;
                    Log.i(SchemaBD.TAG_DATABASE, "No existe un grupo academico con slug: "+slug);
                }
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
            if(cursor.moveToFirst()){
                if (cursor.getInt(0) == 1){
                    exist = true;
                    Log.i(SchemaBD.TAG_DATABASE, "Existe un nivel de juego con slug: "+slug);
                }else{
                    exist = false;
                    Log.i(SchemaBD.TAG_DATABASE, "No existe un nivel de juego con slug: "+slug);
                }
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

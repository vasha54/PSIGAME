package cu.innovat.psigplus.dao;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.util.Log;

import cu.innovat.psigplus.cim.*;
import cu.innovat.psigplus.cim.questions.MultipleChoise;
import cu.innovat.psigplus.cim.questions.Question;
import cu.innovat.psigplus.cim.questions.Sentence;
import cu.innovat.psigplus.cim.questions.TrueOrFalse;

import cu.innovat.psigplus.dao.SchemaBD;
import cu.innovat.psigplus.dao.SchemaBD.AcademicGroupTable;
import cu.innovat.psigplus.dao.SchemaBD.LevelTable;
import cu.innovat.psigplus.dao.SchemaBD.QuestionTable;
import cu.innovat.psigplus.dao.SchemaBD.TrueOrFalseTable;
import cu.innovat.psigplus.dao.SchemaBD.SentenceTable;
import cu.innovat.psigplus.dao.SchemaBD.MultipleChoiseTable;
import cu.innovat.psigplus.dao.SchemaBD.MultipleChoiseSentenceTable;
import cu.innovat.psigplus.dao.SchemaBD.PlayerTable;

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
        db.execSQL(SchemaBD.SQL_CREATE_SENTENCE_TABLE);
        db.execSQL(SchemaBD.SQL_CREATE_QUESTION_TABLE);
        db.execSQL(SchemaBD.SQL_CREATE_TRUE_OR_FALSE_TABLE);
        db.execSQL(SchemaBD.SQL_CREATE_MULTIPLE_CHOISE_TABLE);
        db.execSQL(SchemaBD.SQL_CREATE_MULTIPLE_CHOISE_SENTENCE_TABLE);
        db.execSQL(SchemaBD.SQL_CREATE_PLAYER_TABLE);
        db.execSQL(SchemaBD.SQL_CREATE_PLAYER_LEVEL_TABLE);
    }

    private void onDropSchemeDataBase(SQLiteDatabase db){
        db.execSQL(SchemaBD.SQL_DROP_PLAYER_LEVEL_TABLE);
        db.execSQL(SchemaBD.SQL_DROP_PLAYER_TABLE);
        db.execSQL(SchemaBD.SQL_DROP_ACADEMIC_GROUP_TABLE);
        db.execSQL(SchemaBD.SQL_DROP_LEVEL_TABLE);
        db.execSQL(SchemaBD.SQL_DROP_MULTIPLE_CHOISE_SENTENCE_TABLE);
        db.execSQL(SchemaBD.SQL_DROP_SENTENCE_TABLE);
        db.execSQL(SchemaBD.SQL_DROP_TRUE_OR_FALSE_TABLE);
        db.execSQL(SchemaBD.SQL_DROP_MULTIPLE_CHOISE_TABLE);
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
                    }else if( q instanceof MultipleChoise){
                        MultipleChoise mc = (MultipleChoise) q;
                        if(mc != null)
                            addMultipleChoise(mc,db);
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

    public void addSentences(List<Sentence> senteneces){
        Log.d(SchemaBD.TAG_DATABASE,"Cantidad de sentencias a insertar es:"+String.valueOf(senteneces.size()));
        for (Sentence s: senteneces) {
            addSentence(s);
        }
    }

    public void addSentence(Sentence s){
        if( existSentence(s.getId()) == false){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(SentenceTable.C_SENTENCE,s.getText());
            values.put(SentenceTable.C_ID,s.getId());
            values.put(SentenceTable.C_SLUG,s.getSlug());

            long valueInsert = db.insert(SentenceTable.TABLE_NAME, null, values);

            if(valueInsert!=-1)
                Log.d(SchemaBD.TAG_DATABASE, "Se insertó la sentencia: "+s.toString());
            else
                Log.d(SchemaBD.TAG_DATABASE, "Ocurrío un error en la insersección de la sentencia: "+s.toString());
            db.close();
        }else{
            Log.e(SchemaBD.TAG_DATABASE, "Existe una sentencia con ID: "+s.getId());
        }
    }

    public void addMultipleChoiseSentences(MultipleChoise mch, List<Sentence> sentences){
        if(mch != null && !sentences.isEmpty()){
            String idMCH = mch.getUuid();
            for(Sentence s : sentences){
                if(existMultipleChoiseSentence(idMCH,s.getId()) == false){
                    ContentValues values = new ContentValues();

                    SQLiteDatabase db = this.getWritableDatabase();

                    values.put(MultipleChoiseSentenceTable.C_ID_SENTENCE,s.getId());
                    values.put(MultipleChoiseSentenceTable.C_ID_QUESTION,mch.getUuid());
                    values.put(MultipleChoiseSentenceTable.C_CHOISE,(s.isCorrect()==true ? 1: 0));

                    long valueInsert = db.insert(MultipleChoiseSentenceTable.TABLE_NAME, null, values);
                    if(valueInsert!=-1)
                        Log.d(SchemaBD.TAG_DATABASE, "Se insertó la asociacion entre la sentencia: "+s.toString()+
                            " y la pregunta:"+mch.toString());
                    else
                        Log.d(SchemaBD.TAG_DATABASE, "Ocurrío un error en la insersección de la asociacion entre la sentencia: "+
                            s.toString()+ " y la pregunta:"+mch.toString());

                    db.close();
                }else{
                    Log.e(SchemaBD.TAG_DATABASE, "Existe una asociacion entre la sentencia con ID :"+s.getId()+
                            " y la pregunta con ID:"+idMCH);
                }
            }
        }
    }

    public void addMultipleChoise(MultipleChoise mch, SQLiteDatabase db){
        List<Sentence> sentences = mch.getSentences();

        ContentValues values = new ContentValues();
        values.put(MultipleChoiseTable.C_ID, mch.getUuid());

        long valueInsert = db.insert(MultipleChoiseTable.TABLE_NAME, null, values);

        if(valueInsert!=-1)
            Log.d(SchemaBD.TAG_DATABASE, "Se insertó la pregunta: "+mch.toString());
        else
            Log.d(SchemaBD.TAG_DATABASE, "Ocurrío un error en la insersección de la pregunta: "+mch.toString());
    }

    public void addQTrueOrFalse(TrueOrFalse tof,SQLiteDatabase db){
        ContentValues values = new ContentValues();

        values.put(TrueOrFalseTable.C_ID, tof.getUuid());
        values.put(TrueOrFalseTable.C_RESULT,(tof.isAnswer()==true ? 1 : 0));

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
        List<MultipleChoise> qMCH = getAllQuestionsMCHThisLevel(idLevel);
        Log.i("TAG_DB_PSIGAME_PLUS",DatabaseHelper.class.getName()+"getAllQuestionsThisLevel size:"+questions.size());
        questions.addAll(questions.size(),qTOF);
        Log.i("TAG_DB_PSIGAME_PLUS",DatabaseHelper.class.getName()+"getAllQuestionsThisLevel size:"+questions.size());
        questions.addAll(questions.size(),qMCH);
        Log.i("TAG_DB_PSIGAME_PLUS",DatabaseHelper.class.getName()+"getAllQuestionsThisLevel size:"+questions.size());

        return questions;
    }

    private List<Sentence> findChoiseSentences(String idQMultipleChoise){
        Log.i("TAG_DB_PSIGAME_PLUS",DatabaseHelper.class.getName()+"findChoiseSentences para id:"+idQMultipleChoise);
        List<Sentence> sentences = new ArrayList<Sentence>();
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            String[] args = {idQMultipleChoise};
            Log.i("TAG_DB_PSIGAME_PLUS",DatabaseHelper.class.getName()+"findChoiseSentences para id:"+idQMultipleChoise);
            Cursor cursor = db.query(
                    MultipleChoiseSentenceTable.TABLE_NAME + " , " + SentenceTable.TABLE_NAME,
                    new String[] {SentenceTable.C_ID,SentenceTable.C_SENTENCE,SentenceTable.C_SLUG,
                            MultipleChoiseSentenceTable.C_CHOISE},
                    MultipleChoiseSentenceTable.C_ID_SENTENCE + " = " + SentenceTable.C_ID + " AND " +
                            MultipleChoiseSentenceTable.C_ID_QUESTION + " = '" +  idQMultipleChoise+
                            "'", null, null, null, null);
            Log.i(SchemaBD.TAG_DATABASE, "Tuplas:"+cursor.moveToFirst());
            if(cursor.moveToFirst()){
                do{
                    String ID = cursor.getString(0);
                    String sentence = cursor.getString(1);
                    String slug = cursor.getString(2);
                    boolean choise = (cursor.getInt(3) == 1 ? true : false);
                    Sentence s = new Sentence(sentence,slug,ID,choise);
                    sentences.add(s);
                }while(cursor.moveToNext());
            }
            cursor.close();
            db.close();
        }catch (Exception e){
            Log.e(SchemaBD.TAG_DATABASE, "Ocurrío un error: "+e.getMessage());
        }finally {
            return sentences;
        }

    }

    private List<MultipleChoise> getAllQuestionsMCHThisLevel(String idLevel){
        Log.i("TAG_DB_PSIGAME_PLUS",DatabaseHelper.class.getName()+"getAllQuestionsMCHThisLevel");
        List<MultipleChoise> questions = new ArrayList<MultipleChoise>();
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            String[] args = {idLevel};
            Log.i("TAG_DB_PSIGAME_PLUS",DatabaseHelper.class.getName()+"getAllQuestionsMCHThisLevel"+idLevel);
            Cursor cursor = db.query(
                    QuestionTable.TABLE_NAME + " , " + MultipleChoiseTable.TABLE_NAME,
                    new String[] {QuestionTable.C_ID,QuestionTable.C_LEVEL,QuestionTable.C_STATEMENT,
                            QuestionTable.C_AMOUNT_USE,QuestionTable.C_LAST_USE},
                    QuestionTable.C_ID + " = " + MultipleChoiseTable.C_ID + " AND " + QuestionTable.C_LEVEL + " = '" +  idLevel+"'",
                    null, null, null, null);
            Log.i(SchemaBD.TAG_DATABASE, "Tuplas:"+cursor.moveToFirst());
            if(cursor.moveToFirst()){
                do{
                    String ID = cursor.getString(0);
                    String level = cursor.getString(1);
                    String statement = cursor.getString(2);
                    int amountUse = cursor.getInt(3);
                    long lastUse = cursor.getLong(4);
                    MultipleChoise q = new MultipleChoise(ID,statement,lastUse,amountUse,level);
                    questions.add(q);
                }while(cursor.moveToNext());
            }
            cursor.close();
            db.close();
            for(MultipleChoise q :questions){
                List<Sentence> sentences = findChoiseSentences(q.getUuid());
                q.setSentences(sentences);
            }
        }catch (Exception e){
            Log.e(SchemaBD.TAG_DATABASE, "Ocurrío un error: "+e.getMessage());
        }finally {
            return questions;
        }
    }

    private List<Question> getAllQuestionsTOFThisLevel(String idLevel){
        Log.i("TAG_DB_PSIGAME_PLUS",DatabaseHelper.class.getName()+"getAllQuestionsTOFThisLevel");
        List<Question> questions = new ArrayList<Question>();
        try{
            SQLiteDatabase db = this.getReadableDatabase();
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
                    boolean answer =  (cursor.getInt(5)==1 ? true : false);
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
            SQLiteDatabase db = this.getReadableDatabase();
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

    public String findIDAcademicGroupBySlug(String slug){
        String id = null;
        //TODO Falta;
        return id;
    }

    public boolean existSentence(String id){
        boolean exist = false;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            String[] args = {id};
            Cursor cursor = db.rawQuery(SchemaBD.SQL_EXIST_SENTENCE_WITH_ID,args);
            if(cursor.moveToFirst()){
                if (cursor.getInt(0) == 1){
                    exist = true;
                    Log.i(SchemaBD.TAG_DATABASE, "Existe una sentencia con id: "+id);
                }else{
                    exist = false;
                    Log.i(SchemaBD.TAG_DATABASE, "No existe una sentencia con ID: "+id);
                }
            }
            cursor.close();
            db.close();
        } catch (Exception e){
            Log.e(SchemaBD.TAG_DATABASE, "Ocurrío un error consultado si existia una sentencia: "+e.getMessage());
        } finally {
            return exist;
        }

    }

    public boolean existQuestion(String id){
        boolean exist = false;
        try{
            SQLiteDatabase db = this.getReadableDatabase();
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
            SQLiteDatabase db = this.getReadableDatabase();
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
            SQLiteDatabase db = this.getReadableDatabase();
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

    public boolean existMultipleChoiseSentence(String idMCH, String idSentence){
        boolean exist =false;
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            String[] args = {idMCH,idSentence};
            Cursor cursor = db.rawQuery(SchemaBD.SQL_EXIST_MULTIPLE_CHOISE_SENTENCE_ID,args);
            if(cursor.moveToFirst()){
                if (cursor.getInt(0) == 1){
                    exist = true;
                    Log.i(SchemaBD.TAG_DATABASE,
                            "Existe una relación entre la pregunta con id: "+idMCH+" y la sentencia con id: "+idSentence);
                }else{
                    exist = false;
                    Log.i(SchemaBD.TAG_DATABASE,
                            "No existe una relación entre la pregunta con id: "+idMCH+" y la sentencia con id: "+idSentence);
                }
            }
            cursor.close();
            db.close();
        }catch (Exception e){
            Log.e(SchemaBD.TAG_DATABASE, "Ocurrío un error: "+e.getMessage());
        }finally {
            return exist;
        }
    }

    public void updateLastUseQuestion(String idQuestion){
        if( existQuestion(idQuestion) ){
            try{
                SQLiteDatabase db = this.getWritableDatabase();
                long timstamp = Util.getCurrentTimeStamp();
                ContentValues update = new ContentValues();
                update.put(QuestionTable.C_LAST_USE, timstamp);
                String[] args = new String []{idQuestion};
                db.update(QuestionTable.TABLE_NAME, update, QuestionTable.C_ID+"=?", args);
                db.close();
            }catch (Exception e){
                Log.e(SchemaBD.TAG_DATABASE, "Ocurrío un error: "+e.getMessage());
            }finally {

            }
        }
    }

    public void incrementCountUseQuestion(String idQuestion){
        if( existQuestion(idQuestion)){
            try{
                int aomuntUse = Integer.MIN_VALUE;
                SQLiteDatabase db = this.getReadableDatabase();
                String[] args = {idQuestion};
                Cursor cursor = db.rawQuery(SchemaBD.SQL_SELECT_AMOUNT_USE_QUESTION_WITH_ID,args);
                if(cursor.moveToFirst()){
                    aomuntUse = cursor.getInt(0);
                }
                cursor.close();
                db.close();

                if(aomuntUse != Integer.MIN_VALUE){
                    aomuntUse++;
                    db = this.getWritableDatabase();
                    ContentValues update = new ContentValues();
                    update.put(QuestionTable.C_AMOUNT_USE, aomuntUse);
                    db.update(QuestionTable.TABLE_NAME, update, QuestionTable.C_ID+"=?", args);
                    db.close();
                }

            }catch (Exception e){
                Log.e(SchemaBD.TAG_DATABASE, "Ocurrío un error: "+e.getMessage());
            }finally {

            }
        }
    }

    public void registerAnswer(String idUser, String idQuizz, String idQuestion, int result){
        //TODO Falta
    }

    public boolean registerPlayer(Player player){
        boolean register = false;
        if( player!=null){
            String idGroup = findIDAcademicGroupBySlug(player.getSlugGroup());
            if(idGroup != null){
                player.setIdGroup(idGroup);

                SQLiteDatabase db = this.getWritableDatabase();
                ContentValues values = new ContentValues();

                values.put(PlayerTable.C_CI,player.getCi());
                values.put(PlayerTable.C_ID,player.getIdUser());
                values.put(PlayerTable.C_NAME,player.getName());
                values.put(PlayerTable.C_SURNAME,player.getSurname());
                values.put(PlayerTable.C_ID_GROUP,player.getIdGroup());
                values.put(PlayerTable.C_EMIE,player.getIMEI());
                values.put(PlayerTable.C_PHONE_NUMBER,player.getNumberPhone());
                values.put(PlayerTable.C_ACTIVE,(player.isActivate()? 1 : 0));

                long valueInsert = db.insert(PlayerTable.TABLE_NAME, null, values);

                if(valueInsert!=-1) {
                    db = this.getWritableDatabase();

                    ContentValues updateDeactivate = new ContentValues();
                    updateDeactivate.put(PlayerTable.C_ACTIVE, 0);
                    db.update(PlayerTable.TABLE_NAME, updateDeactivate, null, null);

                    ContentValues updatePlayer = new ContentValues();
                    updatePlayer.put(PlayerTable.C_ACTIVE, (player.isActivate()? 1 : 0));
                    String[] args = {player.getIdUser()};
                    db.update(PlayerTable.TABLE_NAME, updatePlayer, PlayerTable.C_ID+"=?", args);


                    register =true;
                    Log.d(SchemaBD.TAG_DATABASE, "Se insertó el jugador: " + player.toString());
                } else {
                    Log.d(SchemaBD.TAG_DATABASE, "Ocurrío un error en la insersección del jugador: " + player.toString());
                }
                db.close();

            }else{
                Log.i(SchemaBD.TAG_DATABASE, "No existe un grupo con slug: "+player.getSlugGroup());
            }
        }

        return register;
    }

    public List<LevelGame> getLevelAviableCurrentPlayer(){
        List<LevelGame> levels = new ArrayList<LevelGame>();
        String idCurrentPlayer = getIDCurrentPlayer();
        if  (idCurrentPlayer != null){
            List<String> idLevel = new ArrayList<String>();
            //TODO Falta
        }
        return levels;
    }

    public String getIDCurrentPlayer(){
        String id = null;
        //TODO Falta
        return id;
    }
}

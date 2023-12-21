package cu.innovat.psigplus.dao;

import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;
import android.content.Context;
import android.util.Log;

import java.util.List;
import java.util.ArrayList;

import cu.innovat.psigplus.cim.*;
import cu.innovat.psigplus.cim.questions.MultipleChoise;
import cu.innovat.psigplus.cim.questions.Question;
import cu.innovat.psigplus.cim.questions.Sentence;

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

    public void addQuestions(List<Question> questions){
        for(Question q : questions){
            addQuestion(q);
            if( q instanceof MultipleChoise){
                MultipleChoise mch = (MultipleChoise) q;
                if(mch !=null){
                    List<Sentence> sentences = mch.getSentences();
                    Log.i("TAG_DB_PSIGAME_PLUS","Insertando las opciones ("+String.valueOf(sentences.size())+") de la" +
                            " pregunta :"+mch.getUuid());
                    addSentences(sentences);
                    addMultipleChoiseSentences(mch,sentences);
                }

            }
        }
    }

    public void addSentences(List<Sentence> sentences){
        dbHelper.addSentences(sentences);
    }

    public void addMultipleChoiseSentences(MultipleChoise mch, List<Sentence> sentences){
        dbHelper.addMultipleChoiseSentences(mch,sentences);
    }

    public void addQuestion(Question q){ dbHelper.addQuestion(q); }

    public String findIdLevel(GameLevel level){
        return dbHelper.findIdLevel(level);
    }

    public void addQuizz(Quizz quizz){
        Log.i("TAG_DB_PSIGAME_PLUS",DBManager.class.getName()+"addQuizz");
        dbHelper.addQuizz(quizz);
    }

    public List<Question> getAllQuestionsThisLevel(String idLevel){
        Log.i("TAG_DB_PSIGAME_PLUS",DBManager.class.getName()+"getAllQuestionsThisLevel");
        return dbHelper.getAllQuestionsThisLevel(idLevel);
    }

    public void updateLastUseQuestion(String idQuestion){
        dbHelper.updateLastUseQuestion(idQuestion);
        dbHelper.incrementCountUseQuestion(idQuestion);
    }

    public void registerAnswer(String idUser, String idQuizz, String idQuestion, int result){
        dbHelper.registerAnswer(idUser, idQuizz, idQuestion, result);
    }

    public boolean registerPlayer(Player player){
        return dbHelper.registerPlayer(player);
    }

    public List<LevelGame> getLevelAviableCurrentPlayer(){
        return dbHelper.getLevelAviableCurrentPlayer();
    }
}

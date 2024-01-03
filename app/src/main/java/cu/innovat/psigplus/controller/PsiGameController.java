package cu.innovat.psigplus.controller;

import android.content.Context;


import cu.innovat.psigplus.cim.*;
import cu.innovat.psigplus.cim.questions.MultipleChoise;
import cu.innovat.psigplus.cim.questions.Question;
import cu.innovat.psigplus.cim.questions.TrueOrFalse;
import cu.innovat.psigplus.cim.questions.QuestionComparator;
import cu.innovat.psigplus.dao.DBManager;
import cu.innovat.psigplus.R;
import cu.innovat.psigplus.util.LOG;
import cu.innovat.psigplus.util.ReadJson;
import cu.innovat.psigplus.util.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 30/10/23
 */
public class PsiGameController {
    private static PsiGameController instance;

    private Context context;
    private DBManager managerDB;

    private PsiGameController(Context c) {
        this.context = c;
        this.managerDB = new DBManager(this.context);
        this.managerDB.initDatabase();
        insertDataDefault();
    }

    public static PsiGameController getInstance(Context c) {
        if (instance == null) {
            synchronized (PsiGameController.class) {
                if(instance==null) {
                    instance = new PsiGameController(c);
                }
            }
        }
        return instance;
    }

    private void insertDataDefault(){
        LOG.i("TAG_DB_PSIGAME_PLUS","Iniciando inserssción de datos por defectos");
        LOG.i("TAG_DB_PSIGAME_PLUS","Insertando Grupos Academicos");
        insertAcademicGroup();
        LOG.i("TAG_DB_PSIGAME_PLUS","Insertando Niveles del juego");
        insertLevelGame();
        LOG.i("TAG_DB_PSIGAME_PLUS","Insertando preguntas");
        insertQuestions();
        LOG.i("TAG_DB_PSIGAME_PLUS","Fin inserssción de datos por defectos");
    }

    private void insertAcademicGroup(){
        String [] groups = this.context.getResources().getStringArray(R.array.list_names_group_student);
        List<AcademicGroup> g =new ArrayList<AcademicGroup>();
        for(int i=0;i<groups.length;i++){
            String slug = Util.toSlug(groups[i]);
            String id = Util.generateUUID();
            AcademicGroup group = new AcademicGroup(id,groups[i],slug);
            g.add(group);
        }
        this.managerDB.addAcademicGroups(g);
    }

    private void insertLevelGame(){
        List<LevelGame> l = new ArrayList<LevelGame>();
        for(int i=0;i< GameLevel.values().length;i++){
            GameLevel gl = GameLevel.values()[i];
            String name = gl.toString();
            String slug = Util.toSlug(gl.toString());
            String id = Util.generateUUID();
            int cardinal = gl.ordinal();
            LevelGame level = new LevelGame(id, name, slug, cardinal);
            l.add(level);
        }
        this.managerDB.addLevels(l);
    }

    private void insertQuestions(){
        try{
            List<Question> questionsTOF = ReadJson.readQuestion(this.context,"Q_TRUE_OR_FALSE.json");
            List<Question> questionsMCH = ReadJson.readQuestion(this.context,"Q_MULTIPLE_CHOISE.json");

            List<Question> questions = new ArrayList<Question>();
            questions.addAll(questions.size(),questionsTOF);
            questions.addAll(questions.size(),questionsMCH);

            LOG.i("TAG_DB_PSIGAME_PLUS","Insertando preguntas");
            this.managerDB.addQuestions(questions);
        } catch (Exception e){
            LOG.i("TAG_DB_PSIGAME_PLUS","Ocurrio el siguiente error"+e.getMessage());
        }
    }

    public Quizz generateQuizz(GameLevel level){
        Quizz quizz = null;
        LOG.i("TAG_DB_PSIGAME_PLUS","generateQuizz(GameLevel level)");
        String idLevel = managerDB.findIdLevel(level);
        if(idLevel != null){
            quizz =new Quizz();
            quizz.setIdQuizz(Util.generateUUID());
            quizz.setDate(Util.getCurrentTimeStamp());
            quizz.setLevel(level);
            quizz.setIdLevel(idLevel);
            List<Question> questions = this.generateQuestions(idLevel,level);
            quizz.setQuestions(questions);
            int nquestions = questions.size();
            int lifes = nquestions-(int)Math.ceil((nquestions*90)/100);
            lifes = Math.max(1,lifes);
            quizz.setLifes(lifes);
            String idPlayerActive = managerDB.getIDCurrentPlayer();
            quizz.setIdPlayer(idPlayerActive);
        }
        return quizz;
    }

    public void addQuizz(Quizz quizz){
        this.managerDB.addQuizz(quizz);
    }

    //TODO Aqui es el metodo que debemos trabajar para lograr la verdadera aleotoriedad de las preguntas
    List<Question> generateQuestions(String idLevel,GameLevel level){
        LOG.i("TAG_DB_PSIGAME_PLUS",PsiGameController.class.getName()+"generateQuestions");
        List<Question> questions = managerDB.getAllQuestionsThisLevel(idLevel);
        questions.sort(new QuestionComparator());
        int select = (int)Math.ceil(questions.size()/3);

        List<Question> selects = new ArrayList<Question>();

        // Trato de coger la misma cantidad de tipologias de preguntas
        int ctof = select/2;
        int cmch = select - ctof;

        int index = 0;
        while(index < questions.size()){
            Question q = questions.get(index);
            if(q instanceof TrueOrFalse && ctof > 0){
                q.setLevel(level);
                selects.add(q);
                ctof--;
                questions.remove(index);
            }else if(q instanceof MultipleChoise && cmch > 0){
                q.setLevel(level);
                selects.add(q);
                cmch--;
                questions.remove(index);
            }else{
                index++;
            }
        }

        //Completo con cualquier tipologia de preguntas en caso de que me falte
        // para el cuestionario
        while(cmch+ctof>0 && questions.isEmpty()==false){
            Question q = questions.remove(0);
            q.setLevel(level);
            selects.add(q);
            ctof--;
        }

        //Alterno tipologias de preguntas
        for(int i=0;i<selects.size();i++){
            if(i%2==0 && selects.get(i) instanceof MultipleChoise){
                int j=i;
                do{ j++; }while (j<selects.size() && selects.get(j) instanceof  MultipleChoise);
                if(j<selects.size()){
                    Collections.swap(selects,i,j);
                }
            }
            if(i%2==1 && selects.get(i) instanceof TrueOrFalse){
                int j=i;
                do{ j++; }while (j<selects.size() && selects.get(j) instanceof  TrueOrFalse);
                if(j<selects.size()){
                    Collections.swap(selects,i,j);
                }
            }
        }

        return selects;
    }

    public void updateLastUseQuestion(String idQuestion){
        managerDB.updateLastUseQuestion(idQuestion);
    }

    public void registerAnswer(String idUser, String idQuizz, String idQuestion, int result){
        managerDB.registerAnswer(idUser,idQuizz,idQuestion,result);
    }

    public void registerResultQuizz(String idPlayer, String idQuizz,int result){
        managerDB.registerResultQuizz(idPlayer,idQuizz,result);
    }

    public boolean registerPlayer(Player player){
        return managerDB.registerPlayer(player);
    }

    public List<GameLevel> getLevelAviableCurrentPlayer(){
        return managerDB.getLevelAviableCurrentPlayer();
    }

    public String getIDCurrentPlayer(){
        return managerDB.getIDCurrentPlayer();
    }

    public Player getCurrentPlayer(){
        return managerDB.getCurrentPlayer();
    }

    public boolean canCreateQuizzThisLevel(GameLevel level){
        return managerDB.canCreateQuizzThisLevel(level);
    }

    public void updateStatistics(List<Statistics> statistics){
        for(Statistics stat : statistics){
            this.managerDB.updateStatistic(stat);
        }
    }

    public void resetRegisterPlayer(){
        this.managerDB.resetRegisterPlayer();
    }
}

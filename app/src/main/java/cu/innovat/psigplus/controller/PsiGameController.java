package cu.innovat.psigplus.controller;

import android.content.Context;
import android.util.Log;

import cu.innovat.psigplus.cim.AcademicGroup;
import cu.innovat.psigplus.cim.GameLevel;
import cu.innovat.psigplus.cim.LevelGame;
import cu.innovat.psigplus.dao.DBManager;
import cu.innovat.psigplus.R;
import cu.innovat.psigplus.util.Util;

import java.util.ArrayList;
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

        Log.i("TAG_DB_PSIGAME_PLUS","Iniciando inserssción de datos por defectos");
        Log.i("TAG_DB_PSIGAME_PLUS","Insertando Grupos Academicos");
        insertAcademicGroup();
        Log.i("TAG_DB_PSIGAME_PLUS","Insertando Niveles del juego");
        insertLevelGame();
        Log.i("TAG_DB_PSIGAME_PLUS","Fin inserssción de datos por defectos");

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
}

package cu.innovat.psigplus.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import cu.innovat.psigplus.cim.GameLevel;
import cu.innovat.psigplus.cim.LevelGame;
import cu.innovat.psigplus.cim.questions.Question;
import cu.innovat.psigplus.cim.questions.TrueOrFalse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.res.AssetManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 3/11/23
 */
public class ReadJson {

    public static String pathAsset="config";

    public static JSONObject getJSONObjectThisFile(AssetManager assetFiles ,
                                                   String filename) throws IOException, JSONException{
        InputStream file = null;
        file = assetFiles.open(pathAsset+File.separator+filename);
        BufferedReader in = new BufferedReader(new InputStreamReader(file));
        String jsonStr="",line;
        while((line = in.readLine()) != null){
            jsonStr += line;
        }
        JSONObject obj = new JSONObject(jsonStr);
        return obj;
    }

    public static JSONArray getJSONArrayThisFile(AssetManager assetFiles ,
                                                 String filename) throws IOException, JSONException{
        InputStream file = null;
        file = assetFiles.open(pathAsset+File.separator+filename);
        BufferedReader in = new BufferedReader(new InputStreamReader(file));
        String jsonStr="",line;

        while((line = in.readLine()) != null){
            jsonStr += line;
        }
        JSONArray array = new JSONArray(jsonStr);
        return array;
    }

    public static TrueOrFalse readTrueOrFalse(JSONObject _objJSON) throws  JSONException{
        TrueOrFalse p = null;
        if(_objJSON.has("id") && _objJSON.has("level") &&
                _objJSON.has("statement") && _objJSON.has("isTrue")){
            String id = _objJSON.getString("id");
            String statement = _objJSON.getString("statement");
            int isTrue = Integer.parseInt(_objJSON.getString("isTrue"));
            GameLevel level = GameLevel.valueOf(_objJSON.getString("level"));
            p = new TrueOrFalse(id,statement,Util.getCurrentTimeStamp(),level,isTrue);
        }
        return p;
    }

    public static Question parseQuestion(JSONObject _objJSON) throws JSONException{
        Question q = null;
        String type = _objJSON.getString("type_question");
        switch (type){
            case "trueorfalse":
                q = readTrueOrFalse(_objJSON);
                break;
        }
        return q;
    }

    public static List<Question> readQuestion(Context _contex,String fileName) throws IOException, JSONException{
        List<Question> questions = new ArrayList<>();
        if(_contex!=null){
            AssetManager assetFiles = _contex.getAssets();
            JSONArray arrayJSONQuestion=getJSONArrayThisFile(assetFiles,fileName);
            for(int i=0;i<arrayJSONQuestion.length();i++){
                JSONObject objJSON = arrayJSONQuestion.getJSONObject(i);
                if(objJSON.has("type_question")){
                    Question q = parseQuestion(objJSON);
                    if(q!=null) questions.add(q);
                }
            }
        }
        return questions;
    }
}

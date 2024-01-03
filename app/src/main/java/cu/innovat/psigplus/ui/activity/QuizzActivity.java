package cu.innovat.psigplus.ui.activity;

import java.io.File;
import java.io.IOException;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.*;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.content.res.AssetFileDescriptor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import cu.innovat.psigplus.R;
import cu.innovat.psigplus.cim.Constant;
import cu.innovat.psigplus.cim.GameLevel;
import cu.innovat.psigplus.cim.Quizz;
import cu.innovat.psigplus.cim.ResultQuizz;
import cu.innovat.psigplus.cim.questions.Question;
import cu.innovat.psigplus.controller.PsiGameController;
import cu.innovat.psigplus.interfaces.IObserverClickButtonResultQuizz;
import cu.innovat.psigplus.interfaces.IObserverClickButtonStartQuizz;
import cu.innovat.psigplus.ui.fragment.HomeFragment;
import cu.innovat.psigplus.ui.fragment.QuizzInformationFragment;
import cu.innovat.psigplus.ui.fragment.QuizzResultFragment;
import cu.innovat.psigplus.ui.fragment.question.FactoryViewFragmentQuestion;
import cu.innovat.psigplus.ui.fragment.question.QuestionFragment;
import cu.innovat.psigplus.util.Util;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 10/12/23
 */
public class QuizzActivity extends AppCompatActivity implements View.OnClickListener , OnPreparedListener,
        OnErrorListener, IObserverClickButtonStartQuizz, IObserverClickButtonResultQuizz {

    private PsiGameController controller;
    private Quizz quizz;
    private List<QuestionFragment> fragments;
    private int indexFragments;
    private Button buttonCheck;
    private FragmentManager fragmentManager ;
    private TextView tviewCurrentQuestion;
    private TextView tviewNumberQuestions;
    private TextView tviewTime;
    private TextView tviewLife;
    private TextView tviewText1;
    private TextView tviewText2;
    private ImageView imageViewHeart;
    private ProgressBar progressBarTime;
    private int timeToPass;
    private int countLifes;
    private Handler handlerTime = null;
    private MediaPlayer mediaPlayer;

    private QuizzInformationFragment fragmentInformation;
    private QuizzResultFragment fragmentResult;

    private Runnable updateTimeView = new Runnable() {
        @Override
        public void run() {
            timeToPass--;
            if( timeToPass >= 0 ){
                if(progressBarTime != null) progressBarTime.setProgress(timeToPass,true);
                if(tviewTime != null) tviewTime.setText(Util.convertToMMSS(timeToPass));
                handlerTime.postDelayed(updateTimeView, 1000);
            }else{
                handlerTime.removeCallbacks(updateTimeView);
                checkAnswerTimeOut();
            }

        }
    };

    public QuizzActivity(){
        super();
        controller = null;
        quizz = null;
        fragments = new ArrayList<QuestionFragment>();
        handlerTime =null;
        fragmentInformation = null;
        fragmentResult = null;
    }

    private void notificationSound(String filename){
        AssetFileDescriptor descriptor;
        try {
            descriptor = getAssets().openFd("sounds"+File.separator+ filename);
            mediaPlayer.reset();
            mediaPlayer.setDataSource(descriptor.getFileDescriptor(),
                    descriptor.getStartOffset(),
                    descriptor.getLength());
            descriptor.close();
            mediaPlayer.setVolume(1f, 1f);
            mediaPlayer.setLooping(false);
            mediaPlayer.prepare();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void  gameOver(){
        buttonCheck.setEnabled(false);
        handlerTime.removeCallbacks(updateTimeView);
        buttonCheck.setVisibility(View.INVISIBLE);

        fragmentResult = new QuizzResultFragment(getString(R.string.title_text_lose),
                getString(R.string.description_text_lose), ResultQuizz.QUIZZ_LOSE);
        fragmentResult.attachOCBRQ(this);
        if(fragmentManager!=null){
            fragmentManager.beginTransaction().
                    replace(R.id.container_question,fragmentResult,fragmentResult.toString()).
                    addToBackStack(null).
                    commit();
        }
    }

    private void winGame(){
        buttonCheck.setEnabled(false);
        handlerTime.removeCallbacks(updateTimeView);
        buttonCheck.setVisibility(View.INVISIBLE);

        fragmentResult = new QuizzResultFragment(getString(R.string.title_text_win),
                getString(R.string.description_text_win), ResultQuizz.QUIZZ_WIN);
        fragmentResult.attachOCBRQ(this);
        if(fragmentManager!=null){
            fragmentManager.beginTransaction().
                    replace(R.id.container_question,fragmentResult,fragmentResult.toString()).
                    addToBackStack(null).
                    commit();
        }
    }

    private void checkAnswer(){
        if(handlerTime != null && updateTimeView!=null)  handlerTime.removeCallbacks(updateTimeView);
        Fragment fragment = null;
        if(indexFragments < fragments.size()) {
            fragment = getSupportFragmentManager().findFragmentByTag(fragments.get(indexFragments).getUuid());
        }
        if( fragment != null && fragment instanceof QuestionFragment){
            QuestionFragment questionF = (QuestionFragment) fragment;
            if(questionF != null){
                int result = 0;
                if(questionF.checkQuestion() == 1){
                    notificationSound("correct.mp3");
                    result = 1;
                }else if(questionF.checkQuestion() == 0){
                    countLifes --;
                    if(tviewLife != null) tviewLife.setText(Util.convertTo00(Math.max(countLifes,0)));
                    notificationSound("wrong.mp3");
                    result = -1;
                }
                controller.registerAnswer(quizz.getIdPlayer(),quizz.getIdQuizz(),questionF.getIdQuestion(),result);
                controller.updateLastUseQuestion(questionF.getIdQuestion());
            }
        }

        if(countLifes>=0) {
            nextQuestion();
        }else{
            controller.registerResultQuizz(quizz.getIdPlayer(),quizz.getIdQuizz(),-1);
           gameOver();
        }
    }

    private void checkAnswerTimeOut(){
        Fragment fragment = null;
        if(indexFragments < fragments.size()) {
            fragment = getSupportFragmentManager().findFragmentByTag(fragments.get(indexFragments).getUuid());
        }
        if( fragment != null && fragment instanceof QuestionFragment) {
            QuestionFragment questionF = (QuestionFragment) fragment;
            controller.registerAnswer(quizz.getIdPlayer(), quizz.getIdQuizz(), questionF.getIdQuestion(), -1);
        }
        countLifes--;
        if(tviewLife != null) tviewLife.setText(Util.convertTo00(Math.max(countLifes,0)));
        notificationSound("wrong.mp3");
        if(countLifes>=0){
            nextQuestion();
        }else{
            controller.registerResultQuizz(quizz.getIdPlayer(),quizz.getIdQuizz(),-1);
            gameOver();
        }
    }

    private void nextQuestion(){
        indexFragments++;
        if(indexFragments < fragments.size()){
            loadNextQuestion();
        }else{
            controller.registerResultQuizz(quizz.getIdPlayer(),quizz.getIdQuizz(),1);
            winGame();
        }
    }

    private void loadNextQuestion(){
//        if(quizz != null) timeToPass=quizz.getDurationQuestion();
//        if(progressBarTime!=null && quizz!=null){
//            progressBarTime.setMax(quizz.getDurationQuestion());
//            progressBarTime.setProgress(quizz.getDurationQuestion(),false);
//        }
//        if(tviewTime!=null && quizz!=null) tviewTime.setText(Util.convertToMMSS(quizz.getDurationQuestion()));
//        if(tviewCurrentQuestion != null) tviewCurrentQuestion.setText(String.valueOf(indexFragments+1));

        if(indexFragments < fragments.size()){
            if(progressBarTime!=null && quizz!=null){
                timeToPass=fragments.get(indexFragments).getTimeQuestion();
                progressBarTime.setMax(timeToPass);
                progressBarTime.setProgress(timeToPass,false);
                if(tviewTime!=null) tviewTime.setText(Util.convertToMMSS(timeToPass));

            }
            if(tviewCurrentQuestion != null) tviewCurrentQuestion.setText(String.valueOf(indexFragments+1));

            fragmentManager.beginTransaction().
                    replace(R.id.container_question,fragments.get(indexFragments),fragments.get(indexFragments).getUuid()).
                    addToBackStack(null).
                    commit();
        }
        if(handlerTime == null) handlerTime =new Handler();
        handlerTime.postDelayed(updateTimeView,1000);
    }

    private void initQuizz(){
        if(buttonCheck!=null) buttonCheck.setVisibility(View.INVISIBLE);
        if(progressBarTime!=null) progressBarTime.setVisibility(View.INVISIBLE);
        if(tviewLife!=null) tviewLife.setVisibility(View.INVISIBLE);
        if(tviewTime!=null) tviewTime.setVisibility(View.INVISIBLE);
        if(tviewCurrentQuestion!=null) tviewCurrentQuestion.setVisibility(View.INVISIBLE);
        if(tviewNumberQuestions!=null) tviewNumberQuestions.setVisibility(View.INVISIBLE);
        if(tviewText1!=null) tviewText1.setVisibility(View.INVISIBLE);
        if(tviewText2!=null) tviewText2.setVisibility(View.INVISIBLE);
        if(imageViewHeart!=null) imageViewHeart.setVisibility(View.INVISIBLE);
        if(fragmentManager!=null){
            fragmentManager.beginTransaction().
                    replace(R.id.container_question,fragmentInformation,fragmentInformation.toString()).
                    addToBackStack(null).
                    commit();
        }
    }

    private void startQuizz(){
        if(quizz != null && controller !=null){
            controller.addQuizz(quizz);
            if(buttonCheck!=null) buttonCheck.setVisibility(View.VISIBLE);
            if(progressBarTime!=null) progressBarTime.setVisibility(View.VISIBLE);
            if(tviewLife!=null) tviewLife.setVisibility(View.VISIBLE);
            if(tviewTime!=null) tviewTime.setVisibility(View.VISIBLE);
            if(tviewCurrentQuestion!=null) tviewCurrentQuestion.setVisibility(View.VISIBLE);
            if(tviewNumberQuestions!=null) tviewNumberQuestions.setVisibility(View.VISIBLE);
            if(tviewText1!=null) tviewText1.setVisibility(View.VISIBLE);
            if(tviewText2!=null) tviewText2.setVisibility(View.VISIBLE);
            if(imageViewHeart!=null) imageViewHeart.setVisibility(View.VISIBLE);
            indexFragments =0;
            loadNextQuestion();
        }
    }

    private void prepareUI(){
        fragmentManager = getSupportFragmentManager();
        buttonCheck = (Button) findViewById(R.id.button_check);
        tviewNumberQuestions = (TextView) findViewById(R.id.text_view_number_questions);
        tviewCurrentQuestion = (TextView) findViewById(R.id.text_view_current_question);
        tviewTime = (TextView) findViewById(R.id.text_view_time);
        tviewLife = (TextView) findViewById(R.id.text_view_number_life);
        tviewText1 = (TextView) findViewById(R.id.text_view_text_1);
        tviewText2 = (TextView) findViewById(R.id.text_view_text_2);
        progressBarTime = (ProgressBar) findViewById(R.id.progress_bar_time);
        imageViewHeart = (ImageView) findViewById(R.id.image_view_icon_heart);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnErrorListener(this);
        if(buttonCheck != null) buttonCheck.setOnClickListener(this);
        if(tviewNumberQuestions != null) tviewNumberQuestions.setText(String.valueOf(fragments.size()));
        if(tviewCurrentQuestion != null) tviewCurrentQuestion.setText(String.valueOf(0));
        if(progressBarTime != null){
            if(quizz != null) {
                progressBarTime.setMax(quizz.getDurationQuestion());
                progressBarTime.setProgress(quizz.getDurationQuestion(),true);
            }
        }
        if(tviewLife != null){
            if(quizz != null){
                tviewLife.setText(Util.convertTo00(quizz.getLifes()));
                countLifes = quizz.getLifes();
            }
        }
        if(tviewTime != null){
            if(quizz != null) {
                tviewTime.setText(Util.convertToMMSS(quizz.getDurationQuestion()));
            }
        }

        if(quizz != null){
            fragmentInformation = new QuizzInformationFragment(quizz.information());
            fragmentInformation.attachOCBSQ(this);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quizz);
        if (getSupportActionBar() != null) getSupportActionBar().hide();

        Intent intent = getIntent();
        int levelInt = intent.getIntExtra(Constant.LEVEL_GAME,-1);
        GameLevel level = GameLevel.values()[levelInt];

        controller = PsiGameController.getInstance(QuizzActivity.this);

        if(controller!= null) quizz = controller.generateQuizz(level);

        if(quizz != null){
            fragments = FactoryViewFragmentQuestion.getFragments(quizz.getQuestions());
            indexFragments =0 ;
        }

        prepareUI();
        initQuizz();

    }

    @Override
    public void onBackPressed() {
        if(handlerTime != null && updateTimeView!=null)  handlerTime.removeCallbacks(updateTimeView);
        Intent intent = new Intent(QuizzActivity.this, MainActivity.class);
        Bundle b = new Bundle();
        controller.registerResultQuizz(quizz.getIdPlayer(),quizz.getIdQuizz(),-1);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View _view) {
        if(_view!=null){
            int ID = _view.getId();
            switch (ID){
                case R.id.button_check:
                    checkAnswer();
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
       if(mediaPlayer!=null){
           mediaPlayer.stop();
           mediaPlayer.reset();
       }
       super.onDestroy();
    }

    @Override
    public void onPrepared(MediaPlayer arg0) {
        // TODO Auto-generated method stub
        mediaPlayer.start();
    }

    @Override
    public boolean onError(MediaPlayer arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void clickedButtonStartQuizz() {
        this.startQuizz();
    }

    @Override
    public void clickedButtonResultQuizz() {
        if(handlerTime != null && updateTimeView!=null)  handlerTime.removeCallbacks(updateTimeView);
        Intent intent = new Intent(QuizzActivity.this, MainActivity.class);
        Bundle b = new Bundle();
        startActivity(intent);
        finish();
    }
}
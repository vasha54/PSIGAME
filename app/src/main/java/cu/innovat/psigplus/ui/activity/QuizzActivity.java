package cu.innovat.psigplus.ui.activity;

import java.io.File;
import java.io.IOException;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
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
import cu.innovat.psigplus.cim.questions.Question;
import cu.innovat.psigplus.controller.PsiGameController;
import cu.innovat.psigplus.ui.fragment.HomeFragment;
import cu.innovat.psigplus.ui.fragment.question.FactoryViewFragmentQuestion;
import cu.innovat.psigplus.ui.fragment.question.QuestionFragment;
import cu.innovat.psigplus.util.Util;

import java.util.ArrayList;
import java.util.List;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class QuizzActivity extends AppCompatActivity implements View.OnClickListener , OnPreparedListener,
        OnErrorListener{

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
    private ProgressBar progressBarTime;
    private int timeToPass;
    private int countLifes;
    private Handler handlerTime = null;
    private MediaPlayer mediaPlayer;

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
    }

    private void winGame(){
        buttonCheck.setEnabled(false);
        handlerTime.removeCallbacks(updateTimeView);
        buttonCheck.setVisibility(View.INVISIBLE);
    }

    public void checkAnswer(){
        if(handlerTime != null && updateTimeView!=null)  handlerTime.removeCallbacks(updateTimeView);
        Fragment fragment = null;
        if(indexFragments < fragments.size()) {
            fragment = getSupportFragmentManager().findFragmentByTag(fragments.get(indexFragments).getUuid());
        }
        if( fragment != null && fragment instanceof QuestionFragment){
            QuestionFragment questionF = (QuestionFragment) fragment;
            if(questionF != null){
                if(questionF.checkQuestion() == 1){
                    notificationSound("correct.mp3");
                }else if(questionF.checkQuestion() == 0){
                    countLifes --;
                    if(tviewLife != null) tviewLife.setText(Util.convertTo00(countLifes));
                    notificationSound("wrong.mp3");
                }
            }
        }

        if(countLifes>0) {
            nextQuestion();
        }else{
            //TODO falta logica de cuando termina el cuestionario por falta de vida
           gameOver();
        }
    }

    public void checkAnswerTimeOut(){
        //TODO Falta logica de cuando se le agoto el tiempo para responder la
        // pregunta registar en BD
        countLifes--;
        if(tviewLife != null) tviewLife.setText(Util.convertTo00(countLifes));
        notificationSound("wrong.mp3");
        if(countLifes>0){
            nextQuestion();
        }else{
            //TODO falta logica de cuando termina el cuestionario por falta de vida
            gameOver();
        }
    }

    public void nextQuestion(){
        indexFragments++;
        if(indexFragments < fragments.size()){
            loadNextQuestion();
        }else{
            winGame();
        }
    }

    public void loadNextQuestion(){
        if(quizz != null) timeToPass=quizz.getDurationQuestion();
        if(progressBarTime!=null && quizz!=null){
            progressBarTime.setMax(quizz.getDurationQuestion());
            progressBarTime.setProgress(quizz.getDurationQuestion(),false);
        }
        if(tviewTime!=null && quizz!=null) tviewTime.setText(Util.convertToMMSS(quizz.getDurationQuestion()));
        if(tviewCurrentQuestion != null) tviewCurrentQuestion.setText(String.valueOf(indexFragments+1));
        if(indexFragments < fragments.size()){
            fragmentManager.beginTransaction().
                    replace(R.id.container_question,fragments.get(indexFragments),fragments.get(indexFragments).getUuid()).
                    addToBackStack(null).
                    commit();
        }
        if(handlerTime == null) handlerTime =new Handler();
        handlerTime.postDelayed(updateTimeView,1000);
    }

    private void prepareUI(){
        fragmentManager = getSupportFragmentManager();
        buttonCheck = (Button) findViewById(R.id.button_check);
        tviewNumberQuestions = (TextView) findViewById(R.id.text_view_number_questions);
        tviewCurrentQuestion = (TextView) findViewById(R.id.text_view_current_question);
        tviewTime = (TextView) findViewById(R.id.text_view_time);
        tviewLife = (TextView) findViewById(R.id.text_view_number_life);
        progressBarTime = (ProgressBar) findViewById(R.id.progress_bar_time);
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
        loadNextQuestion();
    }

    @Override
    public void onBackPressed() {
        if(handlerTime != null && updateTimeView!=null)  handlerTime.removeCallbacks(updateTimeView);
        Intent intent = new Intent(QuizzActivity.this, MainActivity.class);
        Bundle b = new Bundle();
        //TODO falta registrar si inicio el cuestionario como intento
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


    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
   // private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
  //  private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
//    private static final int UI_ANIMATION_DELAY = 300;
//    private final Handler mHideHandler = new Handler();
//    private View mContentView;
//    private final Runnable mHidePart2Runnable = new Runnable() {
//        @SuppressLint("InlinedApi")
//        @Override
//        public void run() {
//            // Delayed removal of status and navigation bar
//            if (Build.VERSION.SDK_INT >= 30) {
//                mContentView.getWindowInsetsController().hide(
//                        WindowInsets.Type.statusBars() | WindowInsets.Type.navigationBars());
//            } else {
//                // Note that some of these constants are new as of API 16 (Jelly Bean)
//                // and API 19 (KitKat). It is safe to use them, as they are inlined
//                // at compile-time and do nothing on earlier devices.
//                mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
//                        | View.SYSTEM_UI_FLAG_FULLSCREEN
//                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
//            }
//        }
//    };
//    private View mControlsView;
//    private final Runnable mShowPart2Runnable = new Runnable() {
//        @Override
//        public void run() {
//            // Delayed display of UI elements
//            ActionBar actionBar = getSupportActionBar();
//            if (actionBar != null) {
//                actionBar.show();
//            }
//            mControlsView.setVisibility(View.VISIBLE);
//        }
//    };
//    private boolean mVisible;
//    private final Runnable mHideRunnable = new Runnable() {
//        @Override
//        public void run() {
//            hide();
//        }
//    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
//    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
//        @Override
//        public boolean onTouch(View view, MotionEvent motionEvent) {
//            switch (motionEvent.getAction()) {
//                case MotionEvent.ACTION_DOWN:
//                    if (AUTO_HIDE) {
//                        delayedHide(AUTO_HIDE_DELAY_MILLIS);
//                    }
//                    break;
//                case MotionEvent.ACTION_UP:
//                    view.performClick();
//                    break;
//                default:
//                    break;
//            }
//            return false;
//        }
//    };
//    private ActivityQuizzBinding binding;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);

//        binding = ActivityQuizzBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        mVisible = true;
//        mControlsView = binding.fullscreenContentControls;
//        mContentView = binding.fullscreenContent;

        // Set up the user interaction to manually show or hide the system UI.
//        mContentView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                toggle();
//            }
//        });
//
//        // Upon interacting with UI controls, delay any scheduled hide()
//        // operations to prevent the jarring behavior of controls going away
//        // while interacting with the UI.
//        binding.dummyButton.setOnTouchListener(mDelayHideTouchListener);
//    }

//    @Override
//    protected void onPostCreate(Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//
//        // Trigger the initial hide() shortly after the activity has been
//        // created, to briefly hint to the user that UI controls
//        // are available.
//        //delayedHide(100);
//    }

//    private void toggle() {
//        if (mVisible) {
//            hide();
//        } else {
//            show();
//        }
//    }

//    private void hide() {
//        // Hide UI first
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.hide();
//        }
//        mControlsView.setVisibility(View.GONE);
//        mVisible = false;
//
//        // Schedule a runnable to remove the status and navigation bar after a delay
//        mHideHandler.removeCallbacks(mShowPart2Runnable);
//        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
//    }
//
//    private void show() {
//        // Show the system bar
//        if (Build.VERSION.SDK_INT >= 30) {
//            mContentView.getWindowInsetsController().show(
//                    WindowInsets.Type.statusBars() | WindowInsets.Type.navigationBars());
//        } else {
//            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
//        }
//        mVisible = true;
//
//        // Schedule a runnable to display UI elements after a delay
//        mHideHandler.removeCallbacks(mHidePart2Runnable);
//        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
//    }
//
//    /**
//     * Schedules a call to hide() in delay milliseconds, canceling any
//     * previously scheduled calls.
//     */
//    private void delayedHide(int delayMillis) {
//        mHideHandler.removeCallbacks(mHideRunnable);
//        mHideHandler.postDelayed(mHideRunnable, delayMillis);
//    }
}
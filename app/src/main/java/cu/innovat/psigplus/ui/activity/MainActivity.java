package cu.innovat.psigplus.ui.activity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.graphics.*;
import android.graphics.pdf.PdfDocument;
import android.provider.Settings;
import android.telephony.SignalStrength;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.os.Environment;
import android.view.*;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.Fragment;
import androidx.core.app.ActivityCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener;

import android.widget.Toast;
import android.content.Intent;
import android.os.Process;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.content.pm.PackageManager;
import android.Manifest;
import android.content.Context;
import android.os.Build;

import cu.innovat.psigplus.R;
import cu.innovat.psigplus.cim.*;
import cu.innovat.psigplus.controller.PsiGameController;
import cu.innovat.psigplus.interfaces.IObserverClickButtonGameLevel;
import cu.innovat.psigplus.interfaces.IObserverClickButtonGenerateCertificate;
import cu.innovat.psigplus.ui.fragment.*;
import cu.innovat.psigplus.util.LOG;
import cu.innovat.psigplus.util.Util;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_PHONE_NUMBERS;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.READ_SMS;

public class MainActivity extends AppCompatActivity implements OnItemSelectedListener, IObserverClickButtonGameLevel,
        IObserverClickButtonGenerateCertificate {

    private BottomNavigationView bottomNavigationView;
    private Toast m_toast;
    private boolean m_exit;

    private String m_emiePhone;
    private String m_numberPhone;

    private PsiGameController controller;

    public MainActivity(){
        super();
        m_exit=false;
        m_toast=null;
        m_numberPhone = "";
        m_emiePhone = "";
        controller = null;
    }



    private void determinePhoneNumbre() { this.m_numberPhone="";}

    private void determineEMIEPhone(){
        this.m_emiePhone = "";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        createDirectoryApp();
        determineEMIEPhone();
        determinePhoneNumbre();
        controller = PsiGameController.getInstance(MainActivity.this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);

        if(bottomNavigationView!=null){
            bottomNavigationView.setOnItemSelectedListener(this);
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = new HomeFragment();
        fragmentManager.beginTransaction().
                replace(R.id.container,fragment).addToBackStack(fragment.toString()).
                commit();
    }

    public boolean onNavigationItemSelected(MenuItem item){
        if(bottomNavigationView!=null){
            final int previousItem = bottomNavigationView.getSelectedItemId();
            final int nextItem = item.getItemId();
            if (previousItem != nextItem) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                BaseFragment fragment=factoryMethodView(nextItem);

                fragmentManager.beginTransaction().
                            replace(R.id.container,fragment).addToBackStack(fragment.toString()).
                            commit();
            }
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if(m_exit==false) {
            m_exit=true;
            m_toast=Toast.makeText(this.getApplicationContext(),
                    getString(R.string.exit),
                    Toast.LENGTH_LONG);
            m_toast.show();
        }else {
            if(m_toast!=null)
                m_toast.cancel();
            finish();
            //Process.killProcess(Process.myPid());
        }
    }


    @Override
    public void clickedButtonGameLevel(GameLevel level) {
        this.initQuizz(level);
    }

    public void initQuizz(GameLevel level){
        if(controller.canCreateQuizzThisLevel(level)){
            Intent intent = new Intent(MainActivity.this, QuizzActivity.class);
            intent.putExtra(Constant.LEVEL_GAME,level.ordinal());
            startActivity(intent);
            finish();
        }else{
            m_toast=Toast.makeText(this.getApplicationContext(),
                    getString(R.string.not_exist_questions),
                    Toast.LENGTH_LONG);
            m_toast.show();
        }
    }

    public BaseFragment factoryMethodView(int nextItem){
        switch (nextItem) {
            case R.id.navigation_about:
                AboutFragment fAboutFragment = new AboutFragment();
                return fAboutFragment;
            case R.id.navigation_certificate:
                String IMEI =  getIMEI();
                String numberPhone = getNumberPhone();
                CertificateFragment fCertificateFragment = new CertificateFragment(IMEI,numberPhone);
                fCertificateFragment.attach(this);
                return fCertificateFragment;
            case R.id.navigation_configuration:
                ConfigurationFragment fConfigurationFragment = new ConfigurationFragment();
                return fConfigurationFragment;
            case R.id.navigation_home:
                HomeFragment fHomeFragment = new HomeFragment();
                return fHomeFragment;
            case R.id.navigation_statistics:
                StatisticsFragment fStatisticsFragment = new StatisticsFragment();
                return fStatisticsFragment;
            default: return null;
        }
    }

    public String getIMEI(){
        return this.m_emiePhone;
    }

    public String getNumberPhone() {
        return this.m_numberPhone;
    }

    private void createDirectoryApp() {
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                + File.separator + getString(R.string.app_folder);
        File dir = new File(path);
        if (!dir.exists()) dir.mkdirs();
        String pathDoc=path+File.separator+getString(R.string.certificate);
        File dirDocs=new File(pathDoc);
        if (!dirDocs.exists()) dirDocs.mkdirs();

        path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
                + File.separator + getString(R.string.app_folder);
        dir = new File(path);
        if (!dir.exists()) dir.mkdirs();
        pathDoc=path+File.separator+getString(R.string.certificate);
        dirDocs=new File(pathDoc);
        if (!dirDocs.exists()) dirDocs.mkdirs();
    }

    @Override
    public void clickedButtonGenerateCertificate(List<Format> formats) {
        if(controller==null) controller = PsiGameController.getInstance(MainActivity.this);
        Player player = controller.getCurrentPlayer();
        if(player != null){

            List<Statistics> levels = new ArrayList<Statistics>();
            levels.add(new Statistics(GameMode.MEDICAL,GameLevel.ROOKIE_GENERAL,getString(R.string.title_level_rookie)));
            levels.add(new Statistics(GameMode.MEDICAL,GameLevel.COMPETENT_GENERAL,getString(R.string.title_level_competent)));
            levels.add(new Statistics(GameMode.MEDICAL,GameLevel.PROFESSIONAL_GENERAL,getString(R.string.title_level_professional)));
            levels.add(new Statistics(GameMode.GENERAL,GameLevel.ROOKIE_MEDICAL,getString(R.string.title_level_rookie)));
            levels.add(new Statistics(GameMode.GENERAL,GameLevel.COMPETENT_MEDICAL,getString(R.string.title_level_competent)));
            levels.add(new Statistics(GameMode.GENERAL,GameLevel.PROFESSIONAL_MEDICAL,getString(R.string.title_level_professional)));

            controller.updateStatistics(levels);

            for(Statistics sta : levels){
                if(sta.getTimeStampFWGame() != Long.MIN_VALUE){
                    for(Format format : formats){
                        switch (format){
                            case PDF: certificatePDF(player,sta); break;
                            case PNG: certificatePNG(player,sta); break;
                            case JPG: certificateJPG(player,sta); break;
                        }
                    }
                }
            }

        }else{
            m_toast=Toast.makeText(this.getApplicationContext(),
                    getString(R.string.not_player_active_for_certificate),
                    Toast.LENGTH_LONG);
            m_toast.show();
        }
    }

    private String getTitleCertificate(Statistics stat){
        String title = null;
        if(stat.getLevelGame() == GameLevel.ROOKIE_GENERAL)
            title = getString(R.string.title_level_rookie_mode_general);
        else if(stat.getLevelGame() == GameLevel.COMPETENT_GENERAL)
            title = getString(R.string.title_level_competent_mode_general);
        else if(stat.getLevelGame() == GameLevel.PROFESSIONAL_GENERAL)
            title = getString(R.string.title_level_professional_mode_general);
        else if(stat.getLevelGame() == GameLevel.ROOKIE_MEDICAL)
            title = getString(R.string.title_level_rookie_mode_medica);
        else if(stat.getLevelGame() == GameLevel.COMPETENT_MEDICAL)
            title = getString(R.string.title_level_competent_mode_medica);
        else if(stat.getLevelGame() == GameLevel.PROFESSIONAL_MEDICAL)
            title = getString(R.string.title_level_professional_mode_medica);
        return title;
    }

    public void certificatePDF(Player player, Statistics stat){
        int currentApiVersion = Build.VERSION.SDK_INT;

        if(currentApiVersion >= Build.VERSION_CODES.LOLLIPOP){
            Bitmap bitmapOriginal = BitmapFactory.decodeResource(getResources(),R.mipmap.template_certificate);
            Bitmap bitmapCopy = bitmapOriginal.copy(Bitmap.Config.ARGB_8888,true);
            Canvas canvas = new Canvas(bitmapCopy);

            putTextInImage(player,stat,canvas,bitmapCopy);

            PdfDocument document = new PdfDocument();

            PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(
                    bitmapCopy.getWidth(), bitmapCopy.getHeight(),1).create();
            PdfDocument.Page page = document.startPage(pageInfo);

            Canvas canvasPDF = page.getCanvas();
            canvasPDF.drawBitmap(bitmapCopy,0,0,null);

            document.finishPage(page);

            String pathDirectory=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) +
                    File.separator + getString(R.string.app_folder)+ File.separator+getString(R.string.certificate);
            File filePDF = new File(pathDirectory+File.separator+player.getIdUser()+"_"+
                        stat.getLevelGame().toString()+".pdf");
            try{
                document.writeTo(new FileOutputStream(filePDF));
                Toast.makeText(getApplicationContext(),
                        getString(R.string.locationdownloadDoc)+
                                pathDirectory+File.separator+ player.getIdUser()+"_"+
                                stat.getLevelGame().toString()+".pdf",
                        Toast.LENGTH_SHORT).show();
            }catch (IOException e){
                e.printStackTrace();
                LOG.e("TAG_DB_PSIGAME_PLUS",e.getMessage());
            }
            document.close();
        }else{
            Toast.makeText(getApplicationContext(),
                    getString(R.string.version_android_not_support_certificate_pdf),
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void certificatePNG(Player player, Statistics stat){
        Bitmap bitmapOriginal = BitmapFactory.decodeResource(getResources(),R.mipmap.template_certificate);
        Bitmap bitmapCopy = bitmapOriginal.copy(Bitmap.Config.ARGB_8888,true);
        Canvas canvas = new Canvas(bitmapCopy);

        putTextInImage(player,stat,canvas,bitmapCopy);

        FileOutputStream fileCertificate = null;
        String pathDirectory=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + File.separator +
                getString(R.string.app_folder)+ File.separator+getString(R.string.certificate);
        try{
            fileCertificate = new FileOutputStream(pathDirectory+File.separator+player.getIdUser()+"_"+
                    stat.getLevelGame().toString()+".png");
            bitmapCopy.compress(Bitmap.CompressFormat.PNG,100,fileCertificate);
            Toast.makeText(getApplicationContext(),
                    getString(R.string.locationdownloadDoc)+
                            pathDirectory+File.separator+ player.getIdUser()+"_"+
                            stat.getLevelGame().toString()+".png",
                    Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
            LOG.e("TAG_DB_PSIGAME_PLUS",e.getMessage());
        }finally {
            try{
                if(fileCertificate != null) fileCertificate.close();
            }catch (IOException f){
                f.printStackTrace();
                LOG.e("TAG_DB_PSIGAME_PLUS",f.getMessage());
            }
        }
    }

    public void certificateJPG(Player player, Statistics sta){
        Bitmap bitmapOriginal = BitmapFactory.decodeResource(getResources(),R.mipmap.template_certificate);
        Bitmap bitmapCopy = bitmapOriginal.copy(Bitmap.Config.ARGB_8888,true);
        Canvas canvas = new Canvas(bitmapCopy);

        putTextInImage(player,sta,canvas,bitmapCopy);

        FileOutputStream fileCertificate = null;
        String pathDirectory=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + File.separator +
                getString(R.string.app_folder)+ File.separator+getString(R.string.certificate);
        try{
            fileCertificate = new FileOutputStream(pathDirectory+File.separator+player.getIdUser()+"_"+
                    sta.getLevelGame().toString()+".jpg");
            bitmapCopy.compress(Bitmap.CompressFormat.JPEG,100,fileCertificate);
            Toast.makeText(getApplicationContext(),
                    getString(R.string.locationdownloadDoc)+
                            pathDirectory+File.separator+ player.getIdUser()+"_"+
                            sta.getLevelGame().toString()+".jpg",
                    Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
            LOG.e("TAG_DB_PSIGAME_PLUS",e.getMessage());
        }finally {
            try{
                if(fileCertificate != null) fileCertificate.close();
            }catch (IOException f){
                f.printStackTrace();
                LOG.e("TAG_DB_PSIGAME_PLUS",f.getMessage());
            }
        }
    }

    public void putTextInImage(Player player,Statistics stat, Canvas canvas, Bitmap bitmap){
        Typeface typeface = ResourcesCompat.getFont(MainActivity.this,R.font.z003_medium_italic);

        Paint paintText = new Paint();
        paintText.setTypeface(typeface);
        paintText.setColor(Color.BLACK);

        //Texto de la fecha
        paintText.setTextSize(12);
        String dateExpired = Util.formatTimeStamp(stat.getTimeStampFWGame());
        canvas.drawText(dateExpired,79,675,paintText);

        //Texto del nivel alcanzado
        paintText.setTextSize(30);
        String titleCertificate = getTitleCertificate(stat);
        int widthText =(int) paintText.measureText(titleCertificate);
        canvas.drawText(titleCertificate,Util.WIDTH_TEMPLATE_CERTIFICATE/2-widthText/2,500,paintText);

        //Texto del nombre
        int fontSize = Integer.MAX_VALUE;
        String namePlayer = player.getName()+" "+player.getSurname();

        fontSize = Util.searchSizeOptimus(paintText,namePlayer,Util.WIDTH_TEMPLATE_CERTIFICATE-30,70);
        paintText.setTextSize(fontSize);
        Rect bounds =new Rect();
        paintText.getTextBounds(namePlayer,0,namePlayer.length(),bounds);
        int h = bounds.height();
        widthText =(int) paintText.measureText(namePlayer);
        canvas.drawText(namePlayer,Util.WIDTH_TEMPLATE_CERTIFICATE/2-widthText/2,420-h/2,paintText);

    }

}

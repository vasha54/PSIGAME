package cu.innovat.psigplus.ui.activity;

import android.provider.Settings;
import android.telephony.SignalStrength;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.Fragment;
import androidx.core.app.ActivityCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener;

import android.util.Log;
import android.widget.Toast;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.Window;
import android.content.Intent;
import android.os.Process;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.content.pm.PackageManager;
import android.Manifest;
import android.content.Context;
import android.os.Build;

import cu.innovat.psigplus.R;
import cu.innovat.psigplus.cim.Constant;
import cu.innovat.psigplus.cim.GameLevel;
import cu.innovat.psigplus.controller.PsiGameController;
import cu.innovat.psigplus.interfaces.IObserverClickButtonGameLevel;
import cu.innovat.psigplus.ui.fragment.*;

import java.util.List;

import static android.Manifest.permission.READ_PHONE_NUMBERS;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.READ_SMS;

public class MainActivity extends AppCompatActivity implements OnItemSelectedListener, IObserverClickButtonGameLevel {

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
}

/*
ublic String getDeviceIMEI() {
    String deviceUniqueIdentifier = null;
    TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
    if (null != tm) {
        deviceUniqueIdentifier = tm.getDeviceId();
    }
    if (null == deviceUniqueIdentifier || 0 == deviceUniqueIdentifier.length()) {
        deviceUniqueIdentifier = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
    }
    return deviceUniqueIdentifier;
}
 */
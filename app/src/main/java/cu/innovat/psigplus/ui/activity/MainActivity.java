package cu.innovat.psigplus.ui.activity;

import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener;

import android.os.Bundle;
import android.widget.Toast;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.Window;
import android.content.Intent;
import android.os.Process;

import cu.innovat.psigplus.R;
import cu.innovat.psigplus.cim.Constant;
import cu.innovat.psigplus.cim.GameLevel;
import cu.innovat.psigplus.interfaces.IObserverClickButtonGameLevel;
import cu.innovat.psigplus.ui.fragment.*;

public class MainActivity extends AppCompatActivity implements OnItemSelectedListener, IObserverClickButtonGameLevel {
    private BottomNavigationView bottomNavigationView;
    private Toast m_toast;
    private boolean m_exit;

    public MainActivity(){
        super();
        m_exit=false;
        m_toast=null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

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
                Fragment fragment=null;
                switch (nextItem) {
                    case R.id.navigation_about:
                        fragment = new AboutFragment();
                        break;
                    case R.id.navigation_certificate:
                        fragment = new CertificateFragment();
                        break;
                    case R.id.navigation_configuration:
                        fragment = new ConfigurationFragment();
                        break;
                    case R.id.navigation_home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.navigation_statistics:
                        fragment = new StatisticsFragment();
                        break;
                }

                if(fragment!=null){
                    if(fragment instanceof HomeFragment){
                        ((HomeFragment)fragment).attach(this);
                    }

                    fragmentManager.beginTransaction().
                            replace(R.id.container,fragment).addToBackStack(fragment.toString()).
                            commit();
                }
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
            Process.killProcess(Process.myPid());
        }
    }


    @Override
    public void clickedButtonGameLevel(GameLevel level) {
        this.initQuizz(level);
    }

    public void initQuizz(GameLevel level){
        Intent intent = new Intent(MainActivity.this, QuizzActivity.class);
        intent.putExtra(Constant.LEVEL_GAME,level.ordinal());
        startActivity(intent);
        finish();
    }
}
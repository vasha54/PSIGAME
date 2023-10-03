package cu.innovat.psigameplus.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener;
import android.os.Bundle;
import android.widget.Toast;
import android.view.MenuItem;

import cu.innovat.psigameplus.R;
import cu.innovat.psigameplus.ui.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity implements OnItemSelectedListener {
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
        setContentView(R.layout.activity_main);
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
                        m_toast=Toast.makeText(this.getApplicationContext(),
                                "About",
                                Toast.LENGTH_SHORT);
                        m_toast.show();
                        break;
                    case R.id.navigation_certificate:
                        m_toast=Toast.makeText(this.getApplicationContext(),
                                "Certificado",
                                Toast.LENGTH_SHORT);
                        m_toast.show();
                        break;
                    case R.id.navigation_configuration:
                        m_toast=Toast.makeText(this.getApplicationContext(),
                                "Configuracion",
                                Toast.LENGTH_SHORT);
                        m_toast.show();
                        break;
                    case R.id.navigation_home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.navigation_statistics:
                        m_toast=Toast.makeText(this.getApplicationContext(),
                                "Estadisticas",
                                Toast.LENGTH_SHORT);
                        m_toast.show();
                        break;
                }
                if(fragment!=null){
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
        }
    }
}
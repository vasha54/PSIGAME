package cu.innovat.psigameplus.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener;
import android.os.Bundle;
import android.widget.Toast;
import android.view.MenuItem;
import cu.innovat.psigameplus.R;

public class MainActivity extends AppCompatActivity implements OnItemSelectedListener {
    private BottomNavigationView bottomNavigationView;
    private Toast m_toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);

        if(bottomNavigationView!=null){
            bottomNavigationView.setOnItemSelectedListener(this);
        }

    }

    public boolean onNavigationItemSelected(MenuItem item){
        if(bottomNavigationView!=null){
            final int previousItem = bottomNavigationView.getSelectedItemId();
            final int nextItem = item.getItemId();
            if (previousItem != nextItem) {
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
                        m_toast=Toast.makeText(this.getApplicationContext(),
                                "Home",
                                Toast.LENGTH_SHORT);
                        m_toast.show();
                        break;
                    case R.id.navigation_statistics:
                        m_toast=Toast.makeText(this.getApplicationContext(),
                                "Estadisticas",
                                Toast.LENGTH_SHORT);
                        m_toast.show();
                        break;
                }
            }
        }
        return true;
    }
}
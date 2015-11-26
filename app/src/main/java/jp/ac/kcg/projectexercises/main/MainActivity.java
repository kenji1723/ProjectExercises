package jp.ac.kcg.projectexercises.main;

import android.os.Bundle;

import jp.ac.kcg.projectexercises.R;
import jp.ac.kcg.projectexercises.activites.ApplicationActivity;

public class MainActivity extends ApplicationActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}

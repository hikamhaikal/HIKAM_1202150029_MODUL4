package id.simplify.hikam_1202150029_modul4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void golist(View view) {
        Intent a = new Intent(this,ListMahasiswa.class);
        startActivity(a);
    }

    public void gosearch(View view) {
        Intent b = new Intent(this,CariGambar.class);
        startActivity(b);
    }
}

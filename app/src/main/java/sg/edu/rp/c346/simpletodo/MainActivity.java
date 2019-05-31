package sg.edu.rp.c346.simpletodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Spinner spn;
    EditText et;
    Button b1;
    Button b2;
    Button b3;
    ArrayList <String> toDo;
    ListView lv;
    ArrayAdapter aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spn = findViewById(R.id.spinner);
        et = findViewById(R.id.editText);
        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        toDo = new ArrayList<String>();

        lv = findViewById(R.id.listView);
        aa= new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,toDo);
        lv.setAdapter(aa);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDo.add(et.getText().toString());
                aa.notifyDataSetChanged();

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toDo.isEmpty()) {
                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_LONG).show();
                    return;
                }
                int index = Integer.parseInt(et.getText().toString());
                if (index >= 0 && index < toDo.size()) {
                    toDo.remove(index);
                    aa.notifyDataSetChanged();
                    return;
                }else{
                    Toast.makeText(MainActivity.this, "Wrong Index Number", Toast.LENGTH_LONG).show();
                    return;
                }


            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDo.clear();
                aa.notifyDataSetChanged();
            }
        });

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        b1.setEnabled(true);
                        b2.setEnabled(false);
                        et.setHint("Type in a new Task Here");
                        et.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case 1:
                        b1.setEnabled(false);
                        b2.setEnabled(true);
                        et.setHint("Type in the index of the task to be removed");
                        et.setInputType(InputType.TYPE_CLASS_NUMBER);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}

package com.example.bigo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String dataStruct;
    private final ArrayList<String> operations = new ArrayList<>();
    private int complexityCase = R.id.rButton_average;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setSupportActionBar((androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar));
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        ((Spinner)findViewById(R.id.spinner_data)).setOnItemSelectedListener(this);
    }

    // spinner
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        dataStruct = adapterView.getItemAtPosition(i).toString();
        // getItemSelectedPosition, getSelectedItem
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}

    // operations
    public void getOperations(View view) {
        CheckBox cView = (CheckBox) view;
        if(cView.isChecked()) operations.add(cView.getText().toString());
        if(!cView.isChecked()) operations.remove(cView.getText().toString());
    }

    // case
    public void getCase(View view) {
        complexityCase = view.getId();
    }

    // send mail
    public void sendMail(View view) {
        String emailAddress = "To: " + ((TextView)findViewById(R.id.eText_emailAddress)).getText().toString();
        String subject = "Subject: " + ((TextView)findViewById(R.id.eText_emailSubject)).getText().toString();

        ((TextView)findViewById(R.id.tView_to)).setText(emailAddress);
        ((TextView)findViewById(R.id.tView_from)).setText(subject + "\n" + getComplexity());
    }
    public String getComplexity() {
        StringBuilder sb = new StringBuilder();
        if(dataStruct.contains("Binary")) {
            if(complexityCase == R.id.rButton_average) {
                sb.append("Binary Search Tree average case time complexity:\n");
                operations.forEach((n) -> sb.append(n).append(": O(log(n))\n"));
            } else if (complexityCase == R.id.rButton_worst) {
                sb.append("Binary Search Tree worst case time complexity:\n");
                operations.forEach((n) -> sb.append(n).append(": O(n)\n"));
            }
        } else if(dataStruct.contains("2")) {
            if (complexityCase == R.id.rButton_average)
                sb.append("2-3 Tree average case time complexity:\n");
            else if (complexityCase == R.id.rButton_worst)
                sb.append("2-3 Tree worst case time complexity:\n");
            operations.forEach((n) -> sb.append(n).append(": O(log(n))\n"));
        } else if(dataStruct.contains("Hash")) {
            if(complexityCase == R.id.rButton_average) {
                sb.append("Hash Table average case time complexity:\n");
                operations.forEach((n) -> sb.append(n).append(": O(1)\n"));
            } else if (complexityCase == R.id.rButton_worst) {
                sb.append("Hash Table worst case time complexity:\n");
                operations.forEach((n) -> sb.append(n).append(": O(n)\n"));
            }
        } else if(dataStruct.contains("Linked")) {
            if(complexityCase == R.id.rButton_average)
                sb.append("Linked List average case time complexity:\n");
            else if (complexityCase == R.id.rButton_worst)
                sb.append("Linked List worst case time complexity:\n");
            if(operations.contains("Get Min")) sb.append("Get Min: O(n)\n");
            if(operations.contains("Insert")) sb.append("Insert: O(1)\n");
            if(operations.contains("Search")) sb.append("Search: O(n)\n");
        } else if(dataStruct.contains("Min")) {
            if(complexityCase == R.id.rButton_average)
                sb.append("Min Heap average case time complexity\n");
            else if(complexityCase == R.id.rButton_worst)
                sb.append("Min Heap worst case time complexity:\n");
            if(operations.contains("Get Min") ) sb.append("Get Min: O(1)\n");
            if(operations.contains("Insert")) sb.append("Insert: O(log(n))\n");
            if(operations.contains("Search")) sb.append("Search: O(log(n))\n");
        }
        return String.valueOf(sb);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch(menuItem.getItemId()) {
            case (R.id.menu_compose):
                // fill out the page
                String emailAddress = "To: " + ((TextView)findViewById(R.id.eText_emailAddress)).getText().toString();
                String subject = "Subject: " + ((TextView)findViewById(R.id.eText_emailSubject)).getText().toString();
                ((TextView)findViewById(R.id.tView_to)).setText(emailAddress);
                ((TextView)findViewById(R.id.tView_from)).setText(subject + "\n" + getComplexity());

                // change the icon
                MenuItem mi = menuItem;
                mi.setIcon(R.drawable.ic_action_send);

                return true;
            case (R.id.menu_settings):
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}


// data structure -> worst: getMin(), insert(), search() | average: getMin(), insert(), search()
// binary search tree -> O(log(n)), O(log(n)), O(log(n)) | O(n), O(n), O(n)
// 2-3 tree -> O(log(n)), O(log(n)), O(log(n)) | O(log(n)), O(log(n)), O(log(n))
// Hash Table -> O(1), O(1), O(1) | O(n), O(n), O(n)
// Linked List -> O(n), O(1), O(n) | O(n), O(1), O(n)
// Min Heap -> O(1), O(log(n)), O(log(n)) | O(1), O(log(n)), O(log(n))
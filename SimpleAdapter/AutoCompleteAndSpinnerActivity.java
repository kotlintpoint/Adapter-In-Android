package firebase.sodhankit.com.sampleapplication.auto_complete_custom_adapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import firebase.sodhankit.com.sampleapplication.R;

public class AutoCompleteAndSpinnerActivity extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTextView;
    Spinner spinnerUser;
    List<HashMap<String,String>> aList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete_sample);

        autoCompleteTextView=(AutoCompleteTextView)findViewById(R.id.autocompleteTextView);

        createDummyData();
        // Keys used in Hashmap
        String[] from = { "name","number"};
        // Ids of views in listview_layout
        int[] to = { R.id.tvUsername,R.id.tvNumber};
        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        // Set Adapter for AutocompletTextView
        SimpleAdapter adapterAutoComplete = new SimpleAdapter(getBaseContext(), aList,
                R.layout.user_row_layout, from, to);
        autoCompleteTextView.setAdapter(adapterAutoComplete);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                HashMap<String,String> hm=(HashMap<String,String>) adapterView.getAdapter().getItem(position);
                autoCompleteTextView.setText(hm.get("name"));
            }
        });

        // Set Adapter for Spinner
        spinnerUser=(Spinner)findViewById(R.id.spinnerUser);
        SimpleAdapter adapterSpinner = new SimpleAdapter(getBaseContext(), aList,
                R.layout.user_row_layout, from, to);
        spinnerUser.setAdapter(adapterSpinner);
        spinnerUser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                HashMap<String,String> hm= (HashMap<String, String>) adapterView.getAdapter().getItem(position);
                Toast.makeText(AutoCompleteAndSpinnerActivity.this, "Welcome, "+hm.get("name"), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void createDummyData() {
        aList = new ArrayList<HashMap<String,String>>();
        for (int i=1;i<=5;i++)
        {
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("name", "User "+i);
            hm.put("number", "123456789"+i );
            aList.add(hm);
        }
    }
}

package com.hungrycollegekids.listmenu_activity;

import android.content.DialogInterface;
import android.content.ContentValues;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.view.Menu;
import android.view.MenuItem;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

import com.hungrycollegekids.listmenu_activity.db.ListDbHelper;
import com.hungrycollegekids.listmenu_activity.db.ListMenuContract;
import com.hungrycollegekids.login_activity.R;

import java.util.ArrayList;
import java.util.Collections;

public class ListMenuActivity extends AppCompatActivity {

    private static final String TAG = "ListMenuActivity";
    private ListDbHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listmenu);

        mHelper = new ListDbHelper(this);
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(ListMenuContract.ListMenuEntry.TABLE, new String[]{ListMenuContract.ListMenuEntry._ID, ListMenuContract.ListMenuEntry.COL_LIST_TITLE}, null, null, null, null, null);
        while(cursor.moveToNext()) {
            int idx = cursor.getColumnIndex(ListMenuContract.ListMenuEntry.COL_LIST_TITLE);
            Log.d(TAG, "Task: " + cursor.getString(idx));
        }
        cursor.close();
        db.close();

        //Toolbar
        android.support.v7.widget.Toolbar mToolBar = findViewById(R.id.m_toolbar);
        setSupportActionBar(mToolBar);

        //ListView
        ListView mListMenu = findViewById(R.id.list_view);
        String[] listNames = new String[] {"Groceries", "Snacks", "Cleaning supplies"};

        final ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, listNames);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        mListMenu.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_create_list:
                final EditText listEditText = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Create new grocery list")
                        .setMessage("Choose a name for the list")
                        .setView(listEditText)
                        .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String listName = String.valueOf(listEditText.getText());
                                SQLiteDatabase db = mHelper.getWritableDatabase();
                                ContentValues values = new ContentValues();
                                values.put(ListMenuContract.ListMenuEntry.COL_LIST_TITLE, listName);
                                db.insertWithOnConflict(ListMenuContract.ListMenuEntry.TABLE,
                                        null,
                                        values,
                                        SQLiteDatabase.CONFLICT_REPLACE);
                                db.close();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}


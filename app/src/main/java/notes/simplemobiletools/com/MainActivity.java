package notes.simplemobiletools.com;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences prefs;
    private EditText notesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE);
        final String text = prefs.getString(Constants.TEXT, "");
        notesView = (EditText) findViewById(R.id.notesView);
        notesView.setText(text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                saveText();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveText() {
        final String text = notesView.getText().toString().trim();
        prefs.edit().putString(Constants.TEXT, text).apply();
        Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show();
        hideKeyboard();
    }

    private void hideKeyboard() {
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(notesView.getWindowToken(), 0);
    }
}

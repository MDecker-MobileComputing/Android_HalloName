package de.mide.halloname;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * App demonstriert Verwendung von Dialogen, Toasts und Log-Nachrichten.
 * <br><br>
 *
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
public class MainActivity extends Activity {

    public static final String TAG4LOGGING = "HalloName";

    /** UI-Element, in das der Nutzer einen (Vor-)Namen eingeben soll. */
    protected EditText _nameEditText = null;


    /**
     * Lifecycle-Methode: Lädt UI, lädt Referenz auf UI-Element in
     * Member-Variable und setzt Event-Handler-Methode für den Button.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _nameEditText = findViewById(R.id.nameEditText);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                eventHandlerFuerButton();
            }
        });

        Log.v(TAG4LOGGING, "Methode onCreate() wurde erfolgreich beendet.");
    }


    /**
     * Diese Methode ist eine Event-Handler-Methode; sie wird beim Klick
     * auf den Button aufgerufen.
     */
    protected void eventHandlerFuerButton() {

        if (_nameEditText == null) {

            // mit "wtf" wird je nach Android-Version die App beendet.
            Log.wtf(TAG4LOGGING, "EditText-Element nicht gefüllt.");

            return;
        }


        String name = _nameEditText.getText().toString().trim();

        if (name.length() == 0) {

            Log.w(TAG4LOGGING, "Der Nutzer hat keinen Namen eingegeben!");

            String toastText = getString( R.string.toast_text_name_eingeben );
            Toast toast =
                    Toast.makeText(
                            this,
                            toastText,
                            Toast.LENGTH_LONG);
            toast.show();
            return;
        }

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        dialogBuilder.setMessage("Hallo " + name + "!");
        dialogBuilder.setPositiveButton("Weiter", null);

        AlertDialog dialog = dialogBuilder.create();
        dialog.show();

        keyboardEinklappen(_nameEditText);
    }


    /**
     * Virtuelles Keyboard wieder "einklappen". Lösung nach
     * <a href="https://stackoverflow.com/a/17789187/1364368">https://stackoverflow.com/a/17789187/1364368</a>
     *
     * @param view  UI-Element, von dem Keyboard eingeblendet wurde.
     */
    public void keyboardEinklappen(View view) {

        InputMethodManager imm = (InputMethodManager)
                getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}

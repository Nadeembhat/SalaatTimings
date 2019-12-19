package waqtesalaat.nadeem;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.NumberPicker;

/**
 * Created by Nadeem Bhat on 3/23/2019.
 * Copy Right (c) 04:14 PM.
 * STC,Rangreth
 * nnbhat@stc.in
 */
public class AreaPickerDialog extends DialogFragment {
    private NumberPicker.OnValueChangeListener valueChangeListener;
    String[] areas ={"Anantnag","Bandipora","Baramulla","Budgam","Ganderbal","Kulgam","Kupwara","Pulwama","Shopian","Srinagar","Sopore"};
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final NumberPicker numberPicker = new NumberPicker(getActivity());

        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(areas.length-1);
        numberPicker.setDisplayedValues(areas);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Long-Lati ");
        builder.setMessage("Choose Area :");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                valueChangeListener.onValueChange(numberPicker,
                        numberPicker.getValue(), numberPicker.getValue());
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                valueChangeListener.onValueChange(numberPicker,
                        numberPicker.getValue(), numberPicker.getValue());
            }
        });

        builder.setView(numberPicker);
        return builder.create();
    }

    public NumberPicker.OnValueChangeListener getValueChangeListener() {
        return valueChangeListener;
    }

    public void setValueChangeListener(NumberPicker.OnValueChangeListener valueChangeListener) {
        this.valueChangeListener = valueChangeListener;
    }
}

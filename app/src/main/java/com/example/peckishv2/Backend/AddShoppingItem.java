package com.example.peckishv2.Backend;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.peckishv2.Models.ShoppingListItems;
import com.example.peckishv2.R;
import com.example.peckishv2.Utils.DataBaseManager;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AddShoppingItem extends BottomSheetDialogFragment {

    public static final String tag= "bottomDialog";

    EditText addItemToList;
    ImageButton submitItemToList;
    DataBaseManager db;

    public static AddShoppingItem newInstance(){
        return new AddShoppingItem();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setStyle(STYLE_NORMAL, R.style.bottom_dialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.add_ingredient_to_list, container, false);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return v;
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState)
    {
        super.onViewCreated(v, savedInstanceState);

        addItemToList = getView().findViewById(R.id.add_ingredient);
        submitItemToList = getView().findViewById(R.id.add_item_btn);

        db = new DataBaseManager(getActivity());
        db.openDB();

        boolean isUpdated = false;
        final Bundle bundle = getArguments();
        if(bundle != null)
        {
            isUpdated =true;
            String item = bundle.getString("item");
            addItemToList.setText(item);
        }
        addItemToList.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                submitItemToList.setEnabled(!s.toString().equals(""));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        boolean finalIsUpdated = isUpdated;
        submitItemToList.setOnClickListener(v1 -> {
            String text = addItemToList.getText().toString();
            if(finalIsUpdated)
            {
                db.updateText(bundle.getInt("id"), text);
            }
            else {
                ShoppingListItems shoppingListItems = new ShoppingListItems();
                shoppingListItems.setIngredient(text);
                shoppingListItems.setStatus(0);
            }
            dismiss();
        });
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        Activity activity = getActivity();
        if (activity instanceof DialogCloseListener) {
            ((DialogCloseListener) activity).handleDialogClose(dialog);
        }
    }
}

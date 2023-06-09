package com.example.peckishv2.Backend;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import java.util.Objects;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.add_ingredient_to_list, container, false);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View v,@Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(v, savedInstanceState);

        addItemToList = requireView().findViewById(R.id.add_ingredient);
        submitItemToList = requireView().findViewById(R.id.add_item_btn);

        boolean isUpdated = false;
        final Bundle bundle = getArguments();
        if(bundle != null)
        {
            isUpdated =true;
            String item = bundle.getString("item");
            addItemToList.setText(item);
        }

        db = new DataBaseManager(getActivity());
        db.openDB();

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
            String text = addItemToList.getText().toString().trim();
            if (finalIsUpdated) {
                db.updateText(bundle.getInt("id"), text);
            } else {
                ShoppingListItems shoppingListItems = new ShoppingListItems();
                shoppingListItems.setIngredient(text);
                shoppingListItems.setStatus(0);
                db.insert_ingredient(shoppingListItems);

            }
            dismiss();
        });
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Activity activity = getActivity();
        if (activity instanceof DialogCloseListener) {
            ((DialogCloseListener) activity).handleDialogClose(dialog);
        }
    }
}

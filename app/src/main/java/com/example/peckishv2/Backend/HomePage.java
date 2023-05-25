package com.example.peckishv2.Backend;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import com.example.peckishv2.R;
import org.jetbrains.annotations.Nullable;

public class HomePage extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstancesState)
    {
        return inflater.inflate(R.layout.fragment_home_page,container,false);
    }
}

package com.example.pozoleria_ajzn;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
//import androidx.navigation.fragment.NavHostFragment;

import com.example.pozoleria_ajzn.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    private RadioButton numero1;

    private RadioButton numero2;

    private RadioButton numero3;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = obtenerNumero();

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(phone));
                startActivity(intent);
            }

            private String obtenerNumero() {
                String numeroCel = "tel:";

                numero1 = view.findViewById(R.id.rbtnTEL1);
                numero2 = view.findViewById(R.id.rbtnTEL2);
                numero3 = view.findViewById(R.id.rbtnTEL3);

                if(numero1.isChecked()){
                    numeroCel = numeroCel + "4959561635";
                }else if(numero2.isChecked()){
                    numeroCel = numeroCel + "4959567228";
                }else if(numero3.isChecked()){
                    numeroCel = numeroCel + "4493206501";
                }

                return  numeroCel;
            }

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
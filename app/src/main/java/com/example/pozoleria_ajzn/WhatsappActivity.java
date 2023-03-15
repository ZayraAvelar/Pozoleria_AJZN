package com.example.pozoleria_ajzn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

public class WhatsappActivity extends AppCompatActivity {

    private Button btnEnviarPedido;

    private String mensaje;

    RadioButton rbtnChico ;
    RadioButton rbtnMediano ;
    RadioButton rbtnGrande ;
    RadioButton rbtnRojo ;
    RadioButton rbtnVerde;
    CheckBox cbCebolla ;
    CheckBox cbRabano;
    CheckBox cbLimon ;
    CheckBox cbRepollo;
    CheckBox cbTostadas;
    EditText cantidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatsapp);

        String phone = "524631094808";

        mensaje = obtenermensaje();

        btnEnviarPedido =  (Button) findViewById(R.id.ConfirmarPedido);

        ActivityCompat.requestPermissions(WhatsappActivity.this, new String[]{android.Manifest.permission.SEND_SMS},1);

        btnEnviarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(ActivityCompat.checkSelfPermission(WhatsappActivity.this, android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(WhatsappActivity.this, new String[]{Manifest.permission.SEND_SMS},1);
                }

                mensaje = obtenermensaje();

                Intent sendIntent =  new Intent();
                sendIntent.setAction(Intent.ACTION_VIEW);
                String uri =  "whatsapp://send?phone="+phone+"&text="+mensaje;
                sendIntent.setData(Uri.parse(uri));
                startActivity(sendIntent);
            }
        });

    }

    private String obtenermensaje() {

        String mensaje = "Hola,su pedido fue este: ";

        rbtnChico = (RadioButton) findViewById(R.id.rbtnChico);
        rbtnMediano = (RadioButton) findViewById(R.id.Mediano);
        rbtnGrande = (RadioButton) findViewById(R.id.Grande);
        rbtnRojo = (RadioButton)  findViewById(R.id.rbtnPozoleRojo);
        rbtnVerde = (RadioButton) findViewById(R.id.rbtnPozoleVerde);

        cbCebolla = (CheckBox) findViewById(R.id.cbCebolla);
        cbRabano = (CheckBox) findViewById(R.id.cbRabano);
        cbLimon = (CheckBox) findViewById(R.id.cbLimon);
        cbRepollo = (CheckBox) findViewById(R.id.cbRepollo);
        cbTostadas = (CheckBox) findViewById(R.id.cbTostadas);
        cantidad = (EditText) findViewById(R.id.EspCantidad);

        if(cantidad.getText().toString() == ""){
            mensaje = mensaje + "\n\nCantidad : 1";
        }else {
            mensaje = mensaje + "\n\nCantidad : " + cantidad.getText().toString();
        }


        if(rbtnChico.isChecked()){
            mensaje = mensaje + "\n\nTamaño de Pozole: Chico";

        }else if (rbtnMediano.isChecked()){
            mensaje = mensaje + "\n\nTamaño de Pozole: Mediano";
        }else if(rbtnGrande.isChecked()){
            mensaje = mensaje + "\n\nTamaño de Pozole: Grande";
        }

        if (rbtnRojo.isChecked()){
            mensaje = mensaje + "\n\nTipo de Pozole: Rojo";
        }else if (rbtnVerde.isChecked()){
            mensaje = mensaje + "\n\nTipo de Pozole: Verde";
        }

        mensaje = mensaje + "\n\nIngredientes Extra:";

        if(cbCebolla.isChecked()  ){
            mensaje = mensaje + "\nCebolla";
        }

        if(cbRabano.isChecked()){
            mensaje = mensaje + "\nRabano";
        }

        if(cbLimon.isChecked()){
            mensaje = mensaje + "\nLimon";
        }

        if(cbTostadas.isChecked()){
            mensaje = mensaje + "\nTostadas";
        }

        if(cbRepollo.isChecked()){
            mensaje = mensaje + "\nRepollo";
        }

        mensaje = mensaje + "\n\nGracias por elegirnos! ";
        return mensaje;
    }
}
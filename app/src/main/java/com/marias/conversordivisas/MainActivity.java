package com.marias.conversordivisas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String>divisas;
    private ArrayList<Double>factoresCambio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializaDivisas();
        inicializarFacatoresCambio();

        RecyclerView rvDivisas=findViewById(R.id.rvDivisas);
        LinearLayoutManager managerLayout=new LinearLayoutManager(this);
        rvDivisas.setLayoutManager(managerLayout);
        MiAdaptador adaptador=new MiAdaptador(this,divisas);
        rvDivisas.setAdapter(adaptador);

        DividerItemDecoration decoration=new DividerItemDecoration(rvDivisas.getContext(),managerLayout.getOrientation());
        rvDivisas.addItemDecoration(decoration);

        Button bConvertir=(Button) findViewById(R.id.bConvertir);
        Switch swVIP=(Switch) findViewById(R.id.swVIP);
        bConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //llamar al método que hace el cambio
                actualizarCambio(swVIP.isChecked(),adaptador.getElementoSeleccionado());
            }
        });

       /* swVIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Llamar al método que hace el cambio
                actualizarCambio(swVIP.isChecked(),adaptador.getElementoSeleccionado());
            }
        });*/
    }
    public void actualizarCambio(boolean VIP,int  cambioSeleccionado){
        TextView tvResultado=findViewById(R.id.tvResultado);
        if(cambioSeleccionado!=-1){
            try {
                EditText etCantidad=(EditText) findViewById(R.id.etCantidadInicial);
                double euros=Double.parseDouble(etCantidad.getText().toString());
                double cambio=VIP? factoresCambio.get(cambioSeleccionado): factoresCambio.get(cambioSeleccionado)*1.01;
                tvResultado.setText(""+(cambio*euros));
            }catch (NumberFormatException ex){
                tvResultado.setText("");
            }
        }
    }

    private void inicializarFacatoresCambio(){
        factoresCambio=new ArrayList<>();
        factoresCambio.add(1.12);
        factoresCambio.add(0.85);
        factoresCambio.add(1.43);
        factoresCambio.add(1.57);
        factoresCambio.add(128.17);
        factoresCambio.add(85.36);
        factoresCambio.add(1.66);
        factoresCambio.add(1.044);
        factoresCambio.add(18.030472);
        factoresCambio.add(83.219626);
    }
    private void inicializaDivisas(){
        divisas=new ArrayList<>();
        divisas.add("USD");
        divisas.add("GBP");
        divisas.add("CAD");
        divisas.add("JPY");
        divisas.add("INR");
        divisas.add("ZND");
        divisas.add("CHF");
        divisas.add("ZAR");
        divisas.add("RUB");
    }
}
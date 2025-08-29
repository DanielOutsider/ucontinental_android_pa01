package chocano.pa1.org.ui.bindec;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.math.BigInteger;

import chocano.pa1.org.R;

public class BinarioDecimalFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_binario_decimal_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText inputBinario = view.findViewById(R.id.inputBinario);
        Button btnConvertir   = view.findViewById(R.id.btnConvertir);
        TextView txtResultado = view.findViewById(R.id.txtResultado);
        TextView txtMensaje   = view.findViewById(R.id.txtMensaje);

        btnConvertir.setOnClickListener(v -> {
            txtMensaje.setText(""); // limpia mensajes previos

            String bin = inputBinario.getText().toString().trim();

            if (TextUtils.isEmpty(bin)) {
                inputBinario.setError("Ingresa un número binario");
                return;
            }

            // Valida que solo tenga 0 y 1
            if (!bin.matches("[01]+")) {
                txtMensaje.setText("Solo se permiten dígitos 0 y 1.");
                return;
            }

            try {
                // BigInteger soporta binarios muy largos sin overflow
                BigInteger decimal = new BigInteger(bin, 2);
                txtResultado.setText("Decimal: " + decimal.toString());
            } catch (Exception e) {
                txtMensaje.setText("Error al convertir. Verifica el número.");
            }
        });
    }
}

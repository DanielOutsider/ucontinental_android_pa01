package chocano.pa1.org.ui.hexbin;

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

import chocano.pa1.org.R;

public class HexaBinarioFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_hexa_binario_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText inputHex = view.findViewById(R.id.inputHex);
        Button btnConvertir = view.findViewById(R.id.btnConvertirHex);
        TextView txtPadded = view.findViewById(R.id.txtBinarioPadded);
        TextView txtTrimmed = view.findViewById(R.id.txtBinarioTrimmed);
        TextView txtMensaje = view.findViewById(R.id.txtMensajeHex);

        btnConvertir.setOnClickListener(v -> {
            txtMensaje.setText("");
            txtPadded.setText("Binario (4 bits por dígito): —");
            txtTrimmed.setText("Binario (sin ceros a la izquierda): —");

            String hex = inputHex.getText().toString().trim();
            if (TextUtils.isEmpty(hex)) {
                inputHex.setError("Ingresa un número hexadecimal");
                return;
            }

            // Validar: solo 0-9, A-F (case-insensitive)
            if (!hex.matches("(?i)[0-9a-f]+")) {
                txtMensaje.setText("Solo se permiten caracteres hexadecimales (0-9, A-F).");
                return;
            }

            // Convertir: 4 bits por cada dígito (padded)
            StringBuilder binPadded = new StringBuilder();
            for (char c : hex.toCharArray()) {
                int val = Character.digit(c, 16); // 0..15
                String nibble = Integer.toBinaryString(val); // 1..4 bits
                // Asegurar 4 bits con ceros a la izquierda
                String padded = String.format("%4s", nibble).replace(' ', '0');
                binPadded.append(padded);
            }

            String paddedStr = binPadded.toString();
            // Versión sin ceros a la izquierda (salvo que el valor sea 0)
            String trimmedStr = paddedStr.replaceFirst("^0+(?!$)", "");

            txtPadded.setText("Binario (4 bits por dígito): " + paddedStr);
            txtTrimmed.setText("Binario (sin ceros a la izquierda): " + trimmedStr);
        });
    }
}

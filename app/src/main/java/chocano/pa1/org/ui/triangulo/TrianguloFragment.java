package chocano.pa1.org.ui.triangulo;

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

public class TrianguloFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_triangulo_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText inputA = view.findViewById(R.id.inputLadoA);
        EditText inputB = view.findViewById(R.id.inputLadoB);
        EditText inputC = view.findViewById(R.id.inputLadoC);
        Button btnCalcular = view.findViewById(R.id.btnCalcular);
        TextView txtResultado = view.findViewById(R.id.txtResultado);

        btnCalcular.setOnClickListener(v -> {
            String strA = inputA.getText().toString();
            String strB = inputB.getText().toString();
            String strC = inputC.getText().toString();

            if (TextUtils.isEmpty(strA) || TextUtils.isEmpty(strB) || TextUtils.isEmpty(strC)) {
                txtResultado.setText("Por favor ingresa los 3 lados");
                return;
            }

            double a = Double.parseDouble(strA);
            double b = Double.parseDouble(strB);
            double c = Double.parseDouble(strC);

            // Verificar desigualdad triangular
            if (a + b <= c || a + c <= b || b + c <= a) {
                txtResultado.setText("Los lados no forman un triángulo válido");
                return;
            }

            double s = (a + b + c) / 2.0; // semiperímetro
            double area = Math.sqrt(s * (s - a) * (s - b) * (s - c));

            txtResultado.setText("Resultado: Área = " + area);
        });
    }
}

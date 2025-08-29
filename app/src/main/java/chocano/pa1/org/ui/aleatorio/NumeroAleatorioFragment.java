package chocano.pa1.org.ui.aleatorio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Random;

import chocano.pa1.org.R;

public class NumeroAleatorioFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_numero_aleatorio, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnGenerar = view.findViewById(R.id.btnGenerar);
        TextView txtNumero   = view.findViewById(R.id.txtNumero);
        TextView txtImpares  = view.findViewById(R.id.txtImpares);
        TextView txtResultado= view.findViewById(R.id.txtResultado);

        Random random = new Random();

        btnGenerar.setOnClickListener(v -> {
            // Generar un número aleatorio de 6 cifras en un único paso
            int numero = 100000 + random.nextInt(900000); // 100000..999999

            // Contar dígitos impares (en una sola pasada por las cifras)
            int impares = contarImpares(numero);

            // Condición: exactamente 3 dígitos impares
            boolean esCorrecto = (impares == 3);
            // Si lo quieres "al menos 3", usa: boolean esCorrecto = (impares >= 3);

            // Mostrar resultados
            txtNumero.setText("Número: " + numero);
            txtImpares.setText("Dígitos impares: " + impares);
            txtResultado.setText("Resultado: " + (esCorrecto ? "es correcto" : "no es correcto"));
        });
    }

    private int contarImpares(int numero) {
        int count = 0;
        int n = numero;
        while (n > 0) {
            int d = n % 10;
            if ((d % 2) != 0) count++;
            n /= 10;
        }
        return count;
    }
}

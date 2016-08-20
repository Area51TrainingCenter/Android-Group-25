package com.jcodee.mod1class4_2.fragment;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.jcodee.mod1class4_2.MainActivity;
import com.jcodee.mod1class4_2.R;
import com.jcodee.mod1class4_2.modelos.Foto;
import com.jcodee.mod1class4_2.zoomable.ZoomableDraweeView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FotoFragment extends Fragment {
    private ZoomableDraweeView sdvImagen;
    private TextView tvTitulo;
    private Button btnDescripcion;


    public FotoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_foto, container, false);

        sdvImagen = (ZoomableDraweeView) root.findViewById(R.id.sdvImagen);
        tvTitulo = (TextView) root.findViewById(R.id.tvTitulo);
        btnDescripcion = (Button) root.findViewById(R.id.btnDescripcion);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (getArguments().containsKey("posicion")) {
            int posicion = getArguments().getInt("posicion");
            Foto item = MainActivity.listaFotos.get(posicion);

            final AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setUri(Uri.parse(item.getRutaFoto()))
                    .setOldController(sdvImagen.getController())
                    .build();

            sdvImagen.setController(controller);

            tvTitulo.setText(item.getTitulo());
        }
    }
}

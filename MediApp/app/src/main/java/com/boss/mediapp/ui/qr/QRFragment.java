package com.boss.mediapp.ui.qr;

import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.boss.mediapp.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class QRFragment extends Fragment {

    private QRViewModel qrViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        qrViewModel =
                ViewModelProviders.of(this).get(QRViewModel.class);
        View root = inflater.inflate(R.layout.fragment_qr, container, false);
        String text = "vaibagga@gmail.com"; // Whatever you need to encode in the QR code
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        TextView textView = root.findViewById(R.id.tv);
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Antonio-Regular.ttf");
        textView.setTypeface(tf, Typeface.BOLD);
        ImageView imageView = root.findViewById(R.id.iv);
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imageView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        return root;
    }
}
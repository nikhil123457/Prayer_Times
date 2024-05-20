package com.vitcode.iprayertimes.dateconverter.abcd;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.vitcode.iprayertimes.R;


public class ImageFragment extends Fragment {

    ImageView Imageii01, Imageii02, Imageii03, Imageii04, Imageii05, Imageii06, Imageii07, Imageii08, Imageii09, Imageii010, Imageii011, Imageii012;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_image, container, false);

        // Initialize ImageView components
        Imageii01 = rootView.findViewById(R.id.Imageii01);
        Imageii02 = rootView.findViewById(R.id.Imageii02);
        Imageii03 = rootView.findViewById(R.id.Imageii03);
        Imageii04 = rootView.findViewById(R.id.Imageii04);
        Imageii05 = rootView.findViewById(R.id.Imageii05);
        Imageii06 = rootView.findViewById(R.id.Imageii06);
        Imageii07 = rootView.findViewById(R.id.Imageii07);
        Imageii08 = rootView.findViewById(R.id.Imageii08);
        Imageii09 = rootView.findViewById(R.id.Imageii09);
        Imageii010 = rootView.findViewById(R.id.Imageii010);
        Imageii011 = rootView.findViewById(R.id.Imageii011);
        Imageii012 = rootView.findViewById(R.id.Imageii012);

        // Set click listeners for each ImageView
        Imageii01.setOnClickListener(view -> onImageClicked(Imageii01));
        Imageii02.setOnClickListener(view -> onImageClicked(Imageii02));
        Imageii03.setOnClickListener(view -> onImageClicked(Imageii03));
        Imageii04.setOnClickListener(view -> onImageClicked(Imageii04));
        Imageii05.setOnClickListener(view -> onImageClicked(Imageii05));
        Imageii06.setOnClickListener(view -> onImageClicked(Imageii06));
        Imageii07.setOnClickListener(view -> onImageClicked(Imageii07));
        Imageii08.setOnClickListener(view -> onImageClicked(Imageii08));
        Imageii09.setOnClickListener(view -> onImageClicked(Imageii09));
        Imageii010.setOnClickListener(view -> onImageClicked(Imageii010));
        Imageii011.setOnClickListener(view -> onImageClicked(Imageii011));
        Imageii012.setOnClickListener(view -> onImageClicked(Imageii012));

        return rootView;
    }

    private void onImageClicked(ImageView imageView) {
        // Get the image resource ID of the clicked ImageView
        int imageResourceId = getImageResourceId(imageView);

        // Pass the image resource ID to the activity
        ((EditsActivity) requireActivity()).setImage(imageResourceId);
    }

    private int getImageResourceId(ImageView imageView) {
        return getResources().getIdentifier(imageView.getTag().toString(), "drawable", requireActivity().getPackageName());
    }
}

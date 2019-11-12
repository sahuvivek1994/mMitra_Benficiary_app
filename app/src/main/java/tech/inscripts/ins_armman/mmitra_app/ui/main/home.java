package tech.inscripts.ins_armman.mmitra_app.ui.main;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import tech.inscripts.ins_armman.mmitra_app.R;

public class home extends Fragment {

    private ProgressBar progressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.circular);
        final ProgressBar mProgress = (ProgressBar) view.findViewById(R.id.circularProgressbar);
        mProgress.setProgress(65);   // Main Progress
        mProgress.setSecondaryProgress(100); // Secondary Progress
        mProgress.setMax(100); // Maximum Progress
        mProgress.setProgressDrawable(drawable);

        return view;
    }
}

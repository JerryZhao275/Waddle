package com.example.educationapplication.views.Fragment;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.educationapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DashboardFragment extends Fragment implements View.OnClickListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    View view;
    TextView visibleText;
    FloatingActionButton addClass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        addClass = view.findViewById(R.id.addClassButton);
        addClass.setOnClickListener(this);
        visibleText = view.findViewById(R.id.visibilityTest);
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.addClassButton && visibleText.getVisibility() == View.INVISIBLE) {
            visibleText.setVisibility(View.VISIBLE);
            addClass.setImageResource(R.drawable.cancelbutton);
        }
        else if (view.getId() == R.id.addClassButton && visibleText.getVisibility() == View.VISIBLE) {
            visibleText.setVisibility(View.INVISIBLE);
            addClass.setImageResource(R.drawable.add);
        }
    }
}
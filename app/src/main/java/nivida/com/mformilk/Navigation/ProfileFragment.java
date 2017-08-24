package nivida.com.mformilk.Navigation;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baoyz.actionsheet.ActionSheet;
import com.pkmmte.view.CircularImageView;

import nivida.com.mformilk.R;


public class ProfileFragment extends Fragment implements
        ActionSheet.ActionSheetListener {
    FloatingActionButton fab;
    ImageView profile;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getContext().getTheme().applyStyle(R.style.ActionSheetStyleiOS7, true);
        View rootView = inflater.inflate(R.layout.fragment_profile,null);

         fab = (FloatingActionButton)rootView.findViewById(R.id.fab);
        profile= (ImageView) rootView.findViewById(R.id.edit_image);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),EditProfile.class);
                startActivity(i);
               // getActivity().finish();
                getActivity().overridePendingTransition(R.anim.from_left,R.anim.from_right);


            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showActionSheet();

            }
        });


        return rootView;
    }
    public void showActionSheet() {
        ActionSheet.createBuilder(getActivity(), getFragmentManager())
                .setCancelButtonTitle("Cancel")
                .setOtherButtonTitles("Edit Photo", "Remove Photo")
                .setCancelableOnTouchOutside(true).setListener(this).show();
    }


    @Override
    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {

    }

    @Override
    public void onOtherButtonClick(ActionSheet actionSheet, int index) {

    }
}

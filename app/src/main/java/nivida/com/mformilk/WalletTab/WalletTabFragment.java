package nivida.com.mformilk.WalletTab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nivida.com.mformilk.R;

/**
 * Created by Jauhar xlr on 4/18/2016.
 *  mycreativecodes.in
 */
public class WalletTabFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.wallet_layout,null);

        return rootView;
    }
}

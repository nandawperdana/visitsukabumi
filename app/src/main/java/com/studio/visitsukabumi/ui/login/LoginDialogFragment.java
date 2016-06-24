package com.studio.visitsukabumi.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.studio.visitsukabumi.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by NwP.
 */
public class LoginDialogFragment extends DialogFragment {

    @Bind(R.id.button_login_facebook_dialog)
    Button buttonFacebook;
    @Bind(R.id.textinputlayout_login_email)
    TextInputLayout textInputLayoutEmail;
    @Bind(R.id.autocomplete_login_email)
    AutoCompleteTextView autoCompleteTextViewEmail;
    @Bind(R.id.textinputlayout_login_password)
    TextInputLayout textInputLayoutPass;
    @Bind(R.id.button_login)
    Button buttonLogin;

    @OnClick(R.id.button_login)
    public void onClickLogin() {
        showMainActivity();
    }

    public LoginDialogFragment() {
        // empty constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_login, container, false);
        ButterKnife.bind(this, rootView);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return rootView;
    }

    private void showMainActivity() {
        startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().finish();
    }
}

package com.adpth.signupapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.adpth.signupapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    boolean length8=false, uppercase=false, digit=false, special=false,success=false;
    String password,email,username;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TextfieldCheck();
        SignupUser();

    }

    private void SignupUser() {
        binding.signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (success)
                {
                    Toast.makeText(MainActivity.this,"Account Created",Toast.LENGTH_LONG).show();
                    success = false;
                }
                else {
                    if (TextUtils.isEmpty(username))
                    {
                        binding.usernameInput.setError("Field cannot be left blank.");
                        success = false;
                    }
                    else if (TextUtils.isEmpty(email))
                    {
                        binding.emailInput.setError("Field cannot be left blank.");
                        success = false;
                    }
                    else if (TextUtils.isEmpty(password))
                    {
                        binding.passwordInput.setError("Field cannot be left blank.");
                        success = false;
                    }
                }
            }
        });

    }

    private void TextfieldCheck() {
        binding.passwordInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkRules();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.usernameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkRules();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.emailInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkRules();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @SuppressLint("ResourceType")
    private void checkRules() {
        password = binding.passwordInput.getText().toString();
        email = binding.emailInput.getText().toString();
        username = binding.usernameInput.getText().toString();

        if (password.length()>0)
        {
            if (password.length()<4)
            {
                binding.length.setCardBackgroundColor(Color.parseColor(getString(R.color.LowLevel)));
                length8 = false;
            }
            else if (password.length()>4 && password.length()<8)
            {
                binding.length.setCardBackgroundColor(Color.parseColor(getString(R.color.MediumLevel)));
                length8 = false;
            }
            else if (password.length()>=8)
            {
                binding.length.setCardBackgroundColor(Color.parseColor(getString(R.color.StrongLevel)));
                length8 = true;
            }
        }
        else
        {
            binding.length.setCardBackgroundColor(Color.parseColor(getString(R.color.LowLevel)));
            length8 = false;
        }

        if (password.matches("(.*[A-Z].*)"))
        {
            binding.upperCase.setCardBackgroundColor(Color.parseColor(getString(R.color.StrongLevel)));
            uppercase = true;
        }
        else
        {
            binding.upperCase.setCardBackgroundColor(Color.parseColor(getString(R.color.LowLevel)));
            uppercase = false;
        }

        if (password.matches("(.*[:?!@#$%^&*()].*)"))
        {
            binding.specialChr.setCardBackgroundColor(Color.parseColor(getString(R.color.StrongLevel)));
            special = true;
        }
        else
        {
            binding.specialChr.setCardBackgroundColor(Color.parseColor(getString(R.color.LowLevel)));
            special = false;
        }

        if (password.matches("(.*[0-9].*)"))
        {
            binding.digits.setCardBackgroundColor(Color.parseColor(getString(R.color.StrongLevel)));
            digit = true;
        }
        else
        {
            binding.digits.setCardBackgroundColor(Color.parseColor(getString(R.color.LowLevel)));
            digit = false;
        }

        checkinput();
    }

    @SuppressLint("ResourceType")
    private void checkinput() {

        if (special && digit && length8 && uppercase && email.length()>0 && password.length()>0 && username.length()>0 )
        {
            success=true;
            binding.signupBtn.setCardBackgroundColor(Color.parseColor(getString(R.color.enabled)));
        }
        else {

            success=false;
            binding.signupBtn.setCardBackgroundColor(Color.parseColor(getString(R.color.disabled)));
        }


    }
}
package com.example.holiday.loan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.example.holiday.R;
import com.example.holiday.api.ConsumeAPI;

import org.json.JSONException;

import java.io.IOException;

public class LoanCreateActivity extends AppCompatActivity {
    Toolbar loan_toolbar;
    ImageView imgName,imgGender,imgAge,imgJob,imgIncome,imgExpense,imgPhone,imgAddress,imgAmount,
            imgDuration,imgPurpose,imgStatus,imgStateID,imgFamily,imgStaff,imgHourse;
    EditText etName,etAge,etJob,etIncome,etExpense,etPhone,etAddress,etAmount,etDuration,etPurpose,etStatus,edLoanPrice,edLoanDeposit,edInterestRate,edTerm;
    TextView tvGender,tvStateID,tvFamily,tvStaff,tvHourse;
    Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_create);

        loan_toolbar = (Toolbar)findViewById(R.id.toolbar_fill_information);
        loan_toolbar.setTitle("");

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.grey_light));

        setSupportActionBar(loan_toolbar);
        loan_toolbar.setNavigationIcon(R.drawable.icon_back);
        loan_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ID_Field();
        EventText();
        DropList();
        btnSubmit=(Button) findViewById(R.id.btnsubmit);
        edLoanPrice=(EditText) findViewById(R.id.ed_loan_price);
        edInterestRate=(EditText) findViewById(R.id.ed_loan_deposit);
        edLoanDeposit=(EditText) findViewById(R.id.ed_loan_deposit);
        edTerm=(EditText) findViewById(R.id.ed_loan_term);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void DropList() {
        tvGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu pop_Gender= new PopupMenu(v.getContext(),v, Gravity.RIGHT);
                pop_Gender.getMenuInflater().inflate(R.menu.gender,pop_Gender.getMenu());


                pop_Gender.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        tvGender.setText(item.getTitle());
                        return true;
                    }
                });
                pop_Gender.show();
            }
        });

        tvStateID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu pop_stateId= new PopupMenu(v.getContext(),v, Gravity.RIGHT);
                pop_stateId.getMenuInflater().inflate(R.menu.state_id,pop_stateId.getMenu());


                pop_stateId.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        tvStateID.setText(item.getTitle());
                        return true;
                    }
                });
                pop_stateId.show();
            }
        });

        tvFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu pop_family= new PopupMenu(v.getContext(),v, Gravity.RIGHT);
                pop_family.getMenuInflater().inflate(R.menu.family_book,pop_family.getMenu());


                pop_family.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        tvFamily.setText(item.getTitle());
                        return true;
                    }
                });
                pop_family.show();
            }
        });

        tvStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu pop_staff= new PopupMenu(v.getContext(),v, Gravity.RIGHT);
                pop_staff.getMenuInflater().inflate(R.menu.staff_id,pop_staff.getMenu());


                pop_staff.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        tvStaff.setText(item.getTitle());
                        return true;
                    }
                });
                pop_staff.show();
            }
        });

        tvHourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu pop_hourse= new PopupMenu(v.getContext(),v, Gravity.RIGHT);
                pop_hourse.getMenuInflater().inflate(R.menu.hourse,pop_hourse.getMenu());


                pop_hourse.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        tvHourse.setText(item.getTitle());
                        return true;
                    }
                });
                pop_hourse.show();
            }
        });
    }

    private void ID_Field() {
        imgName =   (ImageView)findViewById(R.id.imgNameL);
        imgGender = (ImageView)findViewById(R.id.imgGenderL);
        imgAge =    (ImageView)findViewById(R.id.imgAgeL);
        imgJob =    (ImageView)findViewById(R.id.imgJobL);
        imgIncome = (ImageView)findViewById(R.id.imgincomeL);
        imgExpense =(ImageView)findViewById(R.id.imgExpenseL);
        imgPhone =  (ImageView)findViewById(R.id.imgPhoneLW);
        imgAddress =(ImageView)findViewById(R.id.imgAddressL);
        imgAmount = (ImageView)findViewById(R.id.imgloanAmmount);
        imgDuration=(ImageView)findViewById(R.id.imgLoanDuration);
        imgPurpose =(ImageView)findViewById(R.id.imgLoanPurpose);
        imgStatus = (ImageView)findViewById(R.id.imgloanstatus);
        imgStateID =(ImageView)findViewById(R.id.imgstateid);
        imgStaff =  (ImageView)findViewById(R.id.imgstaff);
        imgFamily = (ImageView)findViewById(R.id.imgfamily);
        imgHourse = (ImageView)findViewById(R.id.imgHours);

        etName =   (EditText) findViewById(R.id.etNameL);
        tvGender = (TextView) findViewById(R.id.etGenderL);
        etAge =    (EditText) findViewById(R.id.etAgeL);
        etJob =    (EditText) findViewById(R.id.etJobL);
        etIncome = (EditText) findViewById(R.id.etIncomeL);
        etExpense =(EditText) findViewById(R.id.etExpenseL);
        etPhone =  (EditText) findViewById(R.id.etPhoneLW);
        etAddress =(EditText) findViewById(R.id.etAddressL);
        etAmount = (EditText) findViewById(R.id.etLoanAmount);
        etDuration=(EditText) findViewById(R.id.etLoanDuration);
        etPurpose =(EditText) findViewById(R.id.etLoanPurpose);
        etStatus = (EditText) findViewById(R.id.etStatus);
        tvStateID =(TextView) findViewById(R.id.etStateID);
        tvStaff =  (TextView) findViewById(R.id.etstaff);
        tvFamily = (TextView) findViewById(R.id.etfamily);
        tvHourse = (TextView) findViewById(R.id.etHours);

    }

    private void EventText() {

        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    imgName.setImageResource(R.drawable.icon_null);
                } else if (s.length() < 3) {
                    imgName.setImageResource(R.drawable.ic_error_black_24dp);
                } else imgName.setImageResource(R.drawable.icon_ok);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tvGender.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    imgGender.setImageResource(R.drawable.icon_null);
                } else imgGender.setImageResource(R.drawable.icon_ok);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etAge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    imgAge.setImageResource(R.drawable.icon_null);
                } else if (s.length() < 3) {
                    imgAge.setImageResource(R.drawable.ic_error_black_24dp);
                } else imgAge.setImageResource(R.drawable.icon_ok);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etJob.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    imgJob.setImageResource(R.drawable.icon_null);
                } else if (s.length() < 3) {
                    imgJob.setImageResource(R.drawable.ic_error_black_24dp);
                } else imgJob.setImageResource(R.drawable.icon_ok);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etIncome.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    imgIncome.setImageResource(R.drawable.icon_null);
                } else if (s.length() < 3) {
                    imgIncome.setImageResource(R.drawable.ic_error_black_24dp);
                } else imgIncome.setImageResource(R.drawable.icon_ok);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etExpense.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    imgExpense.setImageResource(R.drawable.icon_null);
                } else if (s.length() < 3) {
                    imgExpense.setImageResource(R.drawable.ic_error_black_24dp);
                } else imgExpense.setImageResource(R.drawable.icon_ok);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    imgPhone.setImageResource(R.drawable.icon_null);
                } else if (s.length() < 3) {
                    imgPhone.setImageResource(R.drawable.ic_error_black_24dp);
                } else imgPhone.setImageResource(R.drawable.icon_ok);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    imgAddress.setImageResource(R.drawable.icon_null);
                } else if (s.length() < 3) {
                    imgAddress.setImageResource(R.drawable.ic_error_black_24dp);
                } else imgAddress.setImageResource(R.drawable.icon_ok);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    imgAmount.setImageResource(R.drawable.icon_null);
                } else if (s.length() < 3) {
                    imgAmount.setImageResource(R.drawable.ic_error_black_24dp);
                } else imgAmount.setImageResource(R.drawable.icon_ok);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etDuration.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    imgDuration.setImageResource(R.drawable.icon_null);
                } else if (s.length() < 3) {
                    imgDuration.setImageResource(R.drawable.ic_error_black_24dp);
                } else imgDuration.setImageResource(R.drawable.icon_ok);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etPurpose.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    imgPurpose.setImageResource(R.drawable.icon_null);
                } else if (s.length() < 3) {
                    imgPurpose.setImageResource(R.drawable.ic_error_black_24dp);
                } else imgPurpose.setImageResource(R.drawable.icon_ok);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etStatus.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    imgStatus.setImageResource(R.drawable.icon_null);
                } else if (s.length() < 3) {
                    imgStatus.setImageResource(R.drawable.ic_error_black_24dp);
                } else imgStatus.setImageResource(R.drawable.icon_ok);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tvStaff.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    imgStaff.setImageResource(R.drawable.icon_null);
                } else imgStaff.setImageResource(R.drawable.icon_ok);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tvStateID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    imgStateID.setImageResource(R.drawable.icon_null);
                } else imgStateID.setImageResource(R.drawable.icon_ok);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tvFamily.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    imgFamily.setImageResource(R.drawable.icon_null);
                } else imgFamily.setImageResource(R.drawable.icon_ok);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tvHourse.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    imgHourse.setImageResource(R.drawable.icon_null);
                } else imgHourse.setImageResource(R.drawable.icon_ok);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }  // event text

    private void requestPost() throws IOException, JSONException{

        String URL_ENDPOINT=String.format("%s%s", ConsumeAPI.BASE_URL,"api/v1/loan/");
        
    }
}

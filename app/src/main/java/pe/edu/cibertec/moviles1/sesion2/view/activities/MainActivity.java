package pe.edu.cibertec.moviles1.sesion2.view.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import pe.edu.cibertec.moviles1.sesion2.R;
import pe.edu.cibertec.moviles1.sesion2.data.retrofit.RetrofitClient;
import pe.edu.cibertec.moviles1.sesion2.data.retrofit.UsersApi;
import pe.edu.cibertec.moviles1.sesion2.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<List<User>>,
        View.OnClickListener, AdapterView.OnItemSelectedListener, CompoundButton.OnCheckedChangeListener {

    Spinner spUsers;
    ArrayAdapter<String> adapter;
    private List<User> users = new ArrayList<>();
    private List<String> userNames = new ArrayList<>();

    Button btnShow;

    Switch swToggleSelection;
    boolean checked = false;

    TextView lblId;
    TextView lblName;
    TextView lblUsername;
    TextView lblEmail;
    TextView lblPhone;
    TextView lblAddress;
    TextView lblWebsite;
    TextView lblCompany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spUsers = findViewById(R.id.spUsers);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, userNames);
        spUsers.setAdapter(adapter);

        btnShow = findViewById(R.id.btnShow);
        btnShow.setOnClickListener(this);

        swToggleSelection = findViewById(R.id.swToggleSelection);
        swToggleSelection.setOnCheckedChangeListener(this);

        lblId = findViewById(R.id.lblId);
        lblName = findViewById(R.id.lblName);
        lblUsername = findViewById(R.id.lblUsername);
        lblEmail = findViewById(R.id.lblEmail);
        lblPhone = findViewById(R.id.lblPhone);
        lblAddress = findViewById(R.id.lblAddress);
        lblWebsite = findViewById(R.id.lblWebsite);
        lblCompany = findViewById(R.id.lblCompany);

        UsersApi usersClient = RetrofitClient.getInstance().create(UsersApi.class);
        usersClient.getUsers().enqueue(this);
    }

    @Override
    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
        Log.d("users", "successful: " + response.isSuccessful() + ", body: " + response.body());

        if (response.isSuccessful()) {
            users.addAll(response.body());
            userNames.addAll(users.stream().map(User::getName).collect(Collectors.toList()));
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, response.message(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<List<User>> call, Throwable t) {
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        showUser();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // Do nothing
    }

    @Override
    public void onClick(View view) {
        showUser();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
        this.checked = checked;
        spUsers.setOnItemSelectedListener(checked ? this : null);
        btnShow.setEnabled(!checked);
    }

    private void showUser() {
        String userName = (String) spUsers.getSelectedItem();
        if (Objects.nonNull(userName) && !userName.isEmpty()) {
            users.stream()
                    .filter(u -> u.getName().equals(userName))
                    .findFirst()
                    .ifPresent(u -> {
                        lblId.setText("ID:\t" + u.getId());
                        lblUsername.setText("Username:\t" + u.getUsername());
                        lblName.setText("Name:\t" + u.getName());
                        lblEmail.setText("Email:\t" + u.getEmail());
                        lblPhone.setText("Phone:\t" + u.getPhone());
                        lblAddress.setText("Address:\t" + u.getAddress().getStreet().concat(" - ").concat(u.getAddress().getCity()));
                        lblWebsite.setText("Website:\t" + u.getWebsite());
                        lblCompany.setText("Company:\t" + u.getCompany().getName());
                    });
        }
    }

}
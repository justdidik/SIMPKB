package com.example.simpkb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class reposity extends AppCompatActivity {
    private MyAdapter myAdapter;
    private RecyclerView myRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reposity);


//Create a handler for the RetrofitInstance interface//

        GetData service = RetrofitClient.getRetrofitInstance().create(GetData.class);

        Call<List<RetroUsers>> call = service.getAllUsers();

//Execute the request asynchronously//

        call.enqueue(new Callback<List<RetroUsers>>() {



//Handle a successful response//



            @Override
            public void onResponse(Call<List<RetroUsers>> call, Response<List<RetroUsers>> response) {
                loadDataList(response.body());

            }

            @Override

//Handle execution failures//

            public void onFailure(Call<List<RetroUsers>> call, Throwable throwable) {

//If the request fails, then display the following toast//

                Toast.makeText(reposity.this, "Unable to load users", Toast.LENGTH_SHORT).show();
            }
        });
    }

//Display the retrieved data as a list//

    private void loadDataList(List<RetroUsers> usersList) {

//Get a reference to the RecyclerView//

        myRecyclerView = findViewById(R.id.myRecyclerView);
        myAdapter = new MyAdapter(usersList);

//Use a LinearLayoutManager with default vertical orientation//

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(reposity.this);
        myRecyclerView.setLayoutManager(layoutManager);

//Set the Adapter to the RecyclerView//

        myRecyclerView.setAdapter(myAdapter);
    }
}
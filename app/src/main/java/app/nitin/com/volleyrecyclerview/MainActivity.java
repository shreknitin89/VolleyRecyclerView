package app.nitin.com.volleyrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<Result> tracks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        adapter = new FilmAdapter(new ArrayList<Result>());
        recyclerView.setAdapter(adapter);
        initArray();

    }

    private Example transformJSONToPojo(String msg){
        Example example;
       // GsonBuilder builder = new GsonBuilder();
        Gson gson = new GsonBuilder().create();
        example = gson.fromJson(msg, Example.class);
        return example;
    }

    private void initArray() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://itunes.apple.com/search?term=rock";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        tracks = transformJSONToPojo(response).getResults();
                        adapter = new FilmAdapter(tracks);
                        recyclerView.setAdapter(adapter);

                        // Result handling
                        System.out.println(response.substring(0, 100));

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                // Error handling
                System.out.println("Something went wrong!");
                error.printStackTrace();

            }
        });
        // Add the request to the queue
        queue.add(stringRequest);
    }
    }


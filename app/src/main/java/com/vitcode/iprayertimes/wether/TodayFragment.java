package com.vitcode.iprayertimes.wether;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.vitcode.iprayertimes.R;
import com.vitcode.iprayertimes.wether.adapters.WeatherAdapter;
import com.vitcode.iprayertimes.wether.adapters.WeekWeatherAdapter;
import com.vitcode.iprayertimes.wether.models.WeatherModel;
import com.vitcode.iprayertimes.wether.models.WeekWeatherModel;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TodayFragment extends Fragment {
    TextView cityNameTextView,timeTextView, maxTempTextView, tempTextView, feelTextView, weatherTextView;
    RecyclerView hourlyRecyclerView;
    ArrayList<WeatherModel> weatherModelArrayList;
    WeatherAdapter weatherAdapter;
    Wetherrr mainActivity;
    String city;

    ArrayList<WeekWeatherModel> weekWeatherModelArrayList;
    WeekWeatherAdapter weekWeatherAdapter;
    RecyclerView weeklyRecyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_today, container, false);
        cityNameTextView = view.findViewById(R.id.cityNameTextView);
        timeTextView = view.findViewById(R.id.timeTextView);
        maxTempTextView = view.findViewById(R.id.maxTempTextView);
        tempTextView = view.findViewById(R.id.tempTextView);
        feelTextView = view.findViewById(R.id.feelTextView);
        weatherTextView = view.findViewById(R.id.weatherTextView);
        hourlyRecyclerView = view.findViewById(R.id.hourlyRecyclerView);
        weatherModelArrayList = new ArrayList<>();
        weatherAdapter = new WeatherAdapter(getContext(),weatherModelArrayList);
        hourlyRecyclerView.setAdapter(weatherAdapter);

        mainActivity = (Wetherrr) getActivity();
        city = mainActivity.cityName;
        getWeather(city);




        weeklyRecyclerView = view.findViewById(R.id.weeklyRecyclerView);

        weekWeatherModelArrayList = new ArrayList<>();
        weeklyRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        weekWeatherAdapter = new WeekWeatherAdapter(getContext(), weekWeatherModelArrayList);
        weeklyRecyclerView.setAdapter(weekWeatherAdapter);

        mainActivity = (Wetherrr) getActivity();
        city = mainActivity.cityName;
        getWeather2(city);

        return view;
    }

    public void getWeather2(String cityName) {
        String url = "https://api.weatherapi.com/v1/forecast.json?key=5b898ac69b864f1090c62413233107&q=" + cityName + "&days=8&aqi=yes&alerts=yes";

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                weekWeatherModelArrayList.clear();

                try {

                    JSONArray weekJsonArray = response.getJSONObject("forecast").getJSONArray("forecastday");

                    for (int i = 0; i < weekJsonArray.length() && i < 7; i++) {
                        JSONObject weekDetailJsonObj = weekJsonArray.getJSONObject(i).getJSONObject("day");
                        String date = weekJsonArray.getJSONObject(i).getString("date");
                        int maxTemp = (int) Math.round(weekDetailJsonObj.getDouble("maxtemp_c"));
                        int minTemp = (int) Math.round(weekDetailJsonObj.getDouble("mintemp_c"));
                        String icon = weekDetailJsonObj.getJSONObject("condition").getString("icon");
                        String condition = weekDetailJsonObj.getJSONObject("condition").getString("text");
                        if (condition.equals("Patchy rain possible"))
                            condition = "Scattered rain";
                        weekWeatherModelArrayList.add(new WeekWeatherModel(date, maxTemp, minTemp, icon, condition));
                    }

                    weekWeatherAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Please enter a valid City Name", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }

    public void getWeather(String cityName) {
        String url = "https://api.weatherapi.com/v1/forecast.json?key=5b898ac69b864f1090c62413233107&q=" + cityName + "&days=2&aqi=yes&alerts=yes";


        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                cityNameTextView.setText(cityName);
                weatherModelArrayList.clear();

                try {
                    SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                    SimpleDateFormat output = new SimpleDateFormat("dd MMMM, hh:mm aa");
                    String date = response.getJSONObject("location").getString("localtime");
                    try {
                        Date t = input.parse(date);
                        timeTextView.setText(output.format(t));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    int maxTemp = (int) Math.round(response.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(0).getJSONObject("day").getDouble("maxtemp_c"));
                    int minTemp = (int) Math.round(response.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(0).getJSONObject("day").getDouble("mintemp_c"));
                    maxTempTextView.setText("Day " + maxTemp + "° ↑ •  Night " + minTemp + "° ↓");

                    int temp = (int) Math.round(response.getJSONObject("current").getDouble("temp_c"));
                    tempTextView.setText(temp + "°C");

                    int feelsLike = (int) Math.round(response.getJSONObject("current").getDouble("feelslike_c"));
                    feelTextView.setText("Feels like " + feelsLike + "°");



                    String condition = response.getJSONObject("current").getJSONObject("condition").getString("text");
                    weatherTextView.setText(condition);


                    JSONObject forecastObj = response.getJSONObject("forecast");
                    JSONObject forecastP = forecastObj.getJSONArray("forecastday").getJSONObject(0);
                    JSONArray hourArray = forecastP.getJSONArray("hour");

                    String t1 = (date.substring(11, 13));
                    int t2;
                    if (t1.charAt(1) == ':') {
                        t2 = t1.charAt(0) - '0';
                    } else {
                        t2 = Integer.parseInt(t1);
                    }
                    for (int i = t2 + 1; i < hourArray.length(); i++) {
                        JSONObject hourObj = hourArray.getJSONObject(i);
                        String time = hourObj.getString("time");
                        int tempC = (int) Math.round(hourObj.getDouble("temp_c"));
                        String img = hourObj.getJSONObject("condition").getString("icon");
                        int wind = (int) Math.round(hourObj.getDouble("wind_kph"));
                        weatherModelArrayList.add(new WeatherModel(time, tempC, img, wind));
                    }

                    forecastP = forecastObj.getJSONArray("forecastday").getJSONObject(1);
                    hourArray = forecastP.getJSONArray("hour");
                    for (int i = 0; i < t2 + 1; i++) {
                        JSONObject hourObj = hourArray.getJSONObject(i);
                        String time = hourObj.getString("time");
                        int tempC = (int) Math.round(hourObj.getDouble("temp_c"));
                        String img = hourObj.getJSONObject("condition").getString("icon");
                        int wind = (int) Math.round(hourObj.getDouble("wind_kph"));
                        weatherModelArrayList.add(new WeatherModel(time, tempC, img, wind));
                    }
                    weatherAdapter.notifyDataSetChanged();



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Please enter a valid City Name", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }
}
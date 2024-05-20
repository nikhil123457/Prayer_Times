package com.vitcode.iprayertimes.tasbeehcounter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vitcode.iprayertimes.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;

public class History extends BaseActivity implements StartDragListener {
    FloatingActionButton btnAdd;
    TasbihModel model;
    TasbihAdapter tasbihAdapter;
    String[] tasbihCount;
    String[] tasbihName;
    ItemTouchHelper touchHelper;
    boolean undo = false;
    ImageView bt_back;
    ArrayList<TasbihModel> tasbihModelsArrayList = new ArrayList();

    public void onCreate(Bundle bundle2) {
        super.onCreate(bundle2);
        setContentView(R.layout.activity_history);
        this.btnAdd = findViewById(R.id.add);
        this.bt_back =  findViewById(R.id.bt_back);
        addNew();
        int i = this.sharedPreferences.getInt("imgID", 1);
        String string = this.sharedPreferences.getString("vig", "assets://oval/oval.png");
        Context applicationContext = getApplicationContext();
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void addNew() {
        this.btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View view) {
                SmartAlert.select(view.getContext(), "Enter Tasbih Name", "Save", "Cancel", new SmartAlert.SmartAlertListener() {
                    public void result(int i) {
                        if (i == 1) {
                            JSONObject jSONObject = new JSONObject();
                            JSONObject jSONObject2 = new JSONObject();
                            try {
                                jSONObject.put("total_count", 0);
                                jSONObject.put("max", SmartAlert.getTasbihMax());
                                jSONObject.put("sets", 0);
                                jSONObject2.put(SmartAlert.getTasbihName(), jSONObject);
                            } catch (JSONException e) {
                                Toast.makeText(view.getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                            try {
                                JSONObject jSONObject3 = new JSONObject(loadJSONFromAsset());
                                JSONArray jSONArray = jSONObject3.getJSONArray("Tasbeehat");
                                jSONArray.put(jSONObject2);
                                jSONObject3.remove("Tasbeehat");
                                jSONObject3.put("Tasbeehat", jSONArray);
                                String jSONObject4 = jSONObject3.toString();
                                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(getApplicationContext().getFilesDir(), "tasbih.json")));
                                bufferedWriter.write(jSONObject4);
                                bufferedWriter.close();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class).putExtra("tasbihName", SmartAlert.getTasbihName()));
                            } catch (IOException | JSONException e2) {
                                Context context = view.getContext();
                                Toast.makeText(context, e2.getLocalizedMessage() + " :add new", Toast.LENGTH_SHORT).show();
                            }
                            History.this.editor.putString("tasbihName", SmartAlert.getTasbihName()).commit();
                        }
                    }
                });
            }
        });
    }
    //nooo chek

    private void iniliazationOfJSONDatA() {
        try {
            JSONArray jSONArray = new JSONObject(loadJSONFromAsset()).getJSONArray("Tasbeehat");
            int length = jSONArray.length();
            this.tasbihName = new String[length];
            this.tasbihCount = new String[length];
            tasbihModelsArrayList.clear();
            for (int length2 = jSONArray.length() - 1; length2 >= 0; length2--) {
                JSONObject jSONObject = jSONArray.getJSONObject(length2);
                model = new TasbihModel();
                for (String t : iterate(jSONObject.keys())) {
                    model.setTasbihName(t);
                    model.setTasbihCount(jSONObject.getJSONObject(t).getInt("total_count"));
                    model.setTasbihSet(jSONObject.getJSONObject(t).getInt("sets"));
                    model.setTasbihMax(jSONObject.getJSONObject(t).getInt("max"));
                }
                tasbihModelsArrayList.add(model);
            }
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), e.getLocalizedMessage() + " :inital", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        RecyclerView recyclerView = findViewById(R.id.recycler_tasbeehat);
        TasbihAdapter tasbihAdapter2 = new TasbihAdapter(tasbihModelsArrayList, this);
        tasbihAdapter = tasbihAdapter2;
        tasbihAdapter2.setTasbihAdapterListener(intent -> {
        });
        ItemMoveCallback itemMoveCallback = new ItemMoveCallback(tasbihAdapter);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, 12) {
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
                return false;
            }

            public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
                final int adapterPosition = viewHolder.getAdapterPosition();
                final TasbihModel tasbihModel = new TasbihModel();
                tasbihModel.setTasbihName(tasbihModelsArrayList.get(adapterPosition).getTasbihName());
                tasbihModel.setTasbihCount(tasbihModelsArrayList.get(adapterPosition).getTasbihCount());
                tasbihModel.setTasbihSet(tasbihModelsArrayList.get(adapterPosition).getTasbihSet());
                tasbihModel.setTasbihMax(tasbihModelsArrayList.get(adapterPosition).getTasbihSet());
                final int size = (History.this.tasbihModelsArrayList.size() - adapterPosition) - 1;
                History.this.tasbihModelsArrayList.remove(tasbihModelsArrayList.get(adapterPosition));
                tasbihAdapter.notifyDataSetChanged();
                Snackbar make = Snackbar.make(findViewById(R.id.listLayout), "Tasbih Deleted!", 0);
                make.setAction("Undo", new View.OnClickListener() {
                    public void onClick(View view) {
                        tasbihModelsArrayList.add(adapterPosition, tasbihModel);
                        tasbihAdapter.notifyDataSetChanged();
                        History.this.undo = true;
                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                            public void run() {
                            }
                        }, 2750);
                    }
                });
                make.getView().addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                    public void onViewAttachedToWindow(View view) {
                    }

                    public void onViewDetachedFromWindow(View view) {
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                if (!History.this.undo) {
                                    removeTasbih(size);
                                } else {
                                    History.this.undo = false;
                                }
                            }
                        }, 1000);
                    }
                });
                make.show();
            }
        }).attachToRecyclerView(recyclerView);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemMoveCallback);
        this.touchHelper = itemTouchHelper;
        itemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(tasbihAdapter);
    }

    /* access modifiers changed from: private */
    public void removeTasbih(int i) {
        try {
            JSONArray jSONArray = new JSONObject(loadJSONFromAsset()).getJSONArray("Tasbeehat");
            try {
                JSONObject jSONObject = new JSONObject(loadJSONFromAsset());
                jSONObject.getJSONArray("Tasbeehat");
                jSONObject.remove("Tasbeehat");
                jSONObject.put("Tasbeehat", removeJsonObjectAtJsonArrayIndex(jSONArray, i));
                String jSONObject2 = jSONObject.toString();
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(getApplicationContext().getFilesDir(), "tasbih.json")));
                bufferedWriter.write(jSONObject2);
                bufferedWriter.close();
            } catch (IOException | JSONException e) {
                Context applicationContext = getApplicationContext();
                Toast.makeText(applicationContext, e.getLocalizedMessage() + " :remove", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e2) {
            Context applicationContext2 = getApplicationContext();
            Toast.makeText(applicationContext2, e2.getLocalizedMessage() + " :inital", Toast.LENGTH_SHORT).show();
            e2.printStackTrace();
        }
    }

    private <T> Iterable<T> iterate(final Iterator<T> it) {
        return new Iterable<T>() {
            public Iterator<T> iterator() {
                return it;
            }
        };
    }

    public String loadJSONFromAsset() {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(getApplicationContext().getFilesDir(), "tasbih.json"));
            byte[] bArr = new byte[fileInputStream.available()];
            fileInputStream.read(bArr);
            fileInputStream.close();
            return new String(bArr, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void onResume() {
        super.onResume();
        if (this.sharedPreferences.getBoolean("isThemeChange", false)) {
            recreate();
            this.editor.putBoolean("isThemeChange", false).commit();
        }
        iniliazationOfJSONDatA();
    }

    public void requestDrag(RecyclerView.ViewHolder viewHolder) {
        this.touchHelper.startDrag(viewHolder);
    }

    public static JSONArray removeJsonObjectAtJsonArrayIndex(JSONArray jSONArray, int i) throws JSONException {
        if (i < 0 || i > jSONArray.length() - 1) {
            throw new IndexOutOfBoundsException();
        }
        JSONArray jSONArray2 = new JSONArray();
        int length = jSONArray.length();
        for (int i2 = 0; i2 < length; i2++) {
            if (i2 != i) {
                jSONArray2.put(jSONArray.get(i2));
            }
        }
        return jSONArray2;
    }

    public void onBackPressed() {
       super.onBackPressed();
    }
}

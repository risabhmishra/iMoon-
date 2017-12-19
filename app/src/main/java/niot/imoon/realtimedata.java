package niot.imoon;

import android.app.ListActivity;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.jpardogo.android.flabbylistview.lib.FlabbyListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class realtimedata extends Fragment{
    private ListAdapters mAdapter;
    List<String> buoyarray;

    public realtimedata() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_realtimedata, container, false);

        String buoys [] = {"AD06","AD07","AD08","AD09","AD10","BD08","BD09","BD10","BD11","BD12","BD13","BD14","CALVAL","CB01","CB02","CB06"};

        buoyarray = new ArrayList<>(
                Arrays.asList(buoys)
        );

        final ListView listView = (ListView)v.findViewById(R.id.listv);

        mAdapter = new ListAdapters(getActivity().getBaseContext(),buoyarray);

        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(realtimedata.this.getActivity(),buoy_info.class);
                intent.putExtra("buoys",mAdapter.getItem(position));
                startActivity(intent);

            }
        });
        return v;
    }

}
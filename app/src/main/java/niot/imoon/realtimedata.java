package niot.imoon;

import android.app.ListActivity;
import android.app.ListFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        String buoys [] = {"AD02","AD04","CB01","CB02","CB03","CB04","CB05","CB06","CB07","CB08"};

        buoyarray = new ArrayList<>(
                Arrays.asList(buoys)
        );

        ListView listView = (ListView)v.findViewById(R.id.listv);

        mAdapter = new ListAdapters(getActivity().getBaseContext(),buoyarray);

        listView.setAdapter(mAdapter);

        return v;
    }

}
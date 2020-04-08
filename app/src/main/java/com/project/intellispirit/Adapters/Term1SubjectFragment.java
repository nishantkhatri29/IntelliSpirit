package com.project.intellispirit.Adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.intellispirit.R;

import java.util.ArrayList;

public class Term1SubjectFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private Term1Subject1ItemAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_term1_subject,container,false);
        mRecyclerView = view.findViewById(R.id.term1_subject1_recyclerview);

        final ArrayList<Term1Subject1Item> itemList = new ArrayList<>();
        itemList.add(new Term1Subject1Item(R.drawable.book, "Subject 1"));
        itemList.add(new Term1Subject1Item(R.drawable.book, "Subject 2"));
        itemList.add(new Term1Subject1Item(R.drawable.book, "Subject 3"));
        itemList.add(new Term1Subject1Item(R.drawable.book, "Subject 4"));

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new Term1Subject1ItemAdapter(itemList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new Term1Subject1ItemAdapter.OnSubjectItemClickListener() {
            @Override
            public void onItemClick(int position) {
                

                Toast.makeText(getContext(), itemList.get(position).getmText(), Toast.LENGTH_SHORT).show();


            }
        });
        return view;
    }
}

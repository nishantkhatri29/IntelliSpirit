package com.project.intellispirit.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.intellispirit.R;

import java.util.ArrayList;

public class Term1Subject1ItemAdapter extends RecyclerView.Adapter<Term1Subject1ItemAdapter.TestSubjectsItemViewHolder> {
    private ArrayList<Term1Subject1Item> mSubjectList;
    private OnSubjectItemClickListener mListener;
    public interface OnSubjectItemClickListener {
        void onItemClick(int position);
    }

    public static class TestSubjectsItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView;

        public TestSubjectsItemViewHolder(@NonNull View itemView, final OnSubjectItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.test_subject_image);
            mTextView = itemView.findViewById(R.id.test_subject_text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }

    public void setOnItemClickListener(Term1Subject1ItemAdapter.OnSubjectItemClickListener listener) {
        mListener = listener;
    }

    public Term1Subject1ItemAdapter(ArrayList<Term1Subject1Item> subjectlist) {
        mSubjectList = subjectlist;
    }

    @NonNull
    @Override
    public TestSubjectsItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.term1_subject1, parent, false);
        TestSubjectsItemViewHolder viewHolder = new TestSubjectsItemViewHolder(v,mListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TestSubjectsItemViewHolder holder, int position) {
        Term1Subject1Item currentItem =mSubjectList.get(position);
        holder.mImageView.setImageResource(currentItem.getmImageResource());
        holder.mTextView.setText(currentItem.getmText());
    }

    @Override
    public int getItemCount() {
        return mSubjectList.size();
    }
}

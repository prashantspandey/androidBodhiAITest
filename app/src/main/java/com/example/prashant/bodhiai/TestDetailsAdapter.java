package com.example.prashant.bodhiai;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prashant.bodhiai.Activities.BeforeTestActivity;

import java.util.ArrayList;
import java.util.List;

public class TestDetailsAdapter extends RecyclerView.Adapter<TestDetailsAdapter.ViewHolder>{

    private static final String tag = "TestDetailsRecyclerView";
    private ArrayList<String> mSubjects = new ArrayList<>();
    private ArrayList<String> mId = new ArrayList<>();
    private ArrayList<String> mnumQuestions = new ArrayList<>();
    private ArrayList<String> mPublished = new ArrayList<>();
    private List<List<String>> mtopics = new ArrayList<List<String>>();
    private Context mContext;
    View dialogView;
    public TestDetailsAdapter(ArrayList<String> mSubjects, ArrayList<String> mnumQuestions, ArrayList<String> mPublished, List<List<String>> mtopics,ArrayList<String> mId ,Context mContext) {
        this.mSubjects = mSubjects;
        this.mnumQuestions = mnumQuestions;
        this.mPublished = mPublished;
        this.mtopics = mtopics;
        this.mId = mId;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(tag,"onCreateViewHolder called");
        View individual_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_test_details,parent,false);
        //dialogView = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_details_dialog,parent,false);
        return new ViewHolder(individual_view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Log.d(tag,"onBindViewHolder called");
        holder.subject.setText(mSubjects.get(position));
        holder.numQuestions.setText(mnumQuestions.get(position));
        holder.published.setText(mPublished.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,mSubjects.get(position),Toast.LENGTH_SHORT).show();
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(mContext);
                View mView = LayoutInflater.from(mContext).inflate(R.layout.test_details_dialog,null);
                TextView dialogSubject = (TextView) mView.findViewById(R.id.dialog_testDetails_subject);
                TextView dialognumQuestions = (TextView) mView.findViewById(R.id.dialog_testDetails_numQuestions);
                TextView dialogTopics = (TextView) mView.findViewById(R.id.dialog_testDetails_topics);
                Button takeTestButton = (Button)  mView.findViewById(R.id.dialog_testDetails_takeTestButton);
                String thisTopics = mtopics.get(position).toString();
                String nowTopics = thisTopics.replace(",","\n");
                nowTopics = nowTopics.replace("[","");
                nowTopics = nowTopics.replace("]","");
                dialogSubject.setText(mSubjects.get(position));
                dialognumQuestions.setText(mnumQuestions.get(position));
                dialogTopics.setText(nowTopics);
                takeTestButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent testIntent = new Intent(mContext, BeforeTestActivity.class);
                        testIntent.putExtra("test_id",mId.get(position));
                        mContext.startActivity(testIntent);
                    }
                });
                //mBuilder.setTitle(mSubjects.get(position));
                //mBuilder.setMessage(mtopics.get(position).toString());
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });
        holder.subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return mSubjects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView subject;
        TextView numQuestions;
        TextView published;

        public ViewHolder(View itemView) {
            super(itemView);
            subject = itemView.findViewById(R.id.recyclerView_subject);
            numQuestions = itemView.findViewById(R.id.recyclerView_numQuestions);
            published = itemView.findViewById(R.id.recyclerView_published);


        }


    }
}

package com.dg.sigco.card.view.fragment;


import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.dg.sigco.R;
import com.dg.sigco.card.presenter.IUploadPresenter;
import com.dg.sigco.card.presenter.UploadPresenter;
import com.dg.sigco.common.UtilView;
import com.dg.sigco.parameter.presenter.ParameterPresenter;
import com.dg.sigco.warning.view.WarningActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class UploadFragment extends Fragment implements IUploadView {
    private LinearLayout uploadLayout, successLayout;
    private Button uploadCards;
    private IUploadPresenter iUploadPresenter;
    private ParameterPresenter parameterPresenter;
    private ProgressBar progressBar;
    private RelativeLayout progressLayout;
    private ObjectAnimator progressAnimator;
    public UploadFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upload, container, false);
        uploadLayout = view.findViewById(R.id.uploadLayout);
        successLayout = view.findViewById(R.id.successLayout);
        progressLayout = view.findViewById(R.id.progressLayout);
        progressBar = view.findViewById(R.id.progressBar);
        progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", 0, 100);
        iUploadPresenter = new UploadPresenter(this);
        parameterPresenter = new ParameterPresenter();
        uploadCards = view.findViewById(R.id.uploadCards);
        initializeUpload();
        return view;
    }

    private void initializeUpload(){
        parameterPresenter.loadServer();
        uploadCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableProgress();
                iUploadPresenter.uploadCards();
            }
        });
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item = (MenuItem) menu.findItem(R.id.action_search);
        item.setVisible(false);
    }

    @Override
    public void errorConnection() {
        disableProgress();
        Intent intent = new Intent(getActivity(), WarningActivity.class);
        startActivity(intent);
    }

    @Override
    public void afterUpload() {
        disableProgress();
        successLayout.setVisibility(View.VISIBLE);
        uploadLayout.setVisibility(View.GONE);
    }

    private void enableProgress(){
        UtilView.enableProgress(progressAnimator, progressLayout);
        uploadCards.setVisibility(View.GONE);
    }

    private void disableProgress(){
        UtilView.disableProgress(progressAnimator, progressLayout);
        uploadCards.setVisibility(View.VISIBLE);
    }
}
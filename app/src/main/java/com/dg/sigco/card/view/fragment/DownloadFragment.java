package com.dg.sigco.card.view.fragment;


import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
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
import android.widget.Toast;

import com.dg.sigco.R;
import com.dg.sigco.api.service.RetrofitBuilder;
import com.dg.sigco.card.presenter.IDownloadPresenter;
import com.dg.sigco.card.presenter.DownloadPresenterImp;
import com.dg.sigco.common.UtilView;
import com.dg.sigco.parameter.presenter.ParameterPresenter;
import com.dg.sigco.warning.view.WarningActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class DownloadFragment extends Fragment implements IDownloadView{
    private LinearLayout downloadLayout, successLayout, withoutCardsLayout;
    private RelativeLayout progressLayout;
    private IDownloadPresenter presenter;
    private Button downloadCards, goCardsList;
    private ProgressBar progressBar;
    private ParameterPresenter parameterPresenter;
    private ObjectAnimator progressAnimator;
    public DownloadFragment() {
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
        View view = inflater.inflate(R.layout.fragment_download, container, false);
        downloadCards = view.findViewById(R.id.downloadCards);
        progressLayout = view.findViewById(R.id.progressLayout);
        progressBar = view.findViewById(R.id.progressBar);
        progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", 0, 100);
        goCardsList = view.findViewById(R.id.goCardsList);
        downloadLayout = view.findViewById(R.id.downloadLayout);
        successLayout = view.findViewById(R.id.successLayout);
        withoutCardsLayout = view.findViewById(R.id.withoutCardsLayout);
        parameterPresenter = new ParameterPresenter();
        presenter = new DownloadPresenterImp(this);
        initializeDownloadCards();
        initializeGoCardsList();
        loadServer();
        return view;
    }

    private void loadServer(){
        parameterPresenter.loadServer();
    }

    public void initializeGoCardsList(){
        goCardsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                getActivity().startActivity(getActivity().getIntent());
            }
        });
    }

    public void initializeDownloadCards(){
        downloadCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(RetrofitBuilder.URL_REST)){
                    UtilView.showAlertDialog(getActivity(),
                            "Sin servidor configurado",
                            "Ouch!!! No has configurado el servidor de Sigco web. Por favor configuralo en opciones e intenta de nuevo.",
                            false);
                    return;
                }
                enableProgress();
                presenter.downloadCard();
            }
        });
    }

    @Override
    public void showDownloadSuccess(long amount) {
        Toast.makeText(getActivity(), String.format("Se han descargado %d tarjeta(s).", amount), Toast.LENGTH_SHORT).show();
        disableProgress();
        downloadLayout.setVisibility(View.GONE);
        successLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showWithoutCards() {
        downloadLayout.setVisibility(View.GONE);
        withoutCardsLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void connectionError() {
        disableProgress();
        Intent intent = new Intent(getActivity(), WarningActivity.class);
        startActivity(intent);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item = (MenuItem) menu.findItem(R.id.action_search);
        item.setVisible(false);
    }

    private void enableProgress(){
        UtilView.enableProgress(progressAnimator, progressLayout);
        downloadCards.setVisibility(View.GONE);
    }

    private void disableProgress(){
        UtilView.disableProgress(progressAnimator, progressLayout);
        downloadCards.setVisibility(View.VISIBLE);
    }
}
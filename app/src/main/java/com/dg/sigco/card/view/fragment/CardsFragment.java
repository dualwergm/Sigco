package com.dg.sigco.card.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import com.dg.sigco.R;
import com.dg.sigco.card.data.Card;
import com.dg.sigco.card.view.adapter.CardAdapter;
import com.dg.sigco.client.view.ClientsActivity;
import com.dg.sigco.common.Constants;
import com.dg.sigco.common.UserSession;
import com.dg.sigco.line.view.LineActivity;
import com.dg.sigco.login.view.UsersActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardsFragment extends Fragment {
    private View view;
    private RecyclerView cardsRecycler;
    private RecyclerView.LayoutManager lManager;
    private List<Card> cards;
    private CardAdapter adapter;
    private List<Card> copyCards;
    private ScrollView noCardsLayout;
    private FloatingActionButton addCard;
    private CoordinatorLayout coordinatorCard;

    public CardsFragment() {
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
        cards = (List<Card>)getArguments().getSerializable(Constants.CARDS);
        copyCards = new ArrayList<>();
        copyCards.addAll(cards);
        view = inflater.inflate(R.layout.fragment_cards, container, false);
        coordinatorCard = (CoordinatorLayout)view.findViewById(R.id.coordinatorCard);
        cardsRecycler = (RecyclerView) view.findViewById(R.id.cardsRecycler);
        noCardsLayout = (ScrollView) view.findViewById(R.id.noCardsLayout);
        showSectionCard();
        addCard = (FloatingActionButton)view.findViewById(R.id.addCard);
        lManager = new LinearLayoutManager(getContext());
        cardsRecycler.setLayoutManager(lManager);
        adapter = new CardAdapter(cards, getActivity(), R.layout.item_cards);
        cardsRecycler.setAdapter(adapter);
        initializeAddCard();
        return view;
    }

    private void showSectionCard() {
        if(cards.isEmpty()){
            noCardsLayout.setVisibility(View.VISIBLE);
            cardsRecycler.setVisibility(View.GONE);
        }else{
            noCardsLayout.setVisibility(View.GONE);
            cardsRecycler.setVisibility(View.VISIBLE);
        }
    }

    private void initializeAddCard(){
        if(!UserSession.isAdmin){
            addCard.setVisibility(View.GONE);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) coordinatorCard.getLayoutParams();
            layoutParams.bottomMargin = 0;
            coordinatorCard.setLayoutParams(layoutParams);
            return;
        }
        addCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ClientsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item = (MenuItem) menu.findItem(R.id.action_search);
        SearchView searcherView = (SearchView)item.getActionView();
        searcherView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(copyCards, newText);
                return true;
            }
        });
    }
}
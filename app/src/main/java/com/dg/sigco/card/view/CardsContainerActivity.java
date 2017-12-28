package com.dg.sigco.card.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.dg.sigco.R;
import com.dg.sigco.card.data.Card;
import com.dg.sigco.card.presenter.CardContainerPresenterImp;
import com.dg.sigco.card.presenter.ICardContainerPresenter;
import com.dg.sigco.card.repository.DetailRepositoryImp;
import com.dg.sigco.card.view.fragment.CardsFragment;
import com.dg.sigco.card.view.fragment.DownloadFragment;
import com.dg.sigco.card.view.fragment.UploadFragment;
import com.dg.sigco.client.view.ClientsActivity;
import com.dg.sigco.common.Constants;
import com.dg.sigco.common.UserSession;
import com.dg.sigco.login.view.LoginActivity;
import com.dg.sigco.login.view.UsersActivity;
import com.dg.sigco.parameter.view.ServerActivity;
import com.dg.sigco.summary.view.SummaryKtActivity;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.io.Serializable;
import java.util.List;

public class CardsContainerActivity extends AppCompatActivity implements ICardContainerView{
    private Toolbar toolbar;
    private BottomBar bottomBar;
    private ICardContainerPresenter cardContainerPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards_container);
        cardContainerPresenter = new CardContainerPresenterImp(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        bottomBar = (BottomBar) findViewById(R.id.bottombar);
        bottomBar.setDefaultTab(R.id.tabCards);
        toolbar.setTitle(UserSession.name);
        initializeBottomBar();
        setSupportActionBar(toolbar);
    }

    private void initializeBottomBar(){
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId){
                    case R.id.tabDownload:
                        DownloadFragment downloadFragment = new DownloadFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.cardsContainer, downloadFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit();
                        break;
                    case R.id.tabCards:
                        cardContainerPresenter.listCards();
                        break;
                    case R.id.tabUpload:
                        UploadFragment uploadFragment = new UploadFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.cardsContainer, uploadFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit();
                        break;
                      default: break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);
        MenuItem item = (MenuItem)menu.findItem(R.id.action_search);
        validateItems(menu);
        SearchView searcherView = (SearchView)item.getActionView();
        searcherView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void validateItems(Menu menu){
        MenuItem itemUser = menu.findItem(R.id.users);
        MenuItem itemServer = menu.findItem(R.id.server);
        MenuItem itemSummary = menu.findItem(R.id.summary);
        if(!UserSession.isAdmin){
            itemUser.setVisible(false);
            itemServer.setVisible(false);
            itemSummary.setVisible(false);
            bottomBar.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent;
        switch (item.getItemId()) {
            case R.id.users:
                intent = new Intent(this, UsersActivity.class);
                startActivity(intent);
                break;
            case R.id.server:
                intent = new Intent(this, ServerActivity.class);
                startActivity(intent);
                break;
            case R.id.summary:
                intent = new Intent(this, SummaryKtActivity.class);
                startActivity(intent);
                break;
            case R.id.close:
                cardContainerPresenter.logout();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public void showListCards(List<Card> cards) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.CARDS, (Serializable) cards);
        CardsFragment cardsFragment = new CardsFragment();
        cardsFragment.setArguments(bundle);
        goFragment(cardsFragment);
    }

    @Override
    public void showLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra(Constants.USER_LOGGED, "");
        startActivity(intent);
        finish();
    }

    private void goFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.cardsContainer, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null).commit();
    }
}

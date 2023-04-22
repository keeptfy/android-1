package com.freecode.earningwheel.realcashapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdScrollView;
import com.facebook.ads.NativeAdView;
import com.facebook.ads.NativeAdsManager;


public class Activity_Redeem extends AppCompatActivity {
    Button _btnsubmit_;
    EditText et_redeem_no;
    String st_user_no;
    private com.facebook.ads.InterstitialAd interstitialAd;
    private NativeAdsManager manager;
    private NativeAdScrollView nativeAdScrollView;
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.redeem_layout);
        manager = new NativeAdsManager(this, AppAdsHandler.Puzzle_FB_NATIVE_AD_PUB_ID, 5);
        manager.setListener(new NativeAdsManager.Listener() {
            @Override
            public void onAdsLoaded() {
                // Create a NativeAdScrollView instance with a height of 300
                nativeAdScrollView = new NativeAdScrollView(Activity_Redeem.this, manager,
                        NativeAdView.Type.HEIGHT_300);
                LinearLayout hscrollContainer = (LinearLayout) findViewById(R.id.hscrollContainer);
                // Add the NativeAdScrollView to the container
                hscrollContainer.addView(nativeAdScrollView);
            }

            @Override
            public void onAdError(AdError adError) {

            }
        });
        manager.loadAds(NativeAd.MediaCacheFlag.ALL);
        interstitialAd = new com.facebook.ads.InterstitialAd(this, AppAdsHandler.Puzzle_FB_INTRESTITIAL_AD_PUB_ID);

        interstitialAd.setAdListener(new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {

            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                finish();
                interstitialAd.loadAd();
            }

            @Override
            public void onError(Ad ad, AdError adError) {

            }

            @Override
            public void onAdLoaded(Ad ad) {
                // interstitialAd.show();
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        });

        // Load a new interstitial.
        interstitialAd.loadAd();
        AppAdsHandler.loadAdmobBannerAds((com.google.android.gms.ads.AdView) findViewById(R.id.adView_reddem));
        this.et_redeem_no = (EditText) findViewById(R.id.redeem_mob_number);
        this._btnsubmit_ = (Button) findViewById(R.id.redeem_submit);
        final int i = getSharedPreferences("your_prefs", 0).getInt("your_int_key", -1);
        this._btnsubmit_.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Activity_Redeem.this.st_user_no = Activity_Redeem.this.et_redeem_no.getText().toString();
                if (TextUtils.isEmpty(Activity_Redeem.this.st_user_no)) {
                    Toast.makeText(Activity_Redeem.this, "Fill The Detail", Toast.LENGTH_SHORT).show();
                } else if (i > 20000) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Activity_Redeem.this);
                    builder.setTitle("Congratulation !,PayTm Money ");
                    builder.setMessage("You Money Credited Within 48 Hours");
                    builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(Activity_Redeem.this, "Congrets..", Toast.LENGTH_LONG).show();
                            Activity_Redeem.this.finish();
                        }
                    });
                    builder.create().show();
                } else {
                    Toast.makeText(Activity_Redeem.this, "You need minimum 20,000 points", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void onBackPressed() {
        if (interstitialAd == null || !interstitialAd.isAdLoaded()) {
            super.onBackPressed();
        } else {
            // Ad was loaded, show it!
            interstitialAd.show();
        }

    }


}

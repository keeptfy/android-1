package com.freecode.earningwheel.realcashapp;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.ads.AdError;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdScrollView;
import com.facebook.ads.NativeAdView;
import com.facebook.ads.NativeAdsManager;


import net.khirr.android.privacypolicy.PrivacyPolicyDialog;



public class Activity_Main extends AppCompatActivity {
    AdView adView;
    LinearLayout main_rating;
    LinearLayout main_redeem;
    LinearLayout main_share;
    LinearLayout main_spin;
    int myIntValue;
    TextView tv_score;
    boolean doubleBackToExitPressedOnce = false;
    private NativeAdsManager manager;
    private NativeAdScrollView nativeAdScrollView;
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.home_layout);
        Dash_Board_User_Team();
        manager = new NativeAdsManager(this, AppAdsHandler.Puzzle_FB_NATIVE_AD_PUB_ID, 5);
        manager.setListener(new NativeAdsManager.Listener() {
            @Override
            public void onAdsLoaded() {
                // Create a NativeAdScrollView instance with a height of 300
                nativeAdScrollView = new NativeAdScrollView(Activity_Main.this, manager,
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

        showbnrads();
        this.main_spin = (LinearLayout) findViewById(R.id.ll_spin);
        this.main_redeem = (LinearLayout) findViewById(R.id.ll_redeem);
        this.tv_score = (TextView) findViewById(R.id.wallet_text_score_id);
        this.main_rating = (LinearLayout) findViewById(R.id.ll_rate);
        this.main_share = (LinearLayout) findViewById(R.id.ll_share);
        this.myIntValue = getSharedPreferences("your_prefs", 0).getInt("your_int_key", 0);
        this.main_spin.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Activity_Main.this, Activity_Spin_Wheel.class);
                intent.putExtra("INT", Activity_Main.this.myIntValue);
                Activity_Main.this.startActivity(intent);
            }
        });
        this.main_rating.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("https://play.google.com/store/apps/details?id=");
                stringBuilder.append(Activity_Main.this.getPackageName());
                Activity_Main.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(stringBuilder.toString())));
            }
        });
        this.main_redeem.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Activity_Main.this.startActivity(new Intent(Activity_Main.this, Activity_Redeem.class));
            }
        });
        this.main_share.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("text/plain");
                intent.putExtra("android.intent.extra.SUBJECT", R.string.app_name);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("\nLet me recommend you this application\n\n");
                stringBuilder.append("https://play.google.com/store/apps/details?id=");
                stringBuilder.append(Activity_Main.this.getPackageName());
                intent.putExtra("android.intent.extra.TEXT", stringBuilder.toString());
                Activity_Main.this.startActivity(Intent.createChooser(intent, "choose one"));
            }
        });
    }


    private void Dash_Board_User_Team() {
        PrivacyPolicyDialog privacyPolicyDialog = new PrivacyPolicyDialog(this, AppAdsHandler.privacy_policy, AppAdsHandler.privacy_policy);
        privacyPolicyDialog.setOnClickListener(new PrivacyPolicyDialog.OnClickListener() {
            @Override
            public void onAccept(boolean isFirstTime) {
            }

            @Override
            public void onCancel() {
                finish();
            }
        });
        privacyPolicyDialog.addPoliceLine("We DO NOT collect or gather any personal information while you visit, download or upgrade any application, your personal information like your first name and last name, physical addresses, telephone numbers, fax numbers and information stored within your device. We will not collect or store your Personal Information and we will not use, transfer or disclose your Personal Information, excepting the personal information that you submit to us when you create a user account, send an error report or participate in online surveys and other activities. In the following circumstances, we may disclose your personal information according to your wish or regulations by law:");
        privacyPolicyDialog.addPoliceLine("Protecting your privacy is important to us. We hope the following statement will help you understand how ceaapp deals with the personal identifiable information ('PII') you may occasionally provide to us via Internet (the'Google Play'Platform)");
        privacyPolicyDialog.addPoliceLine("1. Your prior permission.");
        privacyPolicyDialog.addPoliceLine("2. By the applicable law within or outside your country of residence, legal process, litigation requests.");
        privacyPolicyDialog.addPoliceLine("3. By requests from public and governmental authorities.");
        privacyPolicyDialog.addPoliceLine("2. Ad Networks and Cross Promotion Ads\n\nWe welcome the third parties ad networks for accepting advertisements (banners, interstitials and video ads etc…). In our apps and games, these advertisements authorized to be displayed. Advertisers may use cookies and other web-tracking technologies to collect data, in case that user clicks on any of these advertisements.\n\nWe promote third parties games, apps and services in different types of ways. That might include cross promoting of third parties games or ceaapp while you are using a different games or apps of ours.\n\nWe display ads to cross promote apps and games of third parties.\n\nWe do not gather or kidsdrawing_share any of your personal identification information to display ads.");
        privacyPolicyDialog.addPoliceLine("Privacy Policy Changes.");
        privacyPolicyDialog.addPoliceLine("Our Privacy Policy may change from time to time, we will post any privacy policy changes on this page, so please review it periodically. We may provide you additional forms of notice of modifications or updates as appropriate under the circumstances.");
        privacyPolicyDialog.setTitleTextColor(Color.parseColor("#222222"));
        privacyPolicyDialog.setAcceptButtonColor(ContextCompat.getColor(this, R.color.colorPrimary));
        privacyPolicyDialog.setTitle("Terms of Service");
        privacyPolicyDialog.setTermsOfServiceSubtitle("If you click on {accept}, you acknowledge that it makes the content present and all the content of our {terms}Terms of Service{/terms} and implies that you have read our {privacy}Privacy Policy{privacy}.");
        privacyPolicyDialog.show();
    }




    public void showbnrads() {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.fb_bnr_container_toolbar);
        this.adView = new AdView(this, AppAdsHandler.Puzzle_FB_BANNER_AD_PUB_ID, AdSize.BANNER_HEIGHT_50);
        relativeLayout.addView(this.adView);
        this.adView.loadAd();
    }

    public void finish() {
        super.finish();
        finish();
    }

    protected void onStart() {
        super.onStart();
        String valueOf = String.valueOf(this.myIntValue);
        TextView textView = this.tv_score;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(valueOf);
        stringBuilder.append(" Point");
        textView.setText(stringBuilder.toString());
    }
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}

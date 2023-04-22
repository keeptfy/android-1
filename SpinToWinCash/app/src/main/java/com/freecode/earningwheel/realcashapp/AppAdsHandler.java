package com.freecode.earningwheel.realcashapp;

import android.content.Context;
import android.os.Handler;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.kaopiz.kprogresshud.KProgressHUD;

public class AppAdsHandler {


    public static String privacy_policy = "https://sites.google.com/view/free-spin-and-coin-daily-link/home";
    public static String Puzzle_FB_INTRESTITIAL_AD_PUB_ID = "IMG_16_9_APP_INSTALL#182230885712915_182234475712556";
    public static String Puzzle_FB_NATIVE_AD_PUB_ID = "IMG_16_9_APP_INSTALL#182230885712915_182234475712556";
    public static String Puzzle_FB_BANNER_AD_PUB_ID = "IMG_16_9_APP_INSTALL#182230885712915_182234475712556";


    private static Context puzzle_ctx;
    private static KProgressHUD admob_loader;
    private static KProgressHUD fb_loader;

    public static void loadAdmobBannerAds(AdView adView) {

        adView.loadAd(new AdRequest.Builder().build());
    }


    public static void loadAdmobInterstitialAds(Context context) {
        final com.google.android.gms.ads.InterstitialAd interstitialAd = new com.google.android.gms.ads.InterstitialAd(context);
        puzzle_ctx = context;
        interstitialAd.setAdUnitId(context.getResources().getString(R.string.admob_int));
        interstitialAd.loadAd(new AdRequest.Builder().build());
        interstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                AppAdsHandler.showAdmobInterstitial(interstitialAd);
            }
        });
    }

    private static void showAdmobInterstitial(final com.google.android.gms.ads.InterstitialAd interstitialAd) {
        if (interstitialAd.isLoaded()) {
            try {
                admob_loader = KProgressHUD.create(puzzle_ctx).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setLabel("Showing Ad").setCancellable(false);
                admob_loader.show();
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        interstitialAd.show();
                        if (AppAdsHandler.admob_loader != null) {
                            AppAdsHandler.admob_loader.dismiss();
                        }
                    }
                }, 400);
            } catch (Exception unused) {
                admob_loader = null;
            }
        }
    }


    public static void loadFBInterstitialAds(Context context) {
        final com.facebook.ads.InterstitialAd fbinterstitialAd = new com.facebook.ads.InterstitialAd(context, Puzzle_FB_INTRESTITIAL_AD_PUB_ID);
        puzzle_ctx = context;
        fbinterstitialAd.loadAd();
        fbinterstitialAd.setAdListener(new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
            }

            @Override
            public void onError(Ad ad, AdError adError) {
            }

            @Override
            public void onAdLoaded(Ad ad) {
                AppAdsHandler.showFBInterstitial(fbinterstitialAd);
            }

            @Override
            public void onAdClicked(Ad ad) {
            }

            @Override
            public void onLoggingImpression(Ad ad) {
            }
        });
    }

    private static void showFBInterstitial(final com.facebook.ads.InterstitialAd fbinterstitialAd) {
        if (fbinterstitialAd.isAdLoaded()) {
            try {
                fb_loader = KProgressHUD.create(puzzle_ctx).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setLabel("Showing Ad").setCancellable(false);
                fb_loader.show();
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        fbinterstitialAd.show();
                        if (AppAdsHandler.fb_loader != null) {
                            AppAdsHandler.fb_loader.dismiss();
                        }
                    }
                }, 400);
            } catch (Exception unused) {
                fb_loader = null;
            }
        }
    }
}

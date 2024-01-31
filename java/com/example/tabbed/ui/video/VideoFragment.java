package com.example.tabbed.ui.video;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.tabbed.R;
import com.example.tabbed.databinding.FragmentVideoBinding;

public class VideoFragment extends Fragment {

private FragmentVideoBinding binding;

   private WebView mywebView;
   public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

    binding = FragmentVideoBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

       //setContentView(R.layout.);
       mywebView=(WebView) root.findViewById(R.id.webview);
       mywebView.setWebViewClient(new WebViewClient());
       mywebView.loadUrl("https://m.youtube.com/results?sp=mAEA&search_query=Airlines+videos");
       WebSettings webSettings=mywebView.getSettings();
       webSettings.setJavaScriptEnabled(true);
       return root;
    }

}
package com.example.module_video.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0003J\b\u0010\u001a\u001a\u00020\u0019H\u0003J\b\u0010\u001b\u001a\u00020\u0019H\u0016J\u0012\u0010\u001c\u001a\u00020\u00192\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014J\b\u0010\u001f\u001a\u00020\u0019H\u0014J\b\u0010 \u001a\u00020\u0019H\u0014J\b\u0010!\u001a\u00020\u0019H\u0014J\u0010\u0010\"\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u0017H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u000e\u0010\u000fR\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0015\u0010\u000b\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Lcom/example/module_video/ui/VideoActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "isLiked", "", "isStared", "mAdapter", "Lcom/example/module_video/adapter/OthersAdapter;", "getMAdapter", "()Lcom/example/module_video/adapter/OthersAdapter;", "mAdapter$delegate", "Lkotlin/Lazy;", "mBinding", "Lcom/example/module_video/databinding/ActivityVideoBinding;", "getMBinding", "()Lcom/example/module_video/databinding/ActivityVideoBinding;", "mBinding$delegate", "mOthersViewModel", "Lcom/example/module_video/viewmodel/OthersViewModel;", "getMOthersViewModel", "()Lcom/example/module_video/viewmodel/OthersViewModel;", "mOthersViewModel$delegate", "url", "", "initEvent", "", "initView", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPause", "onResume", "playVideo", "module_video_debug"})
@com.alibaba.android.arouter.facade.annotation.Route(path = "/video/VideoActivity")
public final class VideoActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy mBinding$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy mOthersViewModel$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy mAdapter$delegate = null;
    private java.lang.String url;
    private boolean isLiked = false;
    private boolean isStared = false;
    
    public VideoActivity() {
        super();
    }
    
    private final com.example.module_video.databinding.ActivityVideoBinding getMBinding() {
        return null;
    }
    
    private final com.example.module_video.viewmodel.OthersViewModel getMOthersViewModel() {
        return null;
    }
    
    private final com.example.module_video.adapter.OthersAdapter getMAdapter() {
        return null;
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    private final void initView() {
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    private final void initEvent() {
    }
    
    private final void playVideo(java.lang.String url) {
    }
    
    @java.lang.Override
    protected void onPause() {
    }
    
    @java.lang.Override
    protected void onResume() {
    }
    
    @java.lang.Override
    protected void onDestroy() {
    }
    
    @java.lang.Override
    public void onBackPressed() {
    }
}
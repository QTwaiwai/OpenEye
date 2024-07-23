// Generated by view binder compiler. Do not edit!
package com.example.module_video.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.module_video.R;
import com.google.android.material.appbar.AppBarLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import xyz.doikki.videoplayer.player.VideoView;

public final class ActivityVideoBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final AppBarLayout appbar;

  @NonNull
  public final ImageView ivLikes;

  @NonNull
  public final ImageView ivShare;

  @NonNull
  public final ImageView ivStar;

  @NonNull
  public final ConstraintLayout main;

  @NonNull
  public final RecyclerView rvOthers;

  @NonNull
  public final Toolbar toolbar;

  @NonNull
  public final TextView tvAuthor;

  @NonNull
  public final TextView tvDescription;

  @NonNull
  public final TextView tvLikes;

  @NonNull
  public final TextView tvRelated;

  @NonNull
  public final TextView tvStar;

  @NonNull
  public final TextView tvTag;

  @NonNull
  public final TextView tvTitle;

  @NonNull
  public final VideoView video;

  private ActivityVideoBinding(@NonNull ConstraintLayout rootView, @NonNull AppBarLayout appbar,
      @NonNull ImageView ivLikes, @NonNull ImageView ivShare, @NonNull ImageView ivStar,
      @NonNull ConstraintLayout main, @NonNull RecyclerView rvOthers, @NonNull Toolbar toolbar,
      @NonNull TextView tvAuthor, @NonNull TextView tvDescription, @NonNull TextView tvLikes,
      @NonNull TextView tvRelated, @NonNull TextView tvStar, @NonNull TextView tvTag,
      @NonNull TextView tvTitle, @NonNull VideoView video) {
    this.rootView = rootView;
    this.appbar = appbar;
    this.ivLikes = ivLikes;
    this.ivShare = ivShare;
    this.ivStar = ivStar;
    this.main = main;
    this.rvOthers = rvOthers;
    this.toolbar = toolbar;
    this.tvAuthor = tvAuthor;
    this.tvDescription = tvDescription;
    this.tvLikes = tvLikes;
    this.tvRelated = tvRelated;
    this.tvStar = tvStar;
    this.tvTag = tvTag;
    this.tvTitle = tvTitle;
    this.video = video;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityVideoBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityVideoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_video, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityVideoBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.appbar;
      AppBarLayout appbar = ViewBindings.findChildViewById(rootView, id);
      if (appbar == null) {
        break missingId;
      }

      id = R.id.iv_likes;
      ImageView ivLikes = ViewBindings.findChildViewById(rootView, id);
      if (ivLikes == null) {
        break missingId;
      }

      id = R.id.iv_share;
      ImageView ivShare = ViewBindings.findChildViewById(rootView, id);
      if (ivShare == null) {
        break missingId;
      }

      id = R.id.iv_star;
      ImageView ivStar = ViewBindings.findChildViewById(rootView, id);
      if (ivStar == null) {
        break missingId;
      }

      ConstraintLayout main = (ConstraintLayout) rootView;

      id = R.id.rv_others;
      RecyclerView rvOthers = ViewBindings.findChildViewById(rootView, id);
      if (rvOthers == null) {
        break missingId;
      }

      id = R.id.toolbar;
      Toolbar toolbar = ViewBindings.findChildViewById(rootView, id);
      if (toolbar == null) {
        break missingId;
      }

      id = R.id.tv_author;
      TextView tvAuthor = ViewBindings.findChildViewById(rootView, id);
      if (tvAuthor == null) {
        break missingId;
      }

      id = R.id.tv_description;
      TextView tvDescription = ViewBindings.findChildViewById(rootView, id);
      if (tvDescription == null) {
        break missingId;
      }

      id = R.id.tv_likes;
      TextView tvLikes = ViewBindings.findChildViewById(rootView, id);
      if (tvLikes == null) {
        break missingId;
      }

      id = R.id.tv_related;
      TextView tvRelated = ViewBindings.findChildViewById(rootView, id);
      if (tvRelated == null) {
        break missingId;
      }

      id = R.id.tv_star;
      TextView tvStar = ViewBindings.findChildViewById(rootView, id);
      if (tvStar == null) {
        break missingId;
      }

      id = R.id.tv_tag;
      TextView tvTag = ViewBindings.findChildViewById(rootView, id);
      if (tvTag == null) {
        break missingId;
      }

      id = R.id.tv_title;
      TextView tvTitle = ViewBindings.findChildViewById(rootView, id);
      if (tvTitle == null) {
        break missingId;
      }

      id = R.id.video;
      VideoView video = ViewBindings.findChildViewById(rootView, id);
      if (video == null) {
        break missingId;
      }

      return new ActivityVideoBinding((ConstraintLayout) rootView, appbar, ivLikes, ivShare, ivStar,
          main, rvOthers, toolbar, tvAuthor, tvDescription, tvLikes, tvRelated, tvStar, tvTag,
          tvTitle, video);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}

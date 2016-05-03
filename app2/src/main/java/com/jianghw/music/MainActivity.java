package com.jianghw.music;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.jianghw.music.bean.SongDetailBean;
import com.jianghw.music.manager.MusicPreferences;
import com.jianghw.music.manager.MyMediaController;
import com.jianghw.music.view.allsongs.FragmentAllSongs;
import com.jianghw.music.xutil.AddFgtUtils;
import com.jianghw.music.xutil.AppUtils;

/**
 * @Description: </b>TODO<br/>
 * @Author: </b>jianghw<br>
 * @Since: </b>2016/4/28<br>
 * @See {@link}
 * @Github {@https://github.com/jianghw/Demo_1.1}
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";
    private int currentPosition = 0;//当前页面fragment
    private FragmentAllSongs mFragmentAllSongs;
    private MainActivity mActivity;

    private void initCurrentTheme() {
        AppUtils.settingCurrentTheme(1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.mActivity = MainActivity.this;
        //初始化当前主题
        initCurrentTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        if (drawer != null) {
            drawer.addDrawerListener(toggle);
        }
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }

        initCurrentFragment();
        getIntentData();
    }

    /**
     * 初始化fragment页面
     */
    private void initCurrentFragment() {
        AddFgtUtils.addFragmentOnly(
                getSupportFragmentManager(),
                getFragmentItem(currentPosition),
                R.id.fl_content_main,
                String.valueOf(currentPosition));
    }

    private Fragment getFragmentItem(int currentPosition) {
        switch (currentPosition) {
            case 0:
                if (mFragmentAllSongs == null)
                    mFragmentAllSongs = FragmentAllSongs.newInstance();
                return mFragmentAllSongs;
            default:
                return getFragmentItem(0);
        }
    }

    /**
     * Get intent data from music choose option
     */
    private void getIntentData() {
        Uri data = getIntent().getData();
        if (data != null) {
            //.getSchema()可以返回当前页面使用的协议，http 或是 https;
            if (data.getScheme().equalsIgnoreCase("file")) {
                String path = data.getPath();
                if (!TextUtils.isEmpty(path)) {
                    MyMediaController.getInstance().cleanUpPlayer(true, true);

                    MusicPreferences.getPlaylist(path);
                    updateTitle(false);
                    MyMediaController.getInstance().playAudio(MusicPreferences.songDetailBean);
                }
            }
            if (data.getScheme().equalsIgnoreCase("http"))
                Log.i(TAG, data.getPath());
            if (data.getScheme().equalsIgnoreCase("content"))
                Log.i(TAG, data.getPath());
        }
    }

    private void updateTitle(boolean shutdown) {
        SongDetailBean mSongDetail = MyMediaController.getInstance().getPlayingSongDetail();
        if (mSongDetail == null && shutdown) {
            return;
        } else {
            updateProgress(mSongDetail);
            if (MyMediaController.getInstance().isAudioPaused()) {
               /* btn_playpausePanel.Pause();
                btn_playpause.Pause();*/
            } else {
              /*  btn_playpausePanel.Play();
                btn_playpause.Play();*/
            }
            SongDetailBean audioInfo = MyMediaController.getInstance().getPlayingSongDetail();
            loadSongsDetails(audioInfo);

          /*  if (txt_timetotal != null) {
                long duration = Long.valueOf(audioInfo.getDuration());
                txt_timetotal.setText(duration != 0 ? String.format("%d:%02d", duration / 60, duration % 60) : "-:--");
            }*/
        }
    }

    private void updateProgress(SongDetailBean songDetailBean) {
      /*  if (audio_progress != null) {
            // When SeekBar Draging Don't Show Progress
            if (!isDragingStart) {
                // Progress Value comming in point it range 0 to 1
                audio_progress.setValue((int) (songDetailBean.audioProgress * 100));
            }
            String timeString = String.format("%d:%02d", songDetailBean.audioProgressSec / 60, songDetailBean.audioProgressSec % 60);
            txt_timeprogress.setText(timeString);
        }*/
    }

    private void loadSongsDetails(SongDetailBean songDetailBean) {
    /*    String contentURI = "content://media/external/audio/media/" + mDetail.getId() + "/albumart";
        imageLoader.displayImage(contentURI, songAlbumbg, options, animateFirstListener);
        imageLoader.displayImage(contentURI, img_bottom_slideone, options, animateFirstListener);
        imageLoader.displayImage(contentURI, img_bottom_slidetwo, options, animateFirstListener);

        txt_playesongname.setText(mDetail.getTitle());
        txt_songartistname.setText(mDetail.getArtist());
        txt_playesongname_slidetoptwo.setText(mDetail.getTitle());
        txt_songartistname_slidetoptwo.setText(mDetail.getArtist());

        if (txt_timetotal != null) {
            long duration = Long.valueOf(mDetail.getDuration());
            txt_timetotal.setText(duration != 0 ? String.format("%d:%02d", duration / 60, duration % 60) : "-:--");
        }*/
        updateProgress(songDetailBean);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            drawer.closeDrawer(GravityCompat.START);
        }
        return true;
    }
}

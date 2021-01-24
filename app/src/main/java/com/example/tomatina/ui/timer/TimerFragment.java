package com.example.tomatina.ui.timer;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProvider;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tomatina.R;

import java.util.Locale;

public class TimerFragment extends Fragment {

    private static final long START_TIME_IN_MILLIS = 1500000;

    private TextView mTextViewCountDown;
    private Button mButtonStartPause;
    private Button mButtonReset;
    private TextView infoBox;
    ViewGroup tContainer;

    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;

    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    private TimerViewModel timerViewModel;

    public TimerFragment() {
    }

    public TimerFragment(TextView mTextViewCountDown, Button mButtonStartPause) {
        this.mTextViewCountDown = mTextViewCountDown;
        this.mButtonStartPause = mButtonStartPause;
        this.mButtonReset = mButtonReset;
        this.infoBox = infoBox;
    }

    public static TimerFragment newInstance() {
        return new TimerFragment();
    }

    private TimerViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer, container, false);

        //-------------------------------------------------
        timerViewModel = new ViewModelProvider(this).get(TimerViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        // List Array of Strings
        String[] focusGroups = {
                "Martin           " + "                 " + "27",
                "Robert           " + "                 " + "3",
                "Isi              " + "                 " + " 15",
        };

        ListView lw = (ListView) view.findViewById(R.id.users);

        // this adapter is needed so that listView knows what to display
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_expandable_list_item_1,
                focusGroups
        );
        lw.setAdapter(listViewAdapter);

//---------------------------------------------------------------------------

        mTextViewCountDown = view.findViewById(R.id.text_view_countdown);
        mButtonStartPause = view.findViewById(R.id.button_start_pause);
        mButtonReset = view.findViewById(R.id.button_reset);

        infoBox = view.findViewById(R.id.textView5);
        tContainer = view.findViewById(R.id.fragment_container_view_tag);
        final boolean[] visible = new boolean[1];

        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            // visibility here is gone (0).
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(tContainer);
                // on button click first show space to textView
                // make the visibility from gone to invisible
                visible[0] = !visible[0];
                //after making space
                //if textView is gone make it visible, if visible make it gone
                infoBox.setVisibility(visible[0] ? View.VISIBLE : View.GONE);

            if(mTimerRunning)

            {
                pauseTimer();
                resetTimer();
            } else

            {
                startTimer();

            }
            }
        });

        mButtonReset.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                resetTimer();

            }
        });
        updateCountDownText();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TimerViewModel.class);
        // TODO: Use the ViewModel
    }


    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                mButtonStartPause.setText("Start");
                mButtonReset.setVisibility(View.VISIBLE);

            }
        }.start();

        mTimerRunning = true;
        mButtonStartPause.setText("Stop");

    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        mButtonStartPause.setText("Start");
        mButtonReset.setVisibility(View.VISIBLE);
    }

    private void resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        mButtonReset.setVisibility(View.INVISIBLE);
        mButtonStartPause.setVisibility(View.VISIBLE);
    }


    public void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) mTimeLeftInMillis / 1000 % 60;

        // Convert to a Time String --
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        mTextViewCountDown.setText(timeLeftFormatted);
    }
}
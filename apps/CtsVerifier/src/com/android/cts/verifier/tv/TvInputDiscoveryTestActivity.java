/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.cts.verifier.tv;

import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.media.tv.TvContract;
import android.os.AsyncTask;
import android.view.View;

import com.android.cts.verifier.R;

/**
 * Tests for verifying TV app behavior for third-party TV input apps.
 */
public class TvInputDiscoveryTestActivity extends TvAppVerifierActivity
        implements View.OnClickListener {
    private static final String TAG = "TvInputDiscoveryTestActivity";

    private static final Intent TV_APP_INTENT = new Intent(Intent.ACTION_VIEW,
            TvContract.Channels.CONTENT_URI);
    private static final Intent EPG_INTENT = new Intent(Intent.ACTION_VIEW,
            TvContract.Programs.CONTENT_URI);

    private static final long TIMEOUT_MS = 5l * 60l * 1000l;  // 5 mins.

    private View mGoToSetupItem;
    private View mVerifySetupItem;
    private View mTuneToChannelItem;
    private View mVerifyTuneItem;
    private View mVerifyOverlayViewItem;
    private View mVerifyGlobalSearchItem;
    private View mGoToEpgItem;
    private View mVerifyEpgItem;
    private boolean mTuneVerified;
    private boolean mOverlayViewVerified;
    private boolean mGlobalSearchVerified;

    @Override
    public void onClick(View v) {
        final View postTarget = getPostTarget();

        if (containsButton(mGoToSetupItem, v)) {
            final Runnable failCallback = new Runnable() {
                @Override
                public void run() {
                    setPassState(mVerifySetupItem, false);
                }
            };
            postTarget.postDelayed(failCallback, TIMEOUT_MS);
            MockTvInputSetupActivity.expectLaunch(postTarget, new Runnable() {
                @Override
                public void run() {
                    postTarget.removeCallbacks(failCallback);
                    setPassState(mGoToSetupItem, true);
                    setPassState(mVerifySetupItem, true);
                    setButtonEnabled(mTuneToChannelItem, true);
                }
            });
            startActivity(TV_APP_INTENT);
        } else if (containsButton(mTuneToChannelItem, v)) {
            final Runnable failCallback = new Runnable() {
                @Override
                public void run() {
                    setPassState(mVerifyTuneItem, false);
                }
            };
            postTarget.postDelayed(failCallback, TIMEOUT_MS);
            MockTvInputService.expectTune(postTarget, new Runnable() {
                @Override
                public void run() {
                    setPassState(mTuneToChannelItem, true);
                    setPassState(mVerifyTuneItem, true);

                    mTuneVerified = true;
                    goToNextState(postTarget, failCallback);
                }
            });
            MockTvInputService.expectOverlayView(postTarget, new Runnable() {
                @Override
                public void run() {
                    postTarget.removeCallbacks(failCallback);
                    setPassState(mVerifyOverlayViewItem, true);

                    mOverlayViewVerified = true;
                    goToNextState(postTarget, failCallback);
                }
            });
            verifyGlobalSearch(postTarget, failCallback);
            startActivity(TV_APP_INTENT);
        } else if (containsButton(mGoToEpgItem, v)) {
            startActivity(EPG_INTENT);
            setPassState(mGoToEpgItem, true);
            setButtonEnabled(mVerifyEpgItem, true);
        } else if (containsButton(mVerifyEpgItem, v)) {
            setPassState(mVerifyEpgItem, true);
            getPassButton().setEnabled(true);
        }
    }

    @Override
    protected void createTestItems() {
        mGoToSetupItem = createUserItem(R.string.tv_input_discover_test_go_to_setup,
                R.string.tv_launch_tv_app, this);
        setButtonEnabled(mGoToSetupItem, true);
        mVerifySetupItem = createAutoItem(R.string.tv_input_discover_test_verify_setup);
        mTuneToChannelItem = createUserItem(R.string.tv_input_discover_test_tune_to_channel,
                R.string.tv_launch_tv_app, this);
        mVerifyTuneItem = createAutoItem(R.string.tv_input_discover_test_verify_tune);
        mVerifyOverlayViewItem = createAutoItem(
                R.string.tv_input_discover_test_verify_overlay_view);
        mVerifyGlobalSearchItem = createAutoItem(
                R.string.tv_input_discover_test_verify_global_search);
        mGoToEpgItem = createUserItem(R.string.tv_input_discover_test_go_to_epg,
                R.string.tv_launch_epg, this);
        mVerifyEpgItem = createUserItem(R.string.tv_input_discover_test_verify_epg,
                android.R.string.yes, this);
    }

    @Override
    protected void setInfoResources() {
        setInfoResources(R.string.tv_input_discover_test,
                R.string.tv_input_discover_test_info, -1);
    }

    private void goToNextState(View postTarget, Runnable failCallback) {
        if (mTuneVerified && mOverlayViewVerified && mGlobalSearchVerified) {
            postTarget.removeCallbacks(failCallback);
            setButtonEnabled(mGoToEpgItem, true);
        }
    }

    private void verifyGlobalSearch(final View postTarget, final Runnable failCallback) {
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... params) {
                Context context = TvInputDiscoveryTestActivity.this;
                for (SearchableInfo info : SearchUtil.getSearchableInfos(context)) {
                    if (SearchUtil.verifySearchResult(context, info,
                            MockTvInputSetupActivity.CHANNEL_NAME,
                            MockTvInputSetupActivity.PROGRAM_TITLE)
                            && SearchUtil.verifySearchResult(context, info,
                                    MockTvInputSetupActivity.PROGRAM_TITLE,
                                    MockTvInputSetupActivity.PROGRAM_TITLE)) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            protected void onPostExecute(Boolean result) {
                super.onPostExecute(result);
                setPassState(mVerifyGlobalSearchItem, result);
                mGlobalSearchVerified = result;
                goToNextState(postTarget, failCallback);
            }
        }.execute();
    }
}

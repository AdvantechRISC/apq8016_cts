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

package android.media.tv.cts;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.tv.TvContract;
import android.media.tv.TvInputInfo;
import android.media.tv.TvInputManager;
import android.os.Parcel;
import android.test.AndroidTestCase;

/**
 * Test for {@link android.media.tv.TvInputInfo}.
 */
public class TvInputInfoTest extends AndroidTestCase {
    private TvInputInfo mStubInfo;
    private PackageManager mPackageManager;

    @Override
    public void setUp() throws Exception {
        if (!Utils.hasTvInputFramework(getContext())) {
            return;
        }
        TvInputManager manager =
                (TvInputManager) getContext().getSystemService(Context.TV_INPUT_SERVICE);
        for (TvInputInfo info : manager.getTvInputList()) {
            if (info.getServiceInfo().name.equals(
                    StubTunerTvInputService.class.getName())) {
                mStubInfo = info;
                break;
            }
        }
        mPackageManager = getContext().getPackageManager();
    }

    public void testTvInputInfoOp() throws Exception {
        if (!Utils.hasTvInputFramework(getContext())) {
            return;
        }
        // Test describeContents
        assertEquals(0, mStubInfo.describeContents());

        // Test equals
        assertTrue(mStubInfo.equals(mStubInfo));

        // Test getId
        final ComponentName componentName =
                new ComponentName(getContext(), StubTunerTvInputService.class);
        final String id = TvContract.buildInputId(componentName);
        assertEquals(id, mStubInfo.getId());

        // Test getServiceInfo
        assertEquals(getContext().getPackageManager().getServiceInfo(componentName, 0).name,
                mStubInfo.getServiceInfo().name);

        // Test hashCode
        assertEquals(id.hashCode(), mStubInfo.hashCode());

        // Test writeToParcel
        Parcel p = Parcel.obtain();
        mStubInfo.writeToParcel(p, 0);
        p.setDataPosition(0);
        TvInputInfo infoFromParcel = TvInputInfo.CREATOR.createFromParcel(p);
        assertEquals(mStubInfo.createSettingsIntent().getComponent(),
                infoFromParcel.createSettingsIntent().getComponent());
        assertEquals(mStubInfo.createSetupIntent().getComponent(),
                infoFromParcel.createSetupIntent().getComponent());
        assertEquals(mStubInfo.describeContents(), infoFromParcel.describeContents());
        assertTrue(mStubInfo.equals(infoFromParcel));
        assertEquals(mStubInfo.getId(), infoFromParcel.getId());
        assertEquals(mStubInfo.getParentId(), infoFromParcel.getParentId());
        assertEquals(mStubInfo.getServiceInfo().name, infoFromParcel.getServiceInfo().name);
        assertEquals(mStubInfo.getType(), infoFromParcel.getType());
        assertEquals(mStubInfo.hashCode(), infoFromParcel.hashCode());
        assertEquals(mStubInfo.isPassthroughInput(), infoFromParcel.isPassthroughInput());
        assertEquals(mStubInfo.loadIcon(getContext()).getConstantState(),
                infoFromParcel.loadIcon(getContext()).getConstantState());
        assertEquals(mStubInfo.loadLabel(getContext()), infoFromParcel.loadLabel(getContext()));
        assertEquals(mStubInfo.toString(), infoFromParcel.toString());
        p.recycle();
    }

    public void testGetIntentForSettingsActivity() throws Exception {
        if (!Utils.hasTvInputFramework(getContext())) {
            return;
        }
        Intent intent = mStubInfo.createSettingsIntent();

        assertEquals(intent.getComponent(), new ComponentName(getContext(),
                TvInputSettingsActivityStub.class));
        String inputId = intent.getStringExtra(TvInputInfo.EXTRA_INPUT_ID);
        assertEquals(mStubInfo.getId(), inputId);
    }

    public void testGetIntentForSetupActivity() throws Exception {
        if (!Utils.hasTvInputFramework(getContext())) {
            return;
        }
        Intent intent = mStubInfo.createSetupIntent();

        assertEquals(intent.getComponent(), new ComponentName(getContext(),
                TvInputSetupActivityStub.class));
        String inputId = intent.getStringExtra(TvInputInfo.EXTRA_INPUT_ID);
        assertEquals(mStubInfo.getId(), inputId);
    }

    public void testTunerHasNoParentId() throws Exception {
        if (!Utils.hasTvInputFramework(getContext())) {
            return;
        }
        assertNull(mStubInfo.getParentId());
    }

    public void testGetTypeForTuner() throws Exception {
        if (!Utils.hasTvInputFramework(getContext())) {
            return;
        }
        assertEquals(mStubInfo.getType(), TvInputInfo.TYPE_TUNER);
    }

    public void testTunerIsNotPassthroughInput() throws Exception {
        if (!Utils.hasTvInputFramework(getContext())) {
            return;
        }
        assertFalse(mStubInfo.isPassthroughInput());
    }

    public void testLoadIcon() throws Exception {
        if (!Utils.hasTvInputFramework(getContext())) {
            return;
        }
        assertEquals(mStubInfo.loadIcon(getContext()).getConstantState(),
                mStubInfo.getServiceInfo().loadIcon(mPackageManager).getConstantState());
    }

    public void testLoadLabel() throws Exception {
        if (!Utils.hasTvInputFramework(getContext())) {
            return;
        }
        assertEquals(mStubInfo.loadLabel(getContext()),
                mStubInfo.getServiceInfo().loadLabel(mPackageManager));
    }
}

/*
 * Copyright (C) 2015 The Android Open Source Project
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

package android.telecom.cts;

import android.telecom.Conference;
import android.telecom.Connection;
import android.telecom.DisconnectCause;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;

/**
 * {@link Conference} subclass that immediately performs any state changes that are a result of
 * callbacks sent from Telecom.
 */
public class MockConference extends Conference {

    public MockConference(PhoneAccountHandle phoneAccount) {
        super(phoneAccount);
    }

    public MockConference(Connection a, Connection b) {
        super(null);
        setConnectionCapabilities(a.getConnectionCapabilities());
        addConnection(a);
        addConnection(b);
        setActive();
    }

    @Override
    public void onDisconnect() {
        super.onDisconnect();
        for (Connection c : getConnections()) {
            c.setDisconnected(new DisconnectCause(DisconnectCause.REMOTE));
            c.destroy();
        }
        destroy();
    }

    @Override
    public void onSeparate(Connection connection) {
        if (getConnections().contains(connection)) {
            removeConnection(connection);
        }
    }

    @Override
    public void onMerge() {
        super.onMerge();
        // Let's just change the connection display name for testing that onMerge was invoked.
        for (Connection c : getConnections()) {
            c.setCallerDisplayName(
                    TestUtils.MERGE_CALLER_NAME, TelecomManager.PRESENTATION_ALLOWED);
        }
    }

    @Override
    public void onSwap() {
        super.onSwap();
        // Let's just change the connection display name for testing that onSwap was invoked.
        for (Connection c : getConnections()) {
            c.setCallerDisplayName(
                    TestUtils.SWAP_CALLER_NAME, TelecomManager.PRESENTATION_ALLOWED);
        }
    }

    @Override
    public void onHold() {
        super.onHold();
        for (Connection c : getConnections()) {
            c.setOnHold();
        }
        setOnHold();
    }

    @Override
    public void onUnhold() {
        super.onUnhold();
        for (Connection c : getConnections()) {
            c.setActive();
        }
        setActive();
    }
}

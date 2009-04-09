/*
 * Copyright (C) 2008 The Android Open Source Project
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

package android.text.method.cts;

import dalvik.annotation.TestLevel;
import dalvik.annotation.TestTargetClass;
import dalvik.annotation.TestTargetNew;
import dalvik.annotation.ToBeFixed;

import android.graphics.Rect;
import android.test.ActivityInstrumentationTestCase2;
import android.text.method.ReplacementTransformationMethod;
import android.view.View;
import android.widget.EditText;

/**
 * Test {@link ReplacementTransformationMethod}.
 */
@TestTargetClass(ReplacementTransformationMethod.class)
public class ReplacementTransformationMethodTest extends
        ActivityInstrumentationTestCase2<StubActivity> {
    private static final char[] ORIGINAL = new char[] { '0', '1' };

    private static final char[] ORIGINAL_WITH_MORE_CHARS = new char[] { '0', '1', '2' };

    private static final char[] ORIGINAL_WITH_SAME_CHARS = new char[] { '0', '0' };

    private static final char[] REPLACEMENT = new char[] { '3', '4' };

    private static final char[] REPLACEMENT_WITH_MORE_CHARS = new char[] { '3', '4', '5' };

    private static final char[] REPLACEMENT_WITH_SAME_CHARS = new char[] { '3', '3' };

    private EditText mEditText;

    public ReplacementTransformationMethodTest() {
        super("com.android.cts.stub", StubActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mEditText = new EditText(getActivity());
    }

    @TestTargetNew(
        level = TestLevel.PARTIAL_COMPLETE,
        method = "getTransformation",
        args = {java.lang.CharSequence.class, android.view.View.class}
    )
    public void testGetTransformation() {
        MyReplacementTransformationMethod method =
            new MyReplacementTransformationMethod(ORIGINAL, REPLACEMENT);
        CharSequence result = method.getTransformation("010101", null);
        assertEquals("343434", result.toString());

        mEditText.setTransformationMethod(method);
        mEditText.setText("010101");
        // TODO cannot get transformed text from the view
    }

    @TestTargetNew(
        level = TestLevel.COMPLETE,
        method = "getTransformation",
        args = {CharSequence.class, View.class}
    )
    @ToBeFixed(bug = "1695243", explanation = "Android API javadocs are incomplete. @throws clause "
            + "should be added into javadoc of "
            + "ReplacementTransformationMethod#getTransformation(CharSequence, android.view.View)"
            + "when the params source is null")
    public void testGetTransformationWithAbnormalCharSequence() {
        ReplacementTransformationMethod method = new MyReplacementTransformationMethod(ORIGINAL,
                REPLACEMENT);

        try {
            method.getTransformation(null, null);
            fail("The method should check whether the char sequence is null.");
        } catch (NullPointerException e) {
            // expected
        }

        assertEquals("", method.getTransformation("", null).toString());
    }

    @TestTargetNew(
        level = TestLevel.PARTIAL_COMPLETE,
        method = "getTransformation",
        args = {CharSequence.class, View.class}
    )
    public void testGetTransformationWithAbmornalReplacement() {
        // replacement has same chars
        ReplacementTransformationMethod method =
            new MyReplacementTransformationMethod(ORIGINAL, REPLACEMENT_WITH_SAME_CHARS);
        assertEquals("333333", method.getTransformation("010101", null).toString());

        mEditText.setTransformationMethod(method);
        mEditText.setText("010101");
        // TODO cannot get transformed text from the view

        // replacement has more chars than original
        method = new MyReplacementTransformationMethod(ORIGINAL, REPLACEMENT_WITH_MORE_CHARS);
        assertEquals("343434", method.getTransformation("010101", null).toString());

        mEditText.setTransformationMethod(method);
        mEditText.setText("010101");
        // TODO cannot get transformed text from the view
    }

    @TestTargetNew(
        level = TestLevel.PARTIAL_COMPLETE,
        method = "getTransformation",
        args = {CharSequence.class, View.class}
    )
    @ToBeFixed(bug = "1695243", explanation = "Android API javadocs are incomplete. @throws clause "
            + "should be added into javadoc of "
            + "ReplacementTransformationMethod#getTransformation(CharSequence, android.view.View)"
            + "when threre is more chars in the original than replacement.")
    public void testGetTransformationWithAbmornalOriginal() {
        // original has same chars
        ReplacementTransformationMethod method =
            new MyReplacementTransformationMethod(ORIGINAL_WITH_SAME_CHARS, REPLACEMENT);
        assertEquals("414141", method.getTransformation("010101", null).toString());

        mEditText.setTransformationMethod(method);
        mEditText.setText("010101");
        // TODO cannot get transformed text from the view

        // original has more chars than replacement
        method = new MyReplacementTransformationMethod(ORIGINAL_WITH_MORE_CHARS, REPLACEMENT);
        try {
            method.getTransformation("012012012", null);
            fail("Threre is more chars in the original than replacement.");
        } catch (ArrayIndexOutOfBoundsException e) {
            // expected
        }
    }

    @TestTargetNew(
        level = TestLevel.NOT_NECESSARY,
        notes = "This is a blank method",
        method = "onFocusChanged",
        args = {View.class, CharSequence.class, boolean.class, int.class, Rect.class}
    )
    public void testOnFocusChanged() {
        ReplacementTransformationMethod method = new MyReplacementTransformationMethod(ORIGINAL,
                REPLACEMENT);
        // blank method
        method.onFocusChanged(null, null, true, 0, null);
    }

    private class MyReplacementTransformationMethod extends ReplacementTransformationMethod {
        private char[] mOriginal;

        private char[] mReplacement;

        public MyReplacementTransformationMethod(char[] original, char[] replacement) {
            mOriginal = original;
            mReplacement = replacement;
        }

        @Override
        protected char[] getOriginal() {
            return mOriginal;
        }

        @Override
        protected char[] getReplacement() {
            return mReplacement;
        }
    }
}

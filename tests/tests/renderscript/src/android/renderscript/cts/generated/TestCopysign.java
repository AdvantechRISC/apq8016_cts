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

// Don't edit this file!  It is auto-generated by frameworks/rs/api/gen_runtime.

package android.renderscript.cts;

import android.renderscript.Allocation;
import android.renderscript.RSRuntimeException;
import android.renderscript.Element;

public class TestCopysign extends RSBaseCompute {

    private ScriptC_TestCopysign script;
    private ScriptC_TestCopysignRelaxed scriptRelaxed;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        script = new ScriptC_TestCopysign(mRS);
        scriptRelaxed = new ScriptC_TestCopysignRelaxed(mRS);
    }

    public class ArgumentsFloatFloatFloat {
        public float inMagnitudeValue;
        public float inSignValue;
        public Target.Floaty out;
    }

    private void checkCopysignFloatFloatFloat() {
        Allocation inMagnitudeValue = createRandomAllocation(mRS, Element.DataType.FLOAT_32, 1, 0x4218ae4ccf086614l, false);
        Allocation inSignValue = createRandomAllocation(mRS, Element.DataType.FLOAT_32, 1, 0xd95dacad9d8d3ef5l, false);
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 1), INPUTSIZE);
            script.set_gAllocInSignValue(inSignValue);
            script.forEach_testCopysignFloatFloatFloat(inMagnitudeValue, out);
            verifyResultsCopysignFloatFloatFloat(inMagnitudeValue, inSignValue, out, false);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testCopysignFloatFloatFloat: " + e.toString());
        }
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 1), INPUTSIZE);
            scriptRelaxed.set_gAllocInSignValue(inSignValue);
            scriptRelaxed.forEach_testCopysignFloatFloatFloat(inMagnitudeValue, out);
            verifyResultsCopysignFloatFloatFloat(inMagnitudeValue, inSignValue, out, true);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testCopysignFloatFloatFloat: " + e.toString());
        }
    }

    private void verifyResultsCopysignFloatFloatFloat(Allocation inMagnitudeValue, Allocation inSignValue, Allocation out, boolean relaxed) {
        float[] arrayInMagnitudeValue = new float[INPUTSIZE * 1];
        inMagnitudeValue.copyTo(arrayInMagnitudeValue);
        float[] arrayInSignValue = new float[INPUTSIZE * 1];
        inSignValue.copyTo(arrayInSignValue);
        float[] arrayOut = new float[INPUTSIZE * 1];
        out.copyTo(arrayOut);
        for (int i = 0; i < INPUTSIZE; i++) {
            for (int j = 0; j < 1 ; j++) {
                // Extract the inputs.
                ArgumentsFloatFloatFloat args = new ArgumentsFloatFloatFloat();
                args.inMagnitudeValue = arrayInMagnitudeValue[i];
                args.inSignValue = arrayInSignValue[i];
                // Figure out what the outputs should have been.
                Target target = new Target(relaxed);
                CoreMathVerifier.computeCopysign(args, target);
                // Validate the outputs.
                boolean valid = true;
                if (!args.out.couldBe(arrayOut[i * 1 + j])) {
                    valid = false;
                }
                if (!valid) {
                    StringBuilder message = new StringBuilder();
                    message.append("Input inMagnitudeValue: ");
                    message.append(String.format("%14.8g {%8x} %15a",
                            args.inMagnitudeValue, Float.floatToRawIntBits(args.inMagnitudeValue), args.inMagnitudeValue));
                    message.append("\n");
                    message.append("Input inSignValue: ");
                    message.append(String.format("%14.8g {%8x} %15a",
                            args.inSignValue, Float.floatToRawIntBits(args.inSignValue), args.inSignValue));
                    message.append("\n");
                    message.append("Expected output out: ");
                    message.append(args.out.toString());
                    message.append("\n");
                    message.append("Actual   output out: ");
                    message.append(String.format("%14.8g {%8x} %15a",
                            arrayOut[i * 1 + j], Float.floatToRawIntBits(arrayOut[i * 1 + j]), arrayOut[i * 1 + j]));
                    if (!args.out.couldBe(arrayOut[i * 1 + j])) {
                        message.append(" FAIL");
                    }
                    message.append("\n");
                    assertTrue("Incorrect output for checkCopysignFloatFloatFloat" +
                            (relaxed ? "_relaxed" : "") + ":\n" + message.toString(), valid);
                }
            }
        }
    }

    private void checkCopysignFloat2Float2Float2() {
        Allocation inMagnitudeValue = createRandomAllocation(mRS, Element.DataType.FLOAT_32, 2, 0x8efe093722e9f786l, false);
        Allocation inSignValue = createRandomAllocation(mRS, Element.DataType.FLOAT_32, 2, 0xb33b4a8420cec72bl, false);
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 2), INPUTSIZE);
            script.set_gAllocInSignValue(inSignValue);
            script.forEach_testCopysignFloat2Float2Float2(inMagnitudeValue, out);
            verifyResultsCopysignFloat2Float2Float2(inMagnitudeValue, inSignValue, out, false);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testCopysignFloat2Float2Float2: " + e.toString());
        }
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 2), INPUTSIZE);
            scriptRelaxed.set_gAllocInSignValue(inSignValue);
            scriptRelaxed.forEach_testCopysignFloat2Float2Float2(inMagnitudeValue, out);
            verifyResultsCopysignFloat2Float2Float2(inMagnitudeValue, inSignValue, out, true);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testCopysignFloat2Float2Float2: " + e.toString());
        }
    }

    private void verifyResultsCopysignFloat2Float2Float2(Allocation inMagnitudeValue, Allocation inSignValue, Allocation out, boolean relaxed) {
        float[] arrayInMagnitudeValue = new float[INPUTSIZE * 2];
        inMagnitudeValue.copyTo(arrayInMagnitudeValue);
        float[] arrayInSignValue = new float[INPUTSIZE * 2];
        inSignValue.copyTo(arrayInSignValue);
        float[] arrayOut = new float[INPUTSIZE * 2];
        out.copyTo(arrayOut);
        for (int i = 0; i < INPUTSIZE; i++) {
            for (int j = 0; j < 2 ; j++) {
                // Extract the inputs.
                ArgumentsFloatFloatFloat args = new ArgumentsFloatFloatFloat();
                args.inMagnitudeValue = arrayInMagnitudeValue[i * 2 + j];
                args.inSignValue = arrayInSignValue[i * 2 + j];
                // Figure out what the outputs should have been.
                Target target = new Target(relaxed);
                CoreMathVerifier.computeCopysign(args, target);
                // Validate the outputs.
                boolean valid = true;
                if (!args.out.couldBe(arrayOut[i * 2 + j])) {
                    valid = false;
                }
                if (!valid) {
                    StringBuilder message = new StringBuilder();
                    message.append("Input inMagnitudeValue: ");
                    message.append(String.format("%14.8g {%8x} %15a",
                            args.inMagnitudeValue, Float.floatToRawIntBits(args.inMagnitudeValue), args.inMagnitudeValue));
                    message.append("\n");
                    message.append("Input inSignValue: ");
                    message.append(String.format("%14.8g {%8x} %15a",
                            args.inSignValue, Float.floatToRawIntBits(args.inSignValue), args.inSignValue));
                    message.append("\n");
                    message.append("Expected output out: ");
                    message.append(args.out.toString());
                    message.append("\n");
                    message.append("Actual   output out: ");
                    message.append(String.format("%14.8g {%8x} %15a",
                            arrayOut[i * 2 + j], Float.floatToRawIntBits(arrayOut[i * 2 + j]), arrayOut[i * 2 + j]));
                    if (!args.out.couldBe(arrayOut[i * 2 + j])) {
                        message.append(" FAIL");
                    }
                    message.append("\n");
                    assertTrue("Incorrect output for checkCopysignFloat2Float2Float2" +
                            (relaxed ? "_relaxed" : "") + ":\n" + message.toString(), valid);
                }
            }
        }
    }

    private void checkCopysignFloat3Float3Float3() {
        Allocation inMagnitudeValue = createRandomAllocation(mRS, Element.DataType.FLOAT_32, 3, 0x9a9db55e1b468741l, false);
        Allocation inSignValue = createRandomAllocation(mRS, Element.DataType.FLOAT_32, 3, 0x8ec9f3bdc39ab32cl, false);
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 3), INPUTSIZE);
            script.set_gAllocInSignValue(inSignValue);
            script.forEach_testCopysignFloat3Float3Float3(inMagnitudeValue, out);
            verifyResultsCopysignFloat3Float3Float3(inMagnitudeValue, inSignValue, out, false);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testCopysignFloat3Float3Float3: " + e.toString());
        }
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 3), INPUTSIZE);
            scriptRelaxed.set_gAllocInSignValue(inSignValue);
            scriptRelaxed.forEach_testCopysignFloat3Float3Float3(inMagnitudeValue, out);
            verifyResultsCopysignFloat3Float3Float3(inMagnitudeValue, inSignValue, out, true);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testCopysignFloat3Float3Float3: " + e.toString());
        }
    }

    private void verifyResultsCopysignFloat3Float3Float3(Allocation inMagnitudeValue, Allocation inSignValue, Allocation out, boolean relaxed) {
        float[] arrayInMagnitudeValue = new float[INPUTSIZE * 4];
        inMagnitudeValue.copyTo(arrayInMagnitudeValue);
        float[] arrayInSignValue = new float[INPUTSIZE * 4];
        inSignValue.copyTo(arrayInSignValue);
        float[] arrayOut = new float[INPUTSIZE * 4];
        out.copyTo(arrayOut);
        for (int i = 0; i < INPUTSIZE; i++) {
            for (int j = 0; j < 3 ; j++) {
                // Extract the inputs.
                ArgumentsFloatFloatFloat args = new ArgumentsFloatFloatFloat();
                args.inMagnitudeValue = arrayInMagnitudeValue[i * 4 + j];
                args.inSignValue = arrayInSignValue[i * 4 + j];
                // Figure out what the outputs should have been.
                Target target = new Target(relaxed);
                CoreMathVerifier.computeCopysign(args, target);
                // Validate the outputs.
                boolean valid = true;
                if (!args.out.couldBe(arrayOut[i * 4 + j])) {
                    valid = false;
                }
                if (!valid) {
                    StringBuilder message = new StringBuilder();
                    message.append("Input inMagnitudeValue: ");
                    message.append(String.format("%14.8g {%8x} %15a",
                            args.inMagnitudeValue, Float.floatToRawIntBits(args.inMagnitudeValue), args.inMagnitudeValue));
                    message.append("\n");
                    message.append("Input inSignValue: ");
                    message.append(String.format("%14.8g {%8x} %15a",
                            args.inSignValue, Float.floatToRawIntBits(args.inSignValue), args.inSignValue));
                    message.append("\n");
                    message.append("Expected output out: ");
                    message.append(args.out.toString());
                    message.append("\n");
                    message.append("Actual   output out: ");
                    message.append(String.format("%14.8g {%8x} %15a",
                            arrayOut[i * 4 + j], Float.floatToRawIntBits(arrayOut[i * 4 + j]), arrayOut[i * 4 + j]));
                    if (!args.out.couldBe(arrayOut[i * 4 + j])) {
                        message.append(" FAIL");
                    }
                    message.append("\n");
                    assertTrue("Incorrect output for checkCopysignFloat3Float3Float3" +
                            (relaxed ? "_relaxed" : "") + ":\n" + message.toString(), valid);
                }
            }
        }
    }

    private void checkCopysignFloat4Float4Float4() {
        Allocation inMagnitudeValue = createRandomAllocation(mRS, Element.DataType.FLOAT_32, 4, 0xa63d618513a316fcl, false);
        Allocation inSignValue = createRandomAllocation(mRS, Element.DataType.FLOAT_32, 4, 0x6a589cf766669f2dl, false);
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 4), INPUTSIZE);
            script.set_gAllocInSignValue(inSignValue);
            script.forEach_testCopysignFloat4Float4Float4(inMagnitudeValue, out);
            verifyResultsCopysignFloat4Float4Float4(inMagnitudeValue, inSignValue, out, false);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testCopysignFloat4Float4Float4: " + e.toString());
        }
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 4), INPUTSIZE);
            scriptRelaxed.set_gAllocInSignValue(inSignValue);
            scriptRelaxed.forEach_testCopysignFloat4Float4Float4(inMagnitudeValue, out);
            verifyResultsCopysignFloat4Float4Float4(inMagnitudeValue, inSignValue, out, true);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testCopysignFloat4Float4Float4: " + e.toString());
        }
    }

    private void verifyResultsCopysignFloat4Float4Float4(Allocation inMagnitudeValue, Allocation inSignValue, Allocation out, boolean relaxed) {
        float[] arrayInMagnitudeValue = new float[INPUTSIZE * 4];
        inMagnitudeValue.copyTo(arrayInMagnitudeValue);
        float[] arrayInSignValue = new float[INPUTSIZE * 4];
        inSignValue.copyTo(arrayInSignValue);
        float[] arrayOut = new float[INPUTSIZE * 4];
        out.copyTo(arrayOut);
        for (int i = 0; i < INPUTSIZE; i++) {
            for (int j = 0; j < 4 ; j++) {
                // Extract the inputs.
                ArgumentsFloatFloatFloat args = new ArgumentsFloatFloatFloat();
                args.inMagnitudeValue = arrayInMagnitudeValue[i * 4 + j];
                args.inSignValue = arrayInSignValue[i * 4 + j];
                // Figure out what the outputs should have been.
                Target target = new Target(relaxed);
                CoreMathVerifier.computeCopysign(args, target);
                // Validate the outputs.
                boolean valid = true;
                if (!args.out.couldBe(arrayOut[i * 4 + j])) {
                    valid = false;
                }
                if (!valid) {
                    StringBuilder message = new StringBuilder();
                    message.append("Input inMagnitudeValue: ");
                    message.append(String.format("%14.8g {%8x} %15a",
                            args.inMagnitudeValue, Float.floatToRawIntBits(args.inMagnitudeValue), args.inMagnitudeValue));
                    message.append("\n");
                    message.append("Input inSignValue: ");
                    message.append(String.format("%14.8g {%8x} %15a",
                            args.inSignValue, Float.floatToRawIntBits(args.inSignValue), args.inSignValue));
                    message.append("\n");
                    message.append("Expected output out: ");
                    message.append(args.out.toString());
                    message.append("\n");
                    message.append("Actual   output out: ");
                    message.append(String.format("%14.8g {%8x} %15a",
                            arrayOut[i * 4 + j], Float.floatToRawIntBits(arrayOut[i * 4 + j]), arrayOut[i * 4 + j]));
                    if (!args.out.couldBe(arrayOut[i * 4 + j])) {
                        message.append(" FAIL");
                    }
                    message.append("\n");
                    assertTrue("Incorrect output for checkCopysignFloat4Float4Float4" +
                            (relaxed ? "_relaxed" : "") + ":\n" + message.toString(), valid);
                }
            }
        }
    }

    public void testCopysign() {
        checkCopysignFloatFloatFloat();
        checkCopysignFloat2Float2Float2();
        checkCopysignFloat3Float3Float3();
        checkCopysignFloat4Float4Float4();
    }
}
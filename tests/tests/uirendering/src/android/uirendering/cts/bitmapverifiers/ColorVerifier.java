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
package android.uirendering.cts.bitmapverifiers;

/**
 * Checks to see if a bitmap is entirely a single color
 */
public class ColorVerifier extends BitmapVerifier {
    private int mColor;

    public ColorVerifier(int color) {
        mColor = color;
    }

    @Override
    public boolean verify(int[] bitmap, int offset, int stride, int width, int height) {
        for (int y = 0 ; y < height ; y++) {
            for (int x = 0 ; x < width ; x++) {
                if (bitmap[indexFromXAndY(x, y, stride, offset)] != mColor) {
                    return false;
                }
            }
        }
        return true;
    }
}
; Copyright (C) 2008 The Android Open Source Project
;
; Licensed under the Apache License, Version 2.0 (the "License");
; you may not use this file except in compliance with the License.
; You may obtain a copy of the License at
;
;      http://www.apache.org/licenses/LICENSE-2.0
;
; Unless required by applicable law or agreed to in writing, software
; distributed under the License is distributed on an "AS IS" BASIS,
; WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
; See the License for the specific language governing permissions and
; limitations under the License.

.source T_move_wide_1.java
.class public dot.junit.opcodes.move_wide.d.T_move_wide_1
.super java/lang/Object


.method public <init>()V
.limit regs 1

       invoke-direct {v0}, java/lang/Object/<init>()V
       return-void
.end method

.method public static run()Z
.limit regs 16
       const-wide v0, 1234
       const-wide v14, 567989

       move-wide v0, v14
       
       const-wide v4, 567989

       cmp-long v10, v0, v4
       if-nez v10, Label0
       cmp-long v10, v14, v4
       if-nez v10, Label0
       
       const v1, 1
       return v1

Label0:
       const v1, 0
       return v1
.end method



package com.bignerdranch.android.shootit;

/**
 * Created by robbiepaine on 12/7/15.
 */
/*
Copyright 2015 Robbie Paine and Igwe Igwe-Kalu

        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

        Unless required by applicable law or agreed to in writing, software
        distributed under the License is distributed on an "AS IS" BASIS,
        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
        See the License for the specific language governing permissions and
        limitations under the License.
*/

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Shoot.class);
        Parse.initialize(this, "SiMNxGWtpqUqvPjFHNDC8X9vv95gYHHq3BuuOoyQ", "MjWwXB0bRNHaiaw8fB20gf1uN0IIFYwteTp0INDY");
    }
}
